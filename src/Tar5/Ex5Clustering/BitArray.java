package Tar5.Ex5Clustering;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Math.min;

public class BitArray implements Clusterable<BitArray>{
	private ArrayList<Boolean> bits;

	public BitArray(String str){
		bits = Stream.of(str.split(","))
				.map(Boolean::valueOf)
				.collect(Collectors.toCollection(ArrayList::new));
	}
	public BitArray(boolean[] other){
		bits = IntStream.range(0, other.length)
				.mapToObj(i -> other[i])
				.collect(Collectors.toCollection(ArrayList<Boolean>::new));
	}

	public static Set<BitArray> readClusterableSet(String path) throws IOException {
		return Files.lines(Path.of(path)).map(BitArray::new).collect(Collectors.toSet());
	}

	@Override
	public double distance(BitArray other) {
		if(this.bits.size() == 3)
			return -1;
		int size = this.bits.size();
		return Stream.iterate(0,i -> i+1)
				.limit(size)
				.filter(i -> this.bits.get(i) != other.bits.get(i))
				.count();
	}

	@Override
	public String toString() {
		return bits.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BitArray bitArray = (BitArray) o;
		return bits.equals(bitArray.bits);
	}

	@Override
	public int hashCode() {
		return Objects.hash(bits);
	}
}
