package c2_dev;

import java.util.*;

// Jasmine Chi
// CSE 121
// C2: Social Network
// Simple model of a social network with basic operations like
// adding, removing, listing users, and changing their status. The
// social network is represented as a string where each user and status
// are stored in a concatenated format. Each user in the network is identified
// with their name and the network maintains the current status of each user.

public class SocialNetworkOld {

    // Behavior: 
    //   - Add a new user with status to the network
    // Parameters:
    //   - String network: current state of the social network
    //   - String user: user to be added
    //   - String status: initial status of the user
    // Returns:
    //   - String: updated network with the new user added
    public static String addUser(String network, String user, String friend) {
        return network + user + ":" + friend + ",";
    }

    // Behavior: 
    //   - Counts the number of users in the network
    // Parameters:
    //   - String network: current state of the social network
    // Returns:
    //   - String: the number of users in the network
    public static int size(String network) {
        int count = 0;
        for (int i = 0; i < network.length(); i++) {
            if (network.charAt(i) == ',') {
                count++;
            }
        }
        return count;
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

            if (indexOfComma == -1) {
                return network.substring(0, indexOfKey);
            }

            return network.substring(0, indexOfKey) + network.substring(indexOfKey + indexOfComma + 1);
        }
    }

    // Behavior: 
    //   - Lists all users and their status in the network
    // Parameters:
    //   - String network: current state of the social network
    public static void listUserInfo(String network) {
        int lastComma = -1;
        for (int i = 0; i < network.length(); i++) {
            if (network.charAt(i) == ',') {
                String userStatus = network.substring(lastComma + 1, i);
                int colonIndex = userStatus.indexOf(":");
                if (colonIndex != -1) {
                    String user = userStatus.substring(0, colonIndex);
                    String status = userStatus.substring(colonIndex + 1);
                    System.out.println(user + ": " + status);
                }
                lastComma = i;
            }
        }
    }

    // Behavior: 
    //   - Change the status of a specified user
    // Parameters:
    //   - String network: current state of the social network
    //   - String user: user whose status is to be changed
    //   - String newStatus: new status to be assigned to user
    // Returns:
    //   - String: updated network with the user's status changed
    public static String changeStatus(String network, String user, String newStatus) {
        int indexOfUser = network.indexOf(user + ":");
        if (indexOfUser == -1) {
            return network; 
        }

        int endOfStatusIndex = network.indexOf(",", indexOfUser);
        if (endOfStatusIndex == -1) {
            endOfStatusIndex = network.length();
        }

        String beforeUser = network.substring(0, indexOfUser);
        String afterUser = network.substring(endOfStatusIndex);

        return beforeUser + user + ":" + newStatus + afterUser;
    }

    //------------------------------------------------------//
    //------------------------------------------------------//
    //////////////////////////////////////////////////////////
    //     NOT PLANNING ON ASKING STUDENTS TO IMPLEMENT     //
    //////////////////////////////////////////////////////////
    // Behavior: 
    //   - Gets the current status of a specified user
    // Parameters:
    //   - String network: current state of the social network
    //   - String userToFind: user whose status to find
    // Returns:
    //   - String: User's current status
    public static String getAllFriends(String network, String userToFind) {
        int lastComma = -1;

        for (int i = 0; i < network.length(); i++) {
            if (network.charAt(i) == ',') {
                String userStatus = network.substring(lastComma + 1, i);
                int colonIndex = userStatus.indexOf(":");
                String user = "";
                if (colonIndex != -1) {
                    user = userStatus.substring(0, colonIndex);
                    if (user.equals(userToFind)) {
                        return userStatus.substring(colonIndex + 1);
                    }
                }
                lastComma = i;
            }
        }
        return "User not found";
    }

    //////////////////////////////////////////////////////////
    //     NOT PLANNING ON ASKING STUDENTS TO IMPLEMENT     //
    //////////////////////////////////////////////////////////
    // Behavior: 
    //   - Gets a random user
    // Parameters:
    //   - String network: current state of the social network
    // Returns:
    //   - String: Random user name if found, otherwise "No users"
    public static String randomUser(String network) {
        Random random = new Random();
        int count = size(network); // Get the number of users
        int randomIndex = random.nextInt(count);

        int currentIndex = 0;
        int lastComma = -1;
        String selectedUser = "No users";

        for (int i = 0; i < network.length(); i++) {
            if (network.charAt(i) == ',') {
                if (currentIndex == randomIndex) {
                    int colonIndex = network.indexOf(":", lastComma + 1);
                    selectedUser = network.substring(lastComma + 1, colonIndex);
                }
                lastComma = i;
                currentIndex++;
            }
        }

        return selectedUser;
    }

    public static int calculateOutDegree(String network, String userName) {
        int outDegree = 0;
        int userIndex = network.indexOf(userName + ":");
        
        int commaIndex = network.indexOf(',', userIndex);
        if (commaIndex == -1) {
            commaIndex = network.length();
        }
        String connections = network.substring(network.indexOf(':', userIndex) + 1, commaIndex);
        for (int i = 0; i < connections.length(); i++) {
            if (connections.charAt(i) == '-') {
                outDegree++;
            }
        }
        outDegree++; 
        return outDegree;
    }

    public static int calculateInDegree(String network, String userName) {
        int inDegree = 0;
        String searchPattern = "-" + userName + "-";

        for (int i = 0; i < network.length(); i++) {
            int colonIndex = network.indexOf(':', i);
            if (colonIndex != -1) {
                int commaIndex = network.indexOf(',', colonIndex);
                if (commaIndex == -1) {
                    commaIndex = network.length();
                }
                String connections = "-" + network.substring(colonIndex + 1, commaIndex) + "-";

                if (connections.contains(searchPattern)) {
                    inDegree++;
                }

                if (commaIndex < network.length()) {
                    i = commaIndex;
                } else {
                    i = network.length();
                }
            }
        }

        return inDegree;
    }

    public static String addFriend(String network, String user, String newFriend) {
        int userIndex = network.indexOf(user + ":");
        int endOfUser = network.indexOf(',', userIndex);
        if (endOfUser == -1) {
            endOfUser = network.length();
        }

        String beforeUser = network.substring(0, userIndex);
        String afterUser = network.substring(endOfUser);

        String userSection = network.substring(userIndex, endOfUser);
        if (userSection.endsWith("-")) {
            return beforeUser + userSection + newFriend + afterUser;
        } else {
            return beforeUser + userSection + "-" + newFriend + afterUser;
        }
    }
}
