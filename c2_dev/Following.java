// Jasmine Chi
// CSE 121
// C2: Social Network
// Simple model of a social network with basic operations like
// adding, removing, and finding the size of the network. The
// social network is represented as a string where each user and their friends
// are stored in a concatenated format. Each user in the network is identified
// with their name and the network maintains the current friends of each user.
import java.util.*;
public class SocialNetwork {
    public static void main(String[] args) {
        String network = "";
        Random randy = new Random();
        network = addUser(network, "Jasmine", "Jessie-Belle");
        network = addUser(network, "Jessie", "Jasmine");
        network = addUser(network, "Belle", "Sarah-Jasmine-Maddie");
        network = addUser(network, "Miya", "Matt-Elba");

        System.out.println("network status:");
        System.out.println(network);
        // System.out.println("total users: " + networkSize(network));

        network = removeUser(network, "Jasmine");
        network = addUser(network, "Amy", "social-computing");

        System.out.println("updated network status:");
        System.out.println(network);
        // System.out.println("total users: " + networkSize(network));

        System.out.println();
        System.out.println(countFriends(network, "Belle"));
        System.out.println("Random user and their friends are " + randomUser(network, randy));
        listUserInfo(network);
    }
    
    // Behavior: 
    //   - Add a new user with their friends to the network
    // Parameters:
    //   - String network: current state of the social network
    //   - String user: user to be added
    //   - String friends: friends of the user
    // Returns:
    //   - String: updated network with the new user added
    public static String addUser(String network, String user, String friends) {
        if (network.isEmpty()) {
            return "," + user + ":" + friends + ",";
        } else {
            return network + user + ":" + friends + ",";
        }
    }

    // Behavior: 
    //   - Counts the number of users in the network
    // Parameters:
    //   - String network: current state of the social network
    // Returns:
    //   - String: the number of users in the network
    public static int networkSize(String network) {
        int count = 0;
        for (int i = 0; i < network.length(); i++) {
            if (network.charAt(i) == ',') {
                count++;
            }
        }
        return count - 1;
    }

    // Behavior: 
    //   - Remove a specified user from the network
    // Parameters:
    //   - String network: current state of the social network
    //   - String userToDelete: the user to be removed
    // Returns:
    //   - String: updated network with the specified user removed
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

    ////////////////////////////////////////////
    ///////// Extension: 3 Options /////////////
    ////////////////////////////////////////////
    // Option 1: countFriends
    // Finds number of friends of a user
    // Parameters:
    //   - String network: current state of the network
    //   - String userName: user to find the number of friends indexOf
    // Return:
    //   - int: number of friends of specified user
    public static int countFriends(String network, String userName) {
        int outDegree = 0;
        String userPattern = userName + ":";
        int userIndex = network.indexOf(userPattern);
        if (userIndex == -1) {
            return 0; 
        }
        userIndex += userPattern.length();
        int commaCount = 0;
        for (int i = 0; i < userIndex - userPattern.length(); i++) {
            if (network.charAt(i) == ',') {
                commaCount++;
            }
        }
        int nextCommaIndex = indexOfChar(network, commaCount);
        for (int i = userIndex; i < nextCommaIndex; i++) {
            if (network.charAt(i) == '-' || i == nextCommaIndex - 1) {
                outDegree++;
            }
        }
        return outDegree;
    }
    

    // Option 2: listUserInfo
    // Behavior: 
    //   - Lists and print all users and their friends in the network
    // Parameters:
    //   - String network: current state of the social network
    public static void listUserInfo(String network) {
        for (int i = 0; i < networkSize(network); i++) {
            String curUserAndFriend = network.substring(indexOfChar(network, i) + 1, indexOfChar(network, i + 1));
            int index = curUserAndFriend.indexOf(':');
            System.out.println(curUserAndFriend.substring(0, index + 1) + " " + curUserAndFriend.substring(index + 1));
        }
    }

    // Option 3: Random User
    // Behavior: 
    //   - Lists and print all users and their friends in the network
    // Parameters:
    //   - String network: current state of the social network
    public static String randomUser(String network, Random randy) {
        int count = networkSize(network);
        if (count == 0) {
            return "No users";
        }
        int randomIndex = randy.nextInt(count);
        int startCommaIndex = indexOfChar(network, randomIndex);
        int endCommaIndex = indexOfChar(network, randomIndex + 1);
        String userEntry = network.substring(startCommaIndex + 1, endCommaIndex);
        int colonIndex = userEntry.indexOf(':');
        String selectedUser = userEntry.substring(0, colonIndex + 1) + " " + userEntry.substring(colonIndex + 1);
        return selectedUser;
    }

    // [REQUIRED METHOD FOR ALL EXTENSION]
    // Behavior: 
    //   - Finds the index of a specific occurence of a character
    // Parameters:
    //   - String network: current state of the social network
    //   - int nthOccurrence: specific occurence of the character that 
    //     we're interested in!
    //   - char character: character that we're interested in!
    // Returns:
    //   - int: index where the comma occurs
    public static int indexOfChar(String network, int nthOccurrence) {
        int occurrenceCount = 0;
        for (int i = 0; i < network.length(); i++) {
            if (network.charAt(i) == ',') {
                if (occurrenceCount == nthOccurrence) {
                    return i;
                }
                occurrenceCount++;
            }
        }
        return -1;
    }
}
