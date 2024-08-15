package com.thalesgroup.ibis;

import org.semanticweb.owlapi.model.IRI;

import java.util.Date;

public class Observation {
	Date timestamp;
	IRI sourceModesID;
	IRI type;
	String data;
	Float uncertainty;
	
	Observation(Date timestamp, IRI sourceModesID, IRI type, String data, Float uncertainty) {
		this.timestamp = timestamp;
		this.sourceModesID = sourceModesID;
		this.type = type;
		this.data = data;
		this.uncertainty = uncertainty;
	}
	
	Observation(Date timestamp, IRI sourceModesID, IRI type, String data) {
		this(timestamp, sourceModesID, type, data, null);
	}
	
}
