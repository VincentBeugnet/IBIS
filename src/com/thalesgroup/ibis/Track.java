package com.thalesgroup.ibis;

import com.thalesgroup.ibis.protos.Answer;
import com.thalesgroup.ibis.protos.Request;
import com.thalesgroup.ibis.utils.Chrono;
import com.thalesgroup.ibis.utils.IndexedFloatRanges;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.vocabulary.RDF;
import org.semanticweb.HermiT.ReasonerFactory;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.model.parameters.OntologyCopy;
import org.semanticweb.owlapi.reasoner.*;
import org.semanticweb.owlapi.reasoner.impl.OWLClassNodeSet;
import org.semanticweb.owlapi.search.EntitySearcher;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import uk.ac.manchester.cs.owl.owlapi.OWLDataOneOfImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLDatatypeRestrictionImpl;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.thalesgroup.ibis.Constants.*;


public class Track implements Runnable {

	private final OWLOntologyManager manager = OWLManager.createConcurrentOWLOntologyManager();
	private final OWLDataFactory factory = manager.getOWLDataFactory();
	private final OWLOntology ontology;
	private final OWLReasoner reasoner;

	private Map<IRI, Map<IRI, Pair<Float, Float>>> numericAttributes;
	private Map<IRI, Map<IRI, List<String>>> symbolicAttributes;
	private Map<IRI, Float> neededAttributes;

	private final Set<Observation> observations = new LinkedHashSet<>();
	private final Map<IRI, Integer> attributeObservation = new HashMap<>();

	private final IRI identifier;
	private final OWLNamedIndividual individual;
	private NodeSet<OWLClass> inferedSuperType;
	private Set<OWLClass> inferedClasses;

	private final boolean type;

	public Track(IRI identifier, boolean type) {
		OWLOntology temp;

		this.type = type;
		try {
			temp = manager.copyOntology(KnowledgeBase.getObjectsOntologyManager().getOntology(), OntologyCopy.SHALLOW);
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
			temp = KnowledgeBase.getObjectsOntologyManager().getOntology();
		}
		this.ontology = temp;

		reasoner = new ReasonerFactory().createNonBufferingReasoner(this.ontology);
		reasoner.precomputeInferences(InferenceType.values());

		IRI iri = ontology.getOntologyID().getOntologyIRI().orElseGet(() -> IRI.create(""));
		this.identifier = identifier;
		this.individual = factory.getOWLNamedIndividual(this.identifier);

		OWLClass objectClass = factory.getOWLClass(iri + "#Object");
		OWLClassAssertionAxiom isObject = factory.getOWLClassAssertionAxiom(objectClass, this.individual);
		ontology.addAxiom(isObject);

		TrackList.add(this);
	}

	public IRI getIdentifier() {
		return this.identifier;
	}

	private boolean setSuperType() {
		try {
			this.inferedSuperType = this.reasoner.getTypes(this.individual, InferenceDepth.DIRECT);
			if (!this.inferedSuperType.isTopSingleton() && !this.inferedSuperType.isBottomSingleton())
				return true;
		}
		catch (InconsistentOntologyException ex) {
			this.inferedSuperType = new OWLClassNodeSet();
			if (DEBUG) System.err.println(ex.getMessage());
		}

		return false;
	}

	private boolean isCandidateClass(OWLClass owlClass) {
		OWLClassAssertionAxiom assertion = this.factory.getOWLClassAssertionAxiom(owlClass, this.individual);
		this.ontology.addAxiom(assertion);

		boolean consistency = this.reasoner.isConsistent();

		this.ontology.removeAxiom(assertion);

		return consistency;
	}

	private void addAttributesFromClass(OWLClass owlClass) {
		addAttributesFromClass(owlClass, new HashMap<>(), new HashMap<>());
	}

