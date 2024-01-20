package c2_dev;
import java.util.*;

public class Location {
    public static final double MAXDISTANCE = 1000.0;
    public static final int NUMSCRAMBLES = 5;
    public static final double CONVERSION = 1 / 364320.0;

    public static void main(String[] args) {
        Random randy = new Random();
        // represents different people's exact locations with longitude and latitude
        String latitude = "49.5 54.3 54.3 54.3 54.3 54.3 54.3";
        String longitude = "49.5 54.3 54.3 54.3 54.3 54.3 54.3";
        System.out.println(scrambleCoordinate(randy, longitude, 10000));
    }

    // needs to be fenceposted!
    public static String scrambleCoordinate(Random randy, String coordinates, double distance) {
        double maxDistanceInDegrees = distance * CONVERSION;
        String scrambledCoords = "";

        String firstCoord = coordinates.substring(0, 4);
        double firstCoordinate = Double.parseDouble(firstCoord);
        double adjustment = (randy.nextDouble() - 0.5) * 2 * maxDistanceInDegrees;
        scrambledCoords += (firstCoordinate + adjustment);

        for (int i = 5; i < coordinates.length(); i += 5) {
            String currentCoord = coordinates.substring(i, i + 4);
            double coordinate = Double.parseDouble(currentCoord);
            adjustment = (randy.nextDouble() - 0.5) * 2 * maxDistanceInDegrees;
            coordinate += adjustment;
            scrambledCoords += " " + coordinate;
        }

        return scrambledCoords;
    }

    public static void dynamicUberPricing(Random randy, String longitude, String latitude, double distance) {
        String scrambledLongitude = scrambleCoordinate(randy, latitude, distance);
        String scrambledLatitude = scrambleCoordinate(randy, latitude, distance);
    }

}
