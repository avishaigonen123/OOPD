import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.sqrt;

public class TwoDPoint implements Clusterable<TwoDPoint>{
	double x;
	double y;
	public TwoDPoint(String str){
		// TODO: Complete
	}
	public TwoDPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public double distance(TwoDPoint other) {
		return sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y)); // sqrt((x1-x2)^2 + (y1-y2)^2)
	}

	public static Set<TwoDPoint> readClusterableSet(String path) throws IOException{
	 	return Files.lines(Path.of(path)).map(TwoDPoint::new).collect(Collectors.toSet());
	}

	@Override
	public String toString() {
		return x + "," + y;
	}

	@Override
	public boolean equals(Object other) {
		TwoDPoint otherP = (TwoDPoint) other;
		return (otherP.x == x && otherP.y == y);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}
