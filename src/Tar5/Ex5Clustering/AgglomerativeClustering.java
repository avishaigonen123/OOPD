import java.util.Set;


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
}
