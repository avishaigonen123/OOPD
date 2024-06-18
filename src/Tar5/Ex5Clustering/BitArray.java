package Tar5.Ex5Clustering;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.min;

public class BitArray implements Clusterable<BitArray>{
	private ArrayList<Boolean> bits;

	public BitArray(String str){
		bits = Stream.of(str.split(",")).map(Boolean::valueOf).collect(Collectors.toCollection(ArrayList::new));
	}
	public BitArray(boolean[] bits){
		for(boolean bit:bits)
		{
			this.bits.add(bit);
		}
	}

	public static Set<BitArray> readClusterableSet(String path) throws IOException {
		return Files.lines(Path.of(path)).map(BitArray::new).collect(Collectors.toSet());
	}

	@Override
	public double distance(BitArray other) {
		int counter = 0;
		int size = min(this.bits.size(), other.bits.size());
		for (int i=0;i<size; i++){
			if (this.bits.get(i) != other.bits.get(i))
				counter++;
		}
		return counter;
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