	private void addAttributesFromClass(OWLClass owlClass, Map<IRI, Pair<Float, Float>> numeric, Map<IRI, List<String>> symbolic) {
		if (!owlClass.isBottomEntity() && (inferedSuperType.containsEntity(owlClass) || isCandidateClass(owlClass))) {
			List<OWLClass> subClasses = reasoner.subClasses(owlClass, InferenceDepth.DIRECT).toList();
			Map<IRI, Pair<Float, Float>> superClassNumeric = numeric.entrySet().stream()
					.collect(Collectors.toMap(Map.Entry::getKey, e -> Pair.of(e.getValue().getLeft(), e.getValue().getRight())));
			Map<IRI, List<String>> superClassSymbolic = symbolic.entrySet().stream()
					.collect(Collectors.toMap(Map.Entry::getKey, e -> List.copyOf(e.getValue())));

			List<OWLClassExpression> def = EntitySearcher.getSuperClasses(owlClass, this.ontology).toList();

			def.forEach(exp -> {
				if(exp instanceof OWLDataAllValuesFrom allValues) {
					IRI property = IRI.create(allValues.getProperty().toString());

					// Numeric property
					if(allValues.getFiller() instanceof OWLDatatypeRestrictionImpl filler) {
						List<OWLFacetRestriction> restrictionsList = filler.facetRestrictionsAsList();
						String[] cut = property.toString().split("#");
						if (cut[1].startsWith("min")) {
							IRI prop = IRI.create(cut[0] + '#' + cut[1].substring(3,5).toLowerCase() + cut[1].substring(5));
							Pair<Float, Float> oldValues = Objects.requireNonNullElse(superClassNumeric.get(prop), Pair.of(-800000.0f, 800000.0f));

							restrictionsList.forEach(facet -> {
								float value = facet.getFacetValue().parseFloat();
								switch(facet.getFacet()) {
									case MIN_INCLUSIVE, MIN_EXCLUSIVE -> {
										if(value > oldValues.getLeft())
											superClassNumeric.put(prop, new ImmutablePair<>(value, oldValues.getRight()));
									}
								}
							});
						}
						else if (cut[1].startsWith("max")) {
							IRI prop = IRI.create(cut[0] + '#' + cut[1].substring(3,5).toLowerCase() + cut[1].substring(5));
							Pair<Float, Float> oldValues = Objects.requireNonNullElse(superClassNumeric.get(prop), Pair.of(-800000.0f, 800000.0f));

							restrictionsList.forEach(facet -> {
								float value = facet.getFacetValue().parseFloat();
								switch(facet.getFacet()) {
									case MAX_INCLUSIVE, MAX_EXCLUSIVE -> {
										if(value < oldValues.getRight())
											superClassNumeric.put(prop, new ImmutablePair<>(oldValues.getLeft(), value));
									}
								}
							});
						}
					}
					// Symbolic property
					else if(allValues.getFiller() instanceof OWLDataOneOfImpl filler) {
						superClassSymbolic.put(property, filler.getOperandsAsList().stream().map(OWLLiteral::getLiteral).toList());
					}
				}
				// Else not a property
			});

			if(subClasses.size() > 1) {
				subClasses.forEach(cls -> addAttributesFromClass(cls, superClassNumeric, superClassSymbolic));
			} else {
				inferedClasses.add(owlClass);
				superClassNumeric.forEach((iri, pair) -> numericAttributes.computeIfAbsent(iri, k -> new HashMap<>()).put(owlClass.getIRI(), pair));
				superClassSymbolic.forEach((iri, strings) -> symbolicAttributes.computeIfAbsent(iri, k -> new HashMap<>()).put(owlClass.getIRI(), strings));
			}
		}
	}

	// Compute score for a symbolic attribute
	private float computeSymbolicAttributeScore(Map<IRI, List<String>> map) {
		Map<String, Float> values = new HashMap<>();
		map.forEach((iri, list) ->
				list.forEach(value -> {
					float v = values.getOrDefault(value, 0f);
					values.put(value, v+1);
				})
		);

		float probaC = 1f / map.size();
		float probaA = 1f / values.size();

		values.forEach((value, count) -> values.put(value, (values.size() - count) * count / values.size()));
		return values.values().stream().reduce(0.0f, Float::sum);
	}

	// Compute score for a numeric attribute
	private float computeNumericAttributeScore(Map<IRI, Pair<Float, Float>> map) {
		IndexedFloatRanges ranges = new IndexedFloatRanges();
		List<Pair<Float, Float>> pairs = map.values().stream().toList();

		ranges.addAll(pairs);

		return ranges.getExpectation();
	}

