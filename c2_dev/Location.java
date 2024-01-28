package c2_dev;
import java.util.*;

public class Location {
    public static final double MAXDISTANCE = 1000.0;
   // public static final int NUMSCRAMBLES = 5;
    public static final double CONVERSION = 1 / 364320.0;
    public static final double BASE_FARE = 2.0;
    public static final double PER_MILE_CHARGE = 1.5;

    public static void main(String[] args) {
        Random randy = new Random();
        // represents different people's exact locations with longitude and latitude
        String latitude = "49.543210 54.343210 54.343210 54.343210";
        String longitude = "49.543210 54.343210 54.343210 54.343210";
        double baseDistance = 5.0; 
        dynamicUberPricing(randy, longitude, latitude, baseDistance);
    }

    public static double scrambleCoordinate(Random randy, String coordinate) {
        double maxDistanceInDegrees = MAXDISTANCE * CONVERSION;
        double coord = Double.parseDouble(coordinate);
        double adjustment = (randy.nextDouble() - 0.5) * 2 * maxDistanceInDegrees;
        return coord + adjustment;
    }

    public static void dynamicUberPricing(Random randy, String longitude, String latitude, double baseDistance) {
        int coordinateLength = 9; // 8 digits for the decimal + 1 space

        // Ensure loop doesn't exceed string length
        for (int i = 0; i <= longitude.length() - coordinateLength; i += coordinateLength) {
            // Adjust substring length to exclude trailing space
            String startLon = longitude.substring(i, i + 8);
            String startLat = latitude.substring(i, i + 8);
            
            // Ensure substring indices do not exceed string length
            int nextIndex = Math.min(i + coordinateLength, longitude.length());
            String endLon = longitude.substring(nextIndex, Math.min(nextIndex + 8, longitude.length()));
            String endLat = latitude.substring(nextIndex, Math.min(nextIndex + 8, latitude.length()));

            double scrambledStartLon = scrambleCoordinate(randy, startLon);
            double scrambledStartLat = scrambleCoordinate(randy, startLat);
            double scrambledEndLon = scrambleCoordinate(randy, endLon);
            double scrambledEndLat = scrambleCoordinate(randy, endLat);

            double distance = calculateHaversineDistance(scrambledStartLat, scrambledStartLon, scrambledEndLat, scrambledEndLon);
            double fare = BASE_FARE + (PER_MILE_CHARGE * distance);

            System.out.println("Ride from (" + scrambledStartLat + ", " + scrambledStartLon +
                               ") to (" + scrambledEndLat + ", " + scrambledEndLon + ") costs: $" + fare);
        }
    }

    // given method they should just call in dynamicUberPricing
    public static double calculateHaversineDistance(double lat1, double lon1, double lat2, double lon2) {
        int R = 6371; // radius of the earth in km
        double dLat = (lat2 - lat1) * (Math.PI / 180);
        double dLon = (lon2 - lon1) * (Math.PI / 180);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(lat1 * (Math.PI / 180)) * Math.cos(lat2 * (Math.PI / 180)) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // distance in km
    }
}
