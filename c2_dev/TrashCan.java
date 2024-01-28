package c2_dev;
import java.util.*;

public class TrashCan {
    public static final double MAXDISTANCE = 1000.0;
    public static final int NUMSCRAMBLES = 5;
    public static final double CONVERSION = 1 / 364320.0;
    public static final double BASE_FARE = 2.0;
    public static final double PER_MILE_CHARGE = 1.5;

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

    public static double scrambleCoordinate1(Random randy, String coordinate, double distance) {
        double maxDistanceInDegrees = distance * CONVERSION;
        double coord = Double.parseDouble(coordinate);
        double adjustment = (randy.nextDouble() - 0.5) * 2 * maxDistanceInDegrees;
        return coord + adjustment;
    }

    public static void dynamicUberPricing1(Random randy, String longitude, String latitude, double baseDistance) {
        for (int i = 0; i < longitude.length(); i += 5) {
            String currentLon = longitude.substring(i, Math.min(i + 4, longitude.length()));
            String currentLat = latitude.substring(i, Math.min(i + 4, latitude.length()));

            double scrambledLon = scrambleCoordinate1(randy, currentLon, MAXDISTANCE);
            double scrambledLat = scrambleCoordinate1(randy, currentLat, MAXDISTANCE);

            double distance = baseDistance + Math.abs(scrambledLat - scrambledLon); // Simplified distance calculation
            double fare = BASE_FARE + (PER_MILE_CHARGE * distance); // Fare calculation

            System.out.println("Ride from (" + scrambledLat + ", " + scrambledLon + ") costs: $" + fare);
        }
    }

    public static double calculateHaversineDistance(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371.0; // Radius of the Earth in kilometers

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                 + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                 * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c; // convert to kilometers

        return distance; // return distance
    }

            // for (int i = 0; i < longitude.length() - 5; i += 5) {
        //     String startLon = longitude.substring(i, i + 4);
        //     String startLat = latitude.substring(i, i + 4);
        //     String endLon = longitude.substring(i + 5, Math.min(i + 9, longitude.length()));
        //     String endLat = latitude.substring(i + 5, Math.min(i + 9, latitude.length()));

        //     double scrambledStartLon = scrambleCoordinate(randy, startLon, MAXDISTANCE);
        //     double scrambledStartLat = scrambleCoordinate(randy, startLat, MAXDISTANCE);
        //     double scrambledEndLon = scrambleCoordinate(randy, endLon, MAXDISTANCE);
        //     double scrambledEndLat = scrambleCoordinate(randy, endLat, MAXDISTANCE);

        //     double distance = calculateHaversineDistance(scrambledStartLat, scrambledStartLon, scrambledEndLat, scrambledEndLon);
        //     double fare = BASE_FARE + (PER_MILE_CHARGE * distance);

        //     System.out.println("Ride from (" + scrambledStartLat + ", " + scrambledStartLon +
        //                        ") to (" + scrambledEndLat + ", " + scrambledEndLon + ") costs: $" + fare);
        // }
            // Loop until the second-to-last coordinate
}
