package Tar5.Ex5Clustering;

import java.util.Set;

public interface Clustering <T extends Clusterable<T>>{
	public Set<Set<T>> clusterSet (Set<T> elements);
}
