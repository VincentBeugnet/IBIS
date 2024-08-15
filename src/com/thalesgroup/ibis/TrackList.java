package com.thalesgroup.ibis;

import org.semanticweb.owlapi.model.IRI;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TrackList {
    private static final TrackList singleton = new TrackList();

    private final Map<IRI, Track> tracks;

    private TrackList() {
        this.tracks = new HashMap<>();
    }

    public static Collection<Track> getTracks() {
        return singleton.tracks.values();
    }

    public static Track get(IRI identifier) {
        return singleton.tracks.get(identifier);
    }

    public static void add(Track track) {
        singleton.tracks.put(track.getIdentifier(), track);
    }

    public static void remove(Track track) {
        singleton.tracks.remove(track.getIdentifier());
    }
}
