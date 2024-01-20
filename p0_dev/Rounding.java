public class Rounding {
    public static void main(String[] args) {
        // our decimal number!
        double numberOfHours = 121.3; // number is required

        // convert to 24-hour format first without using '%'
        int divisor = 24;
        int fullDays = (int) numberOfHours / divisor;
        double hoursIn24Format = numberOfHours - (fullDays * divisor);

        // round at the end
        int roundedHours = (int) (hoursIn24Format + 0.5);

        // output results!
        System.out.println("Donatello's original hours: " + numberOfHours);
        System.out.println("Donatello's hours in 24-hour format before rounding: " + hoursIn24Format);
        System.out.println("Donatello's rounded hours in 24-hour format: " + roundedHours + ":00");
    }
}
