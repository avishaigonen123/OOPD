package Tar5.Ex5Clustering;

import Tar5.Ex5Clustering.Clusterable;
import Tar5.Ex5Clustering.Clustering;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.min;


public class AgglomerativeClustering <T extends Clusterable<T>> implements Clustering<T> {
	double threshold;
	public AgglomerativeClustering(double threshold) {
		this.threshold = threshold;
	}
	public Set<Set<T>> clusterSet(Set<T> elements) {
		Set<Set<T>> clusters = elements.stream()
				.filter(elem -> elem.distance(elem) != -1)
				.map(elem -> new HashSet<>(Arrays.asList(elem)))
				.collect(Collectors.toCollection(HashSet::new));

		while(clusters.size() != 1){

			Map.Entry<Set<T>, Set<T>> min_entry = clusters.stream()
					.flatMap(cluster -> clusters.stream().
							map(cluster2 -> new AbstractMap.SimpleEntry<>(cluster, cluster2)))
					.distinct()
					.filter(entry -> (!entry.getKey().equals(entry.getValue())))
					.min(Comparator.comparingDouble(entry -> distance(entry.getKey(), entry.getValue())))
					.orElse(null);

			if(min_entry == null)
				return null;

			Set<T> minC1 = min_entry.getKey();
			Set<T> minC2 = min_entry.getValue();
			double minVal = distance(minC1, minC2);

			if(minVal > threshold)
				return clusters;

			clusters.remove(minC2);
			clusters.remove(minC1);
			minC1.addAll(minC2);
			clusters.add(minC1);
		}
		return clusters;
	}

	private double distance(T c1, Set<T> c2) {
		return c2.stream()
				.map(i -> i.distance(c1))
				.min(Double::compare).orElse(Double.MAX_VALUE);
	}

	private double distance(Set<T> c1, Set<T> c2) {
		return c1.stream()
				.map(i -> distance(i, c2))
				.min(Double::compare).orElse(Double.MAX_VALUE);
	}
}
/*Cluster(items, threshold)
clusters = A set of singletons, where each item starts in its own cluster
	while (clusters is not of size 1)
		find the two closest clusters, c1,c2 from clusters
		if (distance(c1, c2) > threshold) return clusters;
		replace c1,c2 in clusters with the union of c1,c2
	return clusters
*/