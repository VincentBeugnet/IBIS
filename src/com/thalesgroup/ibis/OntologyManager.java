package com.thalesgroup.ibis;


import org.apache.jena.rdf.model.Model;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.profiles.OWL2DLProfile;
import org.semanticweb.owlapi.profiles.OWLProfileReport;
import org.semanticweb.owlapi.profiles.OWLProfileViolation;

public class OntologyManager {
	private final OWLOntology ontology;
	
	private Model model = null;
	
	public OntologyManager(OWLOntology ontology) {
		checkOWL2DLProfile(ontology);
		this.ontology = ontology;
	}
	
	public OntologyManager(OWLOntology ontology, Model model) {
		this(ontology);
		this.model = model;
	}
	
	public OWLOntology getOntology() {
		return ontology;
	}
	
	public Model getModel() {
		return model;
	}
	
	private void checkOWL2DLProfile(OWLOntology ontology) {
		OWL2DLProfile profile = new OWL2DLProfile();
		OWLProfileReport report = profile.checkOntology(ontology);
		for (OWLProfileViolation v : report.getViolations()) {
			System.out.println(v);
		}
	}
}
