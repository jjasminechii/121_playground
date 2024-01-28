package c2_dev;
 // Jasmine Chi
// CSE 121
// C2: Social Network
// Simple model of a social network with basic operations like
// adding, removing, listing users, and changing their status. The
// social network is represented as a string where each user and status
// are stored in a concatenated format. Each user in the network is identified
// with their name and the network maintains the current status of each user.
public class SocialNetwork {
    public static void main(String[] args) {
        String network = "";
        network = addUser(network, "Jasmine", "Nic-Jessie-Belle");
        network = addUser(network, "Jessie", "Jasmine");
        network = addUser(network, "Belle", "Sarah-Jasmine-Maddie");
        network = addUser(network, "Miya", "Matt-Elba");

        System.out.println("network status:");
        System.out.println(network);
        System.out.println("total users: " + networkSize(network));

        network = removeUser(network, "Jasmine");
        network = addUser(network, "Amy", "social-computing");
        System.out.println();
        System.out.println("updated network status:");
        System.out.println(network);
        System.out.println("total users: " + networkSize(network));
    }
    
    /**
     * Adds a new user along with their friends to the social network.
     * 
     * Example:
     * {@code addUser(",Miya:Gumball,Donatello:Brett-Elba-Matt,", "Jasmine", "Jasmine:Nic-Trey-Hannah-Nicole") }
     * would return ",Miya:Gumball,Donatello:Brett-Elba-Matt,Jasmine:Jasmine:Nic-Trey-Hannah-Nicole,".
     * 
     * @param network The current state of the social network.
     * @param user The user to be added.
     * @param friends The friends of the user, formatted as "friend1-friend2-friend3..."
     * @return Updated network string including the new user and their friends.
     */
    public static String addUser(String network, String user, String friends) {
        if (network.isEmpty()) {
            return "," + user + ":" + friends + ",";
        } else {
            return network + user + ":" + friends + ",";
        }
    }

    /**
     * Counts the number of users in the social network.
     * 
     * Example:
     * {@code networkSize(",Miya:Gumball,Donatello:Brett-Elba-Matt,Jasmine:Nic-Trey-Hannah-Nicole,") }
     * would return 3.
     * 
     * @param network The current state of the social network.
     * @return The number of users in the network.
     */
    public static int networkSize(String network) {
        int count = 0;
        for (int i = 0; i < network.length(); i++) {
            if (network.charAt(i) == ',') {
                count++;
            }
        }
        return count - 1;
    }

    /**
     * Removes a specified user from the social network.
     * 
     * Example:
     * {@code removeUser(",Miya:Gumball,Donatello:Brett-Elba-Matt,Jasmine:Nic-Trey-Hannah-Nicole,", "Miya") }
     * would return ",Donatello:Brett-Elba-Matt,Jasmine:Nic-Trey-Hannah-Nicole,".
     * 
     * @param network The current state of the social network.
     * @param userToDelete The name of the user to be removed.
     * @return Updated network string with the specified user removed.
     */
    public static String removeUser(String network, String userToDelete) {
        if (!network.contains(userToDelete + ":")) {
            return network;
        } else {
            int indexOfKey = network.indexOf(userToDelete + ":");
            String trimmedNetwork = network.substring(indexOfKey);
            int indexOfComma = trimmedNetwork.indexOf(",");
            return network.substring(0, indexOfKey) + network.substring(indexOfKey + indexOfComma + 1);
        }
    }
}
