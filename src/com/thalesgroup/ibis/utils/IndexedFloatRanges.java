package com.thalesgroup.ibis.utils;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class IndexedFloatRanges extends LinkedList<Pair<Float, Float>> {

	public void add(Float min, Float max) {
		Pair<Float, Float> pair = Pair.of(min, max);
		add(pair);
	}

	public boolean add(Pair<Float, Float> range) {
		int i;
		for(i = 0; i < this.size(); i++) {
			int comp = Float.compare(this.get(i).getLeft(), range.getLeft());
			if(comp > 0 || (comp == 0 && Float.compare(this.get(i).getRight(), range.getRight()) <= 0))
				break;
		}
		this.add(i, range);
		return true;
	}

	public boolean addAll(Collection<? extends Pair<Float, Float>> c) {
		c.forEach(this::add);
		return true;
	}

	public List<Pair<Float, Float>> getMapping() {
		List<Pair<Float, Float>> rangeList = new ArrayList<>();
		TreeSet<Float> limitsTree = new TreeSet<>();

		this.forEach(pair -> {
			if (!Objects.equals(pair.getLeft(), pair.getRight())) {
				limitsTree.add(pair.getLeft());
				limitsTree.add(pair.getRight());
			}
		});

		List<Float> limits = new ArrayList<>(limitsTree);

		for (int i = 0; i < limits.size() - 1; i++) {
			float left = limits.get(i);
			float right = limits.get(i+1);

			Pair<Float, Float> p = Pair.of(left, right);
			rangeList.add(p);
		}

		return rangeList;
	}

	public float getExpectation() {
		List<Float> expected = new ArrayList<>();

		float classCount = this.size();
		List<Pair<Float, Float>> mapping = getMapping();

		for (var range : mapping) {
			List<Pair<Float, Float>> belongToRange = this.stream()
					.filter((classe) -> (range.getRight() > classe.getLeft() && range.getLeft() < classe.getRight()))
					.toList();

			int proportion = this.size() - belongToRange.size();

			float value = belongToRange.stream()
					.map((classe) -> ((range.getRight() - range.getLeft()) / (classe.getRight() - classe.getLeft())))
					.reduce(0f, Float::sum);

			expected.add(value * proportion);
		}
		return expected.stream().reduce(0f, Float::sum) / classCount;
	}
}
