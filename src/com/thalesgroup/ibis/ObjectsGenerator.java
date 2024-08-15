package com.thalesgroup.ibis;

import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public enum ObjectsGenerator {

	DRONE("drone"),
	PILOT("pilot"),
	RANDOM("random"),
	TURRET("turret"),
	VEHICLE("vehicle");

	private final String filename;

	ObjectsGenerator(final String filename) {
		this.filename = filename;
	}

	@Override
	public String toString() {
		return filename;
	}

	private static String getResourcePath(String fileName) {
		final File f = new File("resources" + File.separator + "objects");
		return f.getAbsolutePath() + File.separator + fileName;
	}
	
	private static File getResource(String fileName) {
		final String completeFileName = getResourcePath(fileName);
		return new File(completeFileName);
	}
	
	@SafeVarargs
	public static Map<String,Object> generate(Map.Entry<ObjectsGenerator,Integer>... parameters) {
		Map<ObjectsGenerator,Integer> map = Map.ofEntries(parameters);
		Map<String,Object> objects = new HashMap<>();

		map.forEach((key, value) -> {
			String name = key.toString();
			File file = getResource( name + ".csv");

			for (int i = 0; i < value; i++) {
				objects.put(name + (i+1), generateObject(file));
			}
		});

		return objects;
	}
	
	private static Object generateObject(File file) {
		Map<String, Object.Value> attributes = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				String[] parts = line.split(";");
				String name = parts[0];
				String type = parts[1];
				
				if(type.equals("numeric"))
				{
					Object.Value v = new Object.Value();
					
					String min = parts[2];
					String max = parts[3];

					float v_min = Float.parseFloat(min);
					float v_max = Float.parseFloat(max);
					v.setNumeric(v_min, v_max);
					
					attributes.put(name, v);
				}
				else if (type.equals("symbolic")) {
					Object.Value v = new Object.Value();

					List<String> values = new ArrayList<>(Arrays.asList(parts[2].split(",")));

					v.setSymbolic(values);

					attributes.put(name, v);
				}
				
			}
		}
		catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
		return new Object(attributes);
	}
	

}

class Object {

	Random rand = new Random();
	private final Map<String, Value> attributes;
	
	public Object(Map<String, Value> attributes) {
		this.attributes = attributes;
	}
	
	public Value get(String attr) {
		return attributes.get(attr);
	}

	public float getNumericValue(String attr) {
		Value value = attributes.get(attr);
		assert(value.type == Value.Type.NUMERIC);



		if (Objects.equals(value.numericValue.getLeft(), value.numericValue.getRight())) {
			return value.numericValue.getLeft();
		}

		return rand.nextFloat(value.numericValue.getLeft(), value.numericValue.getRight());
	}
	
	public String getSymbolicValue(String attr) {
		Value value = attributes.get(attr);
		assert(value.type == Value.Type.SYMBOLIC);

		return value.symbolicValues.get(rand.nextInt(value.symbolicValues.size()));
	}
	
	@Override
	public String toString() {
		return "Object{" +
			"attributes=" + attributes +
			'}';
	}
	
	static class Value {
		Type type;
		Pair<Float,Float> numericValue;
		List<String> symbolicValues;
		
		public Value() {}
		
		@Override
		public String toString() {
			switch(type) {
				case NUMERIC -> {return numericValue.toString();}
				case SYMBOLIC -> {return symbolicValues.toString();}
				default -> {return type.toString();}
			}
		}

		void setNumeric(float min, float max) {
			setNumeric(Pair.of(min, max));
		}

		void setNumeric(Pair<Float, Float> value) {
			type = Type.NUMERIC;
			numericValue = value;
		}
		
		void setSymbolic(List<String> values) {
			type = Type.SYMBOLIC;
			symbolicValues = values;
		}
		
		enum Type {
			NUMERIC, SYMBOLIC
		}
	}
}
