package com.thalesgroup.ibis;

import com.google.protobuf.ProtocolStringList;
import com.thalesgroup.ibis.protos.*;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.thalesgroup.ibis.Constants.DEBUG;
import static com.thalesgroup.ibis.Constants.DISPLAY_SENSOR;

public class SensorManager implements Runnable {
	private final Map<String,Object> objects;
	
	public SensorManager(Map<String,Object> objects) {
		this.objects = objects;
	}
	
	@Override
	public void run() {
		try (ZContext context = new ZContext()) {

			// Récupération du modèle de l'ontologie capteurs et rédaction du tronc commun de la requete capteur->attributs
			Model m = KnowledgeBase.getSensorNetworkOntologyManager().getModel();
			ParameterizedSparqlString queryString = new ParameterizedSparqlString();
			queryString.setNsPrefix("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
			queryString.setNsPrefix("sensor-network", "urn:absolute:/ontologies/sensor-network#");

			try (ZMQ.Socket socket = context.createSocket(SocketType.REP)) {
				socket.bind("tcp://localhost:5555");

				// Tant que le thread est actif
				while (!Thread.currentThread().isInterrupted()) {
					if (DISPLAY_SENSOR)
						System.out.println("[SENSOR] En attente de requete");

					// Attente d'une requete d'un thread piste
					try {
						Request request = Request.parseFrom(socket.recv(0));
						if (DISPLAY_SENSOR)
							System.out.println("[SENSOR] Requete reçue:\n" + request);

						Object obj = objects.get(request.getTrackId());

						if (obj == null) {
							System.err.println("Object " + request.getTrackId() + " is not simulated.");
							continue;
						}

						ProtocolStringList modes = request.getSensorModeIdsList();
						long distance = request.getDistance();

						Answer.Builder builder = Answer.newBuilder();
						builder.setTrackId(request.getTrackId())
								.setTimestamp(new Date().getTime())
								.setSourceSensorModeId("");

						for (String mode : modes) {
							if (DISPLAY_SENSOR)
								System.out.println("[SENSOR] Essai du mode:" + mode);

							if (new Random().nextDouble() < Constants.SENSOR_AVAILABILITY) { // Disponibilité du capteur
								queryString.setCommandText("" +
										"SELECT ?time ?minDistance ?maxDistance WHERE {\n" +
										"\t <" + mode + "> rdf:type sensor-network:SensorMode ;\n" +
										"\t\t sensor-network:acquisitionTime ?time.\n" +
										//"\t OPTIONAL { <" + mode + "> sensor-network:minDistance ?minDistance }.\n" +
										//"\t OPTIONAL { <" + mode + "> sensor-network:maxDistance ?maxDistance }.\n" +
										"}");

								if (DEBUG) {
									System.out.println(queryString);
								}

								Query q = QueryFactory.create(queryString.asQuery());

								QueryExecution qe = QueryExecutionFactory.create(q, m);
								ResultSet r = qe.execSelect();

								List<String> v = r.getResultVars();

								if (r.hasNext()) {
									QuerySolution found = r.next();

									Literal minDistance = found.getLiteral(v.get(1));
									Literal maxDistance = found.getLiteral(v.get(2));

									if((minDistance != null && minDistance.getFloat() > distance)
									|| (maxDistance != null && maxDistance.getFloat() < distance)) continue;

									builder.setTimeTaken(found.getLiteral(v.get(0)).getLong());

									queryString.setCommandText("" +
											"SELECT ?iri ?accuracy WHERE {\n" +
											"\t <" + mode + "> rdf:type sensor-network:SensorMode ;\n" +
											"\t\t sensor-network:observes ?attribut.\n" +
											"\t ?attribut rdf:type sensor-network:SensorAttribute ;\n" +
											"\t\t sensor-network:hasAttribute ?iri ;\n" +
											"\t\t sensor-network:accuracy ?accuracy .\n" +
											"\t ?iri rdf:type sensor-network:Attribute.\n" +
											"}");

									if (DEBUG) {
										System.out.println(queryString);
									}

									Query sparqlQuery = QueryFactory.create(queryString.asQuery());

									QueryExecution queryExecution = QueryExecutionFactory.create(sparqlQuery, m);
									ResultSet result = queryExecution.execSelect(); // Attributs que le capteur peut récupérer

									List<String> vars = result.getResultVars();

									builder.setSourceSensorModeId(mode);

									// Création de la réponse à partir de la description de l'objet et du capteur choisi
									result.forEachRemaining(att -> {
										String attribute = att.getResource(vars.get(0)).getURI()
												.replace("urn:absolute:/ontologies/sensor-network#", "");

										if (obj.get(attribute) != null) {
											switch (attribute) {
												case "latitude" -> {
													Angle.Builder lat = Angle.newBuilder()
															.setAngle(0)    // TODO: générer une position cohérente
															.setAngleUncertainty(att.getLiteral(vars.get(1)).getFloat());
													builder.setLatitude(lat);
												}
												case "longitude" -> {
													Angle.Builder lon = Angle.newBuilder()
															.setAngle(0)    // TODO: générer une position cohérente
															.setAngleUncertainty(att.getLiteral(vars.get(1)).getFloat());
													builder.setLongitude(lon);
												}
												case "elevation" -> {
													Distance.Builder alt = Distance.newBuilder()
															.setDistance(obj.getNumericValue(attribute))
															.setDistanceUncertainty(att.getLiteral(vars.get(1)).getFloat());
													builder.setElevation(alt);
												}
												case "distance" -> {
													Distance.Builder dist = Distance.newBuilder()
															.setDistance(0) // TODO: générer une distance cohérente
															.setDistanceUncertainty(att.getLiteral(vars.get(1)).getFloat());
													builder.setDistance(dist);
												}
												case "height" -> {
													Dimension.Builder dim = Dimension.newBuilder()
															.setDimension(obj.getNumericValue(attribute))
															.setDimensionUncertainty(att.getLiteral(vars.get(1)).getFloat());
													builder.setHeight(dim);
												}
												case "width" -> {
													Dimension.Builder dim = Dimension.newBuilder()
															.setDimension(obj.getNumericValue(attribute))
															.setDimensionUncertainty(att.getLiteral(vars.get(1)).getFloat());
													builder.setWidth(dim);
												}
												case "length" -> {
													Dimension.Builder dim = Dimension.newBuilder()
															.setDimension(obj.getNumericValue(attribute))
															.setDimensionUncertainty(att.getLiteral(vars.get(1)).getFloat());
													builder.setLength(dim);
												}
												case "velocity" -> {
													Velocity.Builder vel = Velocity.newBuilder()
															.setVelocity(obj.getNumericValue(attribute))
															.setVelocityUncertainty(att.getLiteral(vars.get(1)).getFloat());
													builder.setVelocity(vel);
												}
												case "type" -> builder.setObjectType(obj.getSymbolicValue(attribute));
												case "identification" -> builder.setObjectIdentification(obj.getSymbolicValue(attribute));
												case "waveformCaracterisation" -> builder.setWaveformCaracterisation(obj.getSymbolicValue(attribute));
												case "radarCrossSection" -> {
													RadarCrossSection.Builder rcs = RadarCrossSection.newBuilder()
															.setRadarCrossSection(obj.getNumericValue(attribute))
															.setRadarCrossSectionUncertainty(att.getLiteral(vars.get(1)).getFloat());
													builder.setRadarCrossSection(rcs);
												}
											}
										}
									});

									if (DISPLAY_SENSOR) {
										System.out.println("Mode " + mode + " utilisé.");
									}
									break;
								}
							}
							if (DISPLAY_SENSOR) {
								System.out.println("Mode " + mode + " non disponible.");
							}
						}

						// Envoi de la réponse au thread piste
						Answer answer = builder.build();
						socket.send(answer.toByteArray());
						if(DISPLAY_SENSOR)
							System.out.println("[SENSOR] Reponse envoyee:\n" + answer);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}