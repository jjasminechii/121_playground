package c2_dev;

import java.util.*;
public class LocationServices {
    public static final double MAXDISTANCE = 1000.0;
    public static final int NUMSCRAMBLES = 5;
    public static final double CONVERSION = 1 / 364320.0;
    public static final double BASE_FARE = 2.0; // base fare for the ride
    public static final double PER_MILE_CHARGE = 1.5; // carge per mile
    public static final double STOP_CHARGE = 0.5; // additional charge per stop
    public static final int SCENARIOS_PER_STOP = 3; // num scenarios to simulate at each stop

    public static final double MAX_PROXIMITY_RANGE = 0.05; // maximum range to consider for proximity (degrees)
    public static final int NUM_POIS = 3; // number of points of interest

    public static void main(String[] args) {
        Random randy = new Random();
        double longitude = 37.7749;
        double latitude = 40.0;
        int stops = 3; // number of stops (maybe class constant this during testing)
        double distance = 5.0; // Total distance in miles

        double cost = uberRideCost(distance, stops, longitude, latitude, randy);
        System.out.println("Total ride cost: $" + cost);
        double propertyLatitude = 40.7128;
        double propertyLongitude = -74.0060;
        int proximityScore = evaluatePropertyProximity(propertyLatitude, propertyLongitude, randy);
        System.out.println("Property Proximity Score: " + proximityScore);
    }

    public static double scrambleCoordinate(Random randy, double coord) {
        double maxDistanceInDegrees = MAXDISTANCE * CONVERSION;
        for (int i = 0; i < NUMSCRAMBLES; i++) {
            double adjustment = (randy.nextDouble() - 0.5) * 2 * maxDistanceInDegrees;
            coord += adjustment;
        }
        return coord;
    }

    public static double uberRideCost(double distance, int stops, double longitude, double latitude, Random randy) {
        double totalCost = BASE_FARE;
        double currentLong = longitude;
        double currentLat = latitude;

        for (int i = 0; i < stops; i++) {
            currentLong = scrambleCoordinate(randy, currentLong);
            currentLat = scrambleCoordinate(randy, currentLat);
            totalCost += STOP_CHARGE;
        }

        totalCost += distance * PER_MILE_CHARGE;
        return totalCost;
    }

    // nested version of uber
    public static double uberRideCost2(double distance, int stops, double longitude, double latitude, Random randy) {
        double totalCost = BASE_FARE;
        double currentLong = longitude;
        double currentLat = latitude;

        for (int i = 0; i < stops; i++) {
            for (int j = 0; j < SCENARIOS_PER_STOP; j++) {
                currentLong = scrambleCoordinate(randy, currentLong);
                currentLat = scrambleCoordinate(randy, currentLat);
            }
            totalCost += STOP_CHARGE;
        }

        totalCost += distance * PER_MILE_CHARGE;
        return totalCost;
    }

    // airbnb dupe shooot i think this is too hard
    public static int evaluatePropertyProximity(double propertyLat, double propertyLong, Random randy) {
        int proximityScore = 0;

        for (int i = 0; i < NUM_POIS; i++) {
            // Simulate coordinates of a point of interest
            double poiLat = propertyLat + (randy.nextDouble() - 0.5) * MAX_PROXIMITY_RANGE;
            double poiLong = propertyLong + (randy.nextDouble() - 0.5) * MAX_PROXIMITY_RANGE;

            // Nested loop to simulate checking proximity in various scenarios
            for (int j = 0; j < 10; j++) { // 10 different scenarios
                double adjustedLat = propertyLat + (randy.nextDouble() - 0.5) * 0.01;
                double adjustedLong = propertyLong + (randy.nextDouble() - 0.5) * 0.01;

                if (isWithinProximity(adjustedLat, adjustedLong, poiLat, poiLong)) {
                    proximityScore++;
                }
            }
        }

        return proximityScore;
    }
    // checks that we're within the proximity [given method!!]
    public static boolean isWithinProximity(double lat1, double long1, double lat2, double long2) {
        return Math.sqrt(Math.pow(lat2 - lat1, 2) + Math.pow(long2 - long1, 2)) < MAX_PROXIMITY_RANGE;
    }
}
