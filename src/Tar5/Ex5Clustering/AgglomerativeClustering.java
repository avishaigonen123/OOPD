package Tar5.Ex5Clustering;

import Tar5.Ex5Clustering.Clusterable;
import Tar5.Ex5Clustering.Clustering;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static java.lang.Math.min;


public class AgglomerativeClustering <T extends Clusterable<T>> implements Clustering<T> {
	double threshold;
	public AgglomerativeClustering(double threshold) {
		this.threshold = threshold;
	}
	public Set<Set<T>> clusterSet(Set<T> elements) {
		Set<Set<T>> clusters = new HashSet<>();
		for (T elem: elements) {
			HashSet<T> temp = new HashSet<T>();
			temp.add(elem);
			clusters.add(temp);
		}
		while(clusters.size() != 1){
			double minVal = Double.MAX_VALUE;
			Set<T> minC1 = Set.of();
			Set<T> minC2 = Set.of();
			for (Set<T> c1:clusters) {
				for (Set<T> c2:clusters){
					if(!c1.equals(c2) && minVal>distance(c1,c2))
					{
						minVal = distance(c1, c2);
						minC1 = c1;
						minC2 = c2;
					}
				}
			}
			if(minVal > threshold)
				return clusters;
			clusters.remove(minC2);
			clusters.remove(minC1);
			minC1.addAll(minC2);
			clusters.add(minC1);
		}
		return clusters;
	}

	private double distance(Set<T> c1, Set<T> c2) {
		double min_distance = Double.MAX_VALUE;
		for(T i1:c1){
			for(T i2:c2){
				min_distance = min(min_distance, i1.distance(i2));
			}
		}
		return min_distance;
	}
}
/*Cluster(items, threshold)
clusters = A set of singletons, where each item starts in its own cluster
	while (clusters is not of size 1)
		find the two closest clusters, c1,c2 from clusters
		if (distance(c1, c2) > threshold) return clusters;
		replace c1,c2 in clusters with the union of c1,c2
	return clusters
*/