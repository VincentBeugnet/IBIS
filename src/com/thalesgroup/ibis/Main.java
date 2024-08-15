package com.thalesgroup.ibis;

import org.semanticweb.owlapi.model.IRI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static com.thalesgroup.ibis.Constants.DISPLAY_OBJECT;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {
		ArrayList<Thread> threads = new ArrayList<>();

		// Initialisation des objets
		Map<String,Object> objects = ObjectsGenerator.generate(
				Map.entry(ObjectsGenerator.DRONE, 100),
				Map.entry(ObjectsGenerator.PILOT, 100),
				Map.entry(ObjectsGenerator.TURRET, 100),
				Map.entry(ObjectsGenerator.VEHICLE, 100),
				Map.entry(ObjectsGenerator.RANDOM, 100)
		);

		// Initialisation du gestionnaire de capteurs
		SensorManager sensorManager = new SensorManager(objects);
		Thread sensorThread = new Thread(sensorManager);
		sensorThread.start();

		// Detection et tentative d'identification des objets
		objects.forEach((key, value) -> {
			if (DISPLAY_OBJECT) System.out.println("Apparition de l'objet " + key + ": " + value);
			Track track = new Track(IRI.create(key), false);
			Thread trackThread = new Thread(track);
			threads.add(trackThread);
			trackThread.start();
		});
		threads.forEach(thread -> {
			try {
				thread.join();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		});

		System.out.println("\n\n");
		threads.clear();

		objects.forEach((key, value) -> {
			if (DISPLAY_OBJECT) System.out.println("Apparition de l'objet " + key + ": " + value);
			Track track = new Track(IRI.create(key), true);
			Thread trackThread = new Thread(track);
			threads.add(trackThread);
			trackThread.start();
		});
		threads.forEach(thread -> {
			try {
				thread.join();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		});
	}
}