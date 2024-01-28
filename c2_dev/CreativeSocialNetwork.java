package c2_dev;

import java.util.*;

public class CreativeSocialNetwork {

    // private constructor lol
    private CreativeSocialNetwork() {
        throw new UnsupportedOperationException("nope");
    }
    /**
     * Adds a new user along with their friends to the social network.
     * 
     * {@snippet :
     * // Example usage of addUser
     * String network = ",Miya:Gumball,Donatello:Brett-Elba-Matt,";
     * String updatedNetwork = addUser(network, "Jasmine", "Nic-Trey-Hannah-Nicole");
     * System.out.println(updatedNetwork); // Output: ",Miya:Gumball,Donatello:Brett-Elba-Matt,Jasmine:Nic-Trey-Hannah-Nicole,"
     * }
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
     * {@snippet :
     * // Example usage of networkSize
     * String network = ",Miya:Gumball,Donatello:Brett-Elba-Matt,Jasmine:Nic-Trey-Hannah-Nicole,";
     * int userCount = networkSize(network);
     * System.out.println(userCount); // Output: 3
     * }
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
     * {@snippet :
     * // Example usage of removeUser
     * String network = ",Miya:Gumball,Donatello:Brett-Elba-Matt,Jasmine:Nic-Trey-Hannah-Nicole,";
     * String updatedNetwork = removeUser(network, "Miya");
     * System.out.println(updatedNetwork); // Output: ",Donatello:Brett-Elba-Matt,Jasmine:Nic-Trey-Hannah-Nicole,"
     * }
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

    /**
     * Finds the index of a specific occurrence of a comma in the social network string.
     * This helps with identifying the positions of different users or entries, separated 
     * by commas!
     * 
     * {@snippet :
     * // Example usage of indexOfComma
     * String network = ",Miya:Gumball,Donatello:Brett-Elba-Matt,Jasmine:Nic-Trey-Hannah-Nicole,";
     * int indexOfSecondComma = indexOfComma(network, 1);
     * System.out.println(indexOfSecondComma); // Output: 13
     * }
     * 
     * @param network The current state of the social network.
     * @param nthOccurrence The specific occurrence of the comma to find. The first
     *        occurrence is denoted by 0, the second by 1, and so on.
     * @return The index where the nth occurrence of a comma is found, or -1 if it does not exist.
     */
    public static int indexOfComma(String network, int nthOccurrence) {
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

    /**
     * Selects and returns a random user and their friends from the network.
     * 
     * {@snippet :
     * // Example usage of randomUser
     * String network = ",Miya:Gumball,Donatello:Brett-Elba-Matt,Jasmine:Nic-Trey-Hannah-Nicole,";
     * Random randy = new Random();
     * String randomUser = randomUser(network, randy);
     * System.out.println(randomUser); // Output might be "Donatello: Brett-Elba-Matt"
     * }
     * 
     * @param network The current state of the social network.
     * @param randy A Random object used to generate a random index.
     * @return A string representing a random user and their friends.
     */
    public static String randomUser(String network, Random randy) {
        int count = networkSize(network);
        if (count == 0) {
            return "No users";
        }
        int randomIndex = randy.nextInt(count);
        int startCommaIndex = indexOfComma(network, randomIndex);
        int endCommaIndex = indexOfComma(network, randomIndex + 1);
        String userEntry = network.substring(startCommaIndex + 1, endCommaIndex);
        int colonIndex = userEntry.indexOf(':');
        String selectedUser = userEntry.substring(0, colonIndex + 1) + " " + userEntry.substring(colonIndex + 1);
        return selectedUser;
    }

    /**
     * Lists all users and their friends in the network.
     * 
     * {@snippet :
     * // Example usage of listUserInfo
     * String network = ",Miya:Gumball,Donatello:Brett-Elba-Matt,Jasmine:Nic-Trey-Hannah-Nicole,";
     * listUserInfo(network); // This would print each user and their friends in the network
     * }
     * 
     * @param network The current state of the social network.
     */
    public static void listUserInfo(String network) {
        for (int i = 0; i < networkSize(network); i++) {
            String curUserAndFriend = network.substring(indexOfComma(network, i) + 1, indexOfComma(network, i + 1));
            int index = curUserAndFriend.indexOf(':');
            System.out.println(curUserAndFriend.substring(0, index + 1) + " " + curUserAndFriend.substring(index + 1));
        }
    }

    /**
     * Counts the number of friends of a specific user in the network.
     * 
     * {@snippet :
     * // Example usage of countFriends
     * String network = ",Miya:Gumball,Donatello:Brett-Elba-Matt,Jasmine:Nic-Trey-Hannah-Nicole,";
     * int friendCount = countFriends(network, "Donatello");
     * System.out.println(friendCount); // Output: 3
     * }
     * 
     * @param network The current state of the social network.
     * @param userName The name of the user whose friends are to be counted.
     * @return The number of friends of the specified user.
     */
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
        int nextCommaIndex = indexOfComma(network, commaCount);
        for (int i = userIndex; i < nextCommaIndex; i++) {
            if (network.charAt(i) == '-' || i == nextCommaIndex - 1) {
                outDegree++;
            }
        }
        return outDegree;
    }
}