	private void setNeededAttributes() {
		symbolicAttributes = new HashMap<>();
		numericAttributes = new HashMap<>();
		neededAttributes = new HashMap<>();
		inferedClasses = new HashSet<>();

		inferedSuperType.forEach(owlClassNode -> {
			OWLClass owlClass = owlClassNode.getRepresentativeElement();

			addAttributesFromClass(owlClass);
		});

		if (DEBUG) {
			System.out.println("Attributs numériques: " + numericAttributes);
			System.out.println("Attributs symboliques: " + symbolicAttributes);
		}

		// Tri par score de discrimination
		if (type) {
			symbolicAttributes.forEach((attr, value) -> neededAttributes.put(attr, computeSymbolicAttributeScore(value)));
			numericAttributes.forEach((attr, value) -> neededAttributes.put(attr, computeNumericAttributeScore(value)));
		}
		else {
			symbolicAttributes.forEach((attr, value) -> neededAttributes.put(attr, 1f));
			numericAttributes.forEach((attr, value) -> neededAttributes.put(attr, 1f));
		}

		IRI sensorOntologyIRI = KnowledgeBase.getSensorNetworkOntologyManager().getOntology()
				.getOntologyID().getOntologyIRI().orElseGet(() -> IRI.create(""));
		Model model = KnowledgeBase.getSensorNetworkOntologyManager().getModel();
		ParameterizedSparqlString queryString = new ParameterizedSparqlString();
		queryString.setNsPrefix("rdf", RDF.getURI());
		queryString.setNsPrefix("sensor-network", sensorOntologyIRI + "#");

		Map<IRI, Float> lastingAttributes = neededAttributes.entrySet().stream().filter(
				x -> attributeObservation.getOrDefault(IRI.create(x.getKey().toString().replaceAll("[<>]", "")), 0) == 0
		).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

		if (lastingAttributes.size() > 0)
			neededAttributes = lastingAttributes.entrySet().stream()
					.filter(x -> x.getValue() > 0)
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		else neededAttributes = neededAttributes.entrySet().stream()
			.filter(x -> x.getValue() > 0)
			.filter(
				x -> {
					int nbObservations = attributeObservation.getOrDefault(IRI.create(x.getKey().toString().replaceAll("[<>]", "")), 0);
					try {
						queryString.setCommandText("" +
								"SELECT ?evolutive\nWHERE {\n\t" +
								x.getKey() + " rdf:type sensor-network:Attribute ;\n\t" +
								" sensor-network:isEvolutive ?evolutive .\n\t" +
								"}"
						);

						Query sparqlQuery = QueryFactory.create(queryString.asQuery());
						QueryExecution queryExecution = QueryExecutionFactory.create(sparqlQuery, model);
						ResultSet result = queryExecution.execSelect();

						List<String> vars = result.getResultVars();

						return (result.hasNext() && result.next().getLiteral(vars.get(0)).getBoolean() && nbObservations < MAX_MODE_USAGE);
					}
					catch(Exception e) {
						System.err.println(e.getMessage());
					}
					return false;
				}
		).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	// Remove this track from tracklist
	public void delete() {
		TrackList.remove(this);
	}

	private void updateProperty(IRI propertyIRI, Float min, Float max) {
		String soIRI = KnowledgeBase.getSensorNetworkOntologyManager().getOntology()
				.getOntologyID().getOntologyIRI().orElseGet(() -> IRI.create("")).toString() + '#';

		String prop = propertyIRI.toString().replace(soIRI, "");
		String upperCaseProp =  prop.substring(0, 1).toUpperCase() + prop.substring(1);

		OWLDataProperty minProperty = this.factory.getOWLDataProperty(IRI.create(soIRI + "min" + upperCaseProp));
		OWLDataProperty maxProperty = this.factory.getOWLDataProperty(IRI.create(soIRI + "max" + upperCaseProp));

		ontology.add(this.factory.getOWLDataPropertyAssertionAxiom(maxProperty, this.individual, min));
		ontology.add(this.factory.getOWLDataPropertyAssertionAxiom(minProperty, this.individual, max));
	}

	private void updateProperty(IRI propertyIRI, String value) {
		OWLDataProperty property = this.factory.getOWLDataProperty(propertyIRI);
		List<String> values = EntitySearcher.getDataPropertyValues(this.individual, property, this.ontology).map(OWLLiteral::getLiteral).toList();

		if (!values.contains(value))
			ontology.add(this.factory.getOWLDataPropertyAssertionAxiom(property, this.individual, value));
	}


	@Override
	public void run() {
		double time = 0;
		double reasoningTime = 0;
		int iteration = 0;
		// Connexion au thread capteurs
		try (ZContext context = new ZContext()) {

			ZMQ.Socket socket = context.createSocket(SocketType.REQ);
			socket.connect("tcp://localhost:5555");

			String trackID = this.identifier.toString();

			IRI sensorOntologyIRI = KnowledgeBase.getSensorNetworkOntologyManager().getOntology()
					.getOntologyID().getOntologyIRI().orElseGet(() -> IRI.create(""));

			// Récupération du modèle de l'ontologie capteurs et rédaction du tronc commun de la requete attribut->capteurs
			Model model = KnowledgeBase.getSensorNetworkOntologyManager().getModel();
			ParameterizedSparqlString queryString = new ParameterizedSparqlString();
			queryString.setNsPrefix("rdf", RDF.getURI());
			queryString.setNsPrefix("sensor-network", sensorOntologyIRI + "#");

			Chrono timer = new Chrono();

			int n = 0;
			Set<OWLClass> previousInferedClasses = new HashSet<>();

			/*                     BOUCLE D'IDENTIFICATION                      */

			// Tant que l'objet peut exister dans l'ontologie et qu'on a pas atteint le quota maximal de mise à jour sans amélioration d'identification
			while (setSuperType() && n < MAX_USELESS_UPDATE) {
				timer.resume();
				setNeededAttributes();

				if (inferedClasses.size() < 2 || neededAttributes.isEmpty()) break;

				iteration++;

				if (DISPLAY_OBJECT) {
					System.out.println("Iteration " + iteration + ":");
					System.out.println(inferedClasses);
					System.out.println(neededAttributes);
				}

				// Si la nouvelle information ne permet pas une mise à jour
				if (inferedClasses.equals(previousInferedClasses)) n++;
				else n = 0;

				Map<IRI, Float> modes = new HashMap<>();

				// Recherche des modes permettant de récuperer les attributs listés
				try {
					queryString.setCommandText("" +
							"SELECT ?mode ?att ?accuracy ?time\nWHERE {\n\t" +
							"?mode sensor-network:observes ?attr ;\n\t" +
							"\t\t sensor-network:acquisitionTime ?time.\n" +
							"?attr rdf:type sensor-network:SensorAttribute ;\n\t" +
								  "sensor-network:hasAttribute ?att .\n\t" +
							"OPTIONAL { ?attr sensor-network:accuracy ?accuracy }.\n\t" +
							"VALUES ?att { " + String.join(" ", neededAttributes.keySet()) + " }\n" +
							"}");

					if (DEBUG) {
						System.out.println(queryString);
					}

					Query sparqlQuery = QueryFactory.create(queryString.asQuery());
					QueryExecution queryExecution = QueryExecutionFactory.create(sparqlQuery, model);
					ResultSet result = queryExecution.execSelect();

					List<String> vars = result.getResultVars();

					result.forEachRemaining(elem -> {
						if (DISPLAY_OBJECT)
							System.out.println(elem);

						IRI key = IRI.create(elem.getResource(vars.get(0)).toString());
						IRI attr = IRI.create(elem.getResource(vars.get(1)).toString());
						Literal accuracy = elem.getLiteral(vars.get(2));

						Float disc = neededAttributes.getOrDefault(IRI.create("<"+attr+">"), 1f);
						Float value = disc / elem.getLiteral(vars.get(3)).getFloat();

						Optional<Float> obs = observations.stream().filter(o -> o.type == attr).map(o -> o.uncertainty).reduce(Math::min);

						if ((modes.get(key) == null || modes.get(key) < value)
								&& (attributeObservation.get(attr) == null || attributeObservation.get(attr) < MAX_MODE_USAGE)
								&& (obs.isEmpty() || accuracy == null || obs.get() > accuracy.getFloat()))
							modes.put(key, value);
					});
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}

				if (modes.size() < 1) {
					n++;
					continue;
				}

				List<Map.Entry<IRI, Float>> list = new ArrayList<>(modes.entrySet());
				list.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));

				Map<IRI, Float> sortedModes = new LinkedHashMap<>();
				for (Map.Entry<IRI, Float> entry : list) {
					sortedModes.put(entry.getKey(), entry.getValue());
				}

				if (DISPLAY_OBJECT)
					System.out.println(sortedModes);

				if (DEBUG)
					System.out.println("[OBJECT]["+ identifier.toString().toUpperCase() + "] Modes triés: " +sortedModes);

				// Création de la requete avec la liste des modes capteurs
				Request.Builder requestBuilder = Request.newBuilder()
						.setTrackId(trackID)
						.addAllSensorModeIds(sortedModes.keySet().stream().map(IRI::toString).toList());
				Request request = requestBuilder.build();

				// Envoi de la requete au thread capteur
				if (request.isInitialized()) {
					socket.send(request.toByteArray(), 0);

					if (DEBUG)
						System.out.println("[OBJECT][" + identifier.toString().toUpperCase() + "] Requete envoyee:\n" + request);
				}
				else {
					if (DEBUG)
						System.err.println("[OBJECT][" + identifier.toString().toUpperCase() + "] Requete non initialisee...");
				}

				/*
				 * ************************************* Attente du thread capteur *************************************
				 */
				timer.stop();
				// Récupération de la réponse du thread capteur
				Answer answer = Answer.parseFrom(socket.recv(0));

				timer.resume();

				if (DISPLAY_OBJECT)
					System.out.println("[OBJECT][" + identifier.toString().toUpperCase() + "] Reponse reçue:\n" + answer);

				if (!answer.getSourceSensorModeId().equals("")) {
					if (answer.hasLatitude()) {
						IRI iri = IRI.create(sensorOntologyIRI + "#latitude");
						Float lat = answer.getLatitude().getAngle();
						Float latUncertainty = answer.getLatitude().getAngleUncertainty();

						updateProperty(iri, lat - latUncertainty, lat + latUncertainty);

						attributeObservation.merge(iri, 1, Integer::sum);
						observations.add(new Observation(new Date(answer.getTimestamp()),
								IRI.create(answer.getSourceSensorModeId()), iri, lat.toString(), latUncertainty));
					}

					if (answer.hasLongitude()) {
						IRI iri = IRI.create(sensorOntologyIRI + "#longitude");
						Float lon = answer.getLongitude().getAngle();
						Float lonUncertainty = answer.getLongitude().getAngleUncertainty();

						updateProperty(iri, lon - lonUncertainty, lon + lonUncertainty);

						attributeObservation.merge(iri, 1, Integer::sum);
						observations.add(new Observation(new Date(answer.getTimestamp()),
								IRI.create(answer.getSourceSensorModeId()), iri, lon.toString(), lonUncertainty));
					}

					if (answer.hasElevation()) {
						IRI iri = IRI.create(sensorOntologyIRI + "#elevation");
						Float alt = answer.getElevation().getDistance();
						Float altUncertainty = answer.getElevation().getDistanceUncertainty();

						updateProperty(iri, alt - altUncertainty, alt + altUncertainty);

						attributeObservation.merge(iri, 1, Integer::sum);
						observations.add(new Observation(new Date(answer.getTimestamp()),
								IRI.create(answer.getSourceSensorModeId()), iri, alt.toString(), altUncertainty));
					}

					if (answer.hasDistance()) {
						IRI iri = IRI.create(sensorOntologyIRI + "#distance");
						Float dist = answer.getDistance().getDistance();
						Float distUncertainty = answer.getDistance().getDistanceUncertainty();

						updateProperty(iri, dist - distUncertainty, dist + distUncertainty);

						attributeObservation.merge(iri, 1, Integer::sum);
						observations.add(new Observation(new Date(answer.getTimestamp()),
								IRI.create(answer.getSourceSensorModeId()), iri, dist.toString(), distUncertainty));
					}

					if (answer.hasHeight()) {
						IRI iri = IRI.create(sensorOntologyIRI + "#height");
						Float dim = answer.getHeight().getDimension();
						Float dimUncertainty = answer.getHeight().getDimensionUncertainty();

						updateProperty(iri, dim - dimUncertainty, dim + dimUncertainty);

						attributeObservation.merge(iri, 1, Integer::sum);
						observations.add(new Observation(new Date(answer.getTimestamp()),
								IRI.create(answer.getSourceSensorModeId()), iri, dim.toString(), dimUncertainty));
					}

					if (answer.hasWidth()) {
						IRI iri = IRI.create(sensorOntologyIRI + "#width");
						Float dim = answer.getWidth().getDimension();
						Float dimUncertainty = answer.getWidth().getDimensionUncertainty();

						updateProperty(iri, dim - dimUncertainty, dim + dimUncertainty);

						attributeObservation.merge(iri, 1, Integer::sum);
						observations.add(new Observation(new Date(answer.getTimestamp()),
								IRI.create(answer.getSourceSensorModeId()), iri, dim.toString(), dimUncertainty));
					}

					if (answer.hasLength()) {
						IRI iri = IRI.create(sensorOntologyIRI + "#length");
						Float dim = answer.getLength().getDimension();
						Float dimUncertainty = answer.getLength().getDimensionUncertainty();

						updateProperty(iri, dim - dimUncertainty, dim + dimUncertainty);

						attributeObservation.merge(iri, 1, Integer::sum);
						observations.add(new Observation(new Date(answer.getTimestamp()),
								IRI.create(answer.getSourceSensorModeId()), iri, dim.toString(), dimUncertainty));
					}

					if (answer.hasVelocity()) {
						IRI iri = IRI.create(sensorOntologyIRI + "#velocity");
						Float vel = answer.getVelocity().getVelocity();
						Float velUncertainty = answer.getVelocity().getVelocityUncertainty();

						updateProperty(iri, vel - velUncertainty, vel + velUncertainty);

						attributeObservation.merge(iri, 1, Integer::sum);
						observations.add(new Observation(new Date(answer.getTimestamp()),
								IRI.create(answer.getSourceSensorModeId()), iri, vel.toString(), velUncertainty));
					}

					if (answer.hasObjectType()) {
						IRI iri = IRI.create(sensorOntologyIRI + "#type");
						String type = answer.getObjectType();

						updateProperty(iri, type);

						attributeObservation.merge(iri, 1, Integer::sum);
						observations.add(new Observation(new Date(answer.getTimestamp()), IRI.create(answer.getSourceSensorModeId()), iri, answer.getObjectType()));
					}

					if (answer.hasObjectIdentification()) {
						IRI iri = IRI.create(sensorOntologyIRI + "#identification");
						String id = answer.getObjectIdentification();

						updateProperty(iri, id);

						attributeObservation.merge(iri, 1, Integer::sum);
						observations.add(new Observation(new Date(answer.getTimestamp()), IRI.create(answer.getSourceSensorModeId()), iri, answer.getObjectIdentification()));
					}

					if (answer.hasWaveformCaracterisation()) {
						IRI iri = IRI.create(sensorOntologyIRI + "#waveformCaracterisation");
						String waveformCaracterisation = answer.getWaveformCaracterisation();

						updateProperty(iri, waveformCaracterisation);

						attributeObservation.merge(iri, 1, Integer::sum);
						observations.add(new Observation(new Date(answer.getTimestamp()), IRI.create(answer.getSourceSensorModeId()), iri, answer.getObjectIdentification()));
					}

					if (answer.hasRadarCrossSection()) {
						IRI iri = IRI.create(sensorOntologyIRI + "#radarCrossSection");
						Float rcs = answer.getRadarCrossSection().getRadarCrossSection();
						Float rcsUncertainty = answer.getRadarCrossSection().getRadarCrossSectionUncertainty();

						updateProperty(iri, rcs - rcsUncertainty, rcs + rcsUncertainty);

						attributeObservation.merge(iri, 1, Integer::sum);
						observations.add(new Observation(new Date(answer.getTimestamp()), IRI.create(answer.getSourceSensorModeId()), iri, answer.getObjectIdentification()));
					}
					time += answer.getTimeTaken();
					timer.stop();
				}

				previousInferedClasses = Set.copyOf(inferedClasses);
			}
			timer.stop();
			reasoningTime += timer.getTime();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		if (DISPLAY_OBJECT) {
			System.out.println("[OBJECT][" + identifier.toString().toUpperCase() + "] " + inferedClasses);
			System.out.print("Duration of sensor usage: ");
		}

		System.out.println(Math.abs(reasoningTime) +","+ Math.abs(time));
		delete();
	}
}
