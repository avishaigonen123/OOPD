package Tar1.Airport;

import java.util.ArrayList;

import static java.util.Arrays.sort;

public class AirportUtil {
    public static void sortTransport(Comparable[] transport) {
        sort(transport);
    }

    static String reportAll(Movable[] movables) {
        ArrayList<String> buffer = new ArrayList<>();
        for (Movable m : movables) {
            String s = m.getType() + " " + m.getId() + " going from " + m.getSource() + " to " + m.getDestination() + ". Currently in " + m.getCurrentLocation();
            buffer.add(s);
        }
        return String.join("\n", buffer);
    }
}
