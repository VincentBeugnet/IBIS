package com.thalesgroup.ibis;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.TurtleDocumentFormat;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.SimpleIRIMapper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class KnowledgeBase {
	private static final String ONTOLOGY_DIRECTORY = "resources/";
	private static final String MISSION_ONTOLOGY_FILENAME = "missions.owl";
	private static final String OBJECTS_ONTOLOGY_FILENAME = "objects - 160.owl";
	private static final String SENSOR_NETWORK_ONTOLOGY_FILENAME = "sensor-network.owl";
	private static final IRI MISSION_ONTOLOGY_IRI = IRI.create("urn:absolute:/ontologies/missions");
	private static final IRI OBJECTS_ONTOLOGY_IRI = IRI.create("urn:absolute:/ontologies/objects");
	private static final IRI SENSOR_NETWORK_ONTOLOGY_IRI = IRI.create("urn:absolute:/ontologies/sensor-network");
	
	private static final KnowledgeBase singleton = new KnowledgeBase();
	private final OntologyManager missions;
	private final OntologyManager objects;
	private final OntologyManager sensorNetwork;
	
	private KnowledgeBase() {
		// Create ontology files mapping within manager
		// Concurrent access manager
		OWLOntologyManager ontologyManager = OWLManager.createConcurrentOWLOntologyManager();
		ontologyManager.setIRIMappers(
			Set.of(
				new SimpleIRIMapper(MISSION_ONTOLOGY_IRI,
					IRI.create(new File(ONTOLOGY_DIRECTORY + MISSION_ONTOLOGY_FILENAME))),
				new SimpleIRIMapper(OBJECTS_ONTOLOGY_IRI,
					IRI.create(new File(ONTOLOGY_DIRECTORY + OBJECTS_ONTOLOGY_FILENAME))),
				new SimpleIRIMapper(SENSOR_NETWORK_ONTOLOGY_IRI,
					IRI.create(new File(ONTOLOGY_DIRECTORY + SENSOR_NETWORK_ONTOLOGY_FILENAME)))
			)
		);
		
		try {
			// Import ontologies using Mission ontologies (as it imports the two others)
			Set<OWLOntology> importsClosure = ontologyManager.getImportsClosure(ontologyManager.loadOntology(MISSION_ONTOLOGY_IRI));
			
			// Map imported ontologies to their respective IRI
			Map<IRI, OWLOntology> ontologyMap = new HashMap<>();
			for (OWLOntology ontology : importsClosure) {
				ontology.getOntologyID().getOntologyIRI()
					.ifPresent(iri -> ontologyMap.put(iri, ontology));
			}
			
			// Assign ontologies and check their consistency
			missions = new OntologyManager(ontologyMap.get(MISSION_ONTOLOGY_IRI));
			objects = new OntologyManager(ontologyMap.get(OBJECTS_ONTOLOGY_IRI));

			Model model = ModelFactory.createDefaultModel();
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ontologyManager.saveOntology(ontologyMap.get(SENSOR_NETWORK_ONTOLOGY_IRI), new TurtleDocumentFormat(), os);
			ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
			model.read(is, null, "TURTLE");

			sensorNetwork = new OntologyManager(ontologyMap.get(SENSOR_NETWORK_ONTOLOGY_IRI), model);

		} catch (OWLOntologyCreationException | OWLOntologyStorageException e) {
			throw new RuntimeException(e);
		}
	}

	public static OntologyManager getMissionsOntologyManager() {
		return singleton.missions;
	}
	public static OntologyManager getObjectsOntologyManager() {
		return singleton.objects;
	}
	public static OntologyManager getSensorNetworkOntologyManager() {
		return singleton.sensorNetwork;
	}
}
