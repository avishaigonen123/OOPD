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
            buffer.add(m.toString());
        }
        return String.join("\n", buffer);
    }
}
