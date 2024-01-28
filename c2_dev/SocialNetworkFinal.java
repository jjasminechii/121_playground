package c2_dev;


public class SocialNetworkFinal {

    public static String addUser(String network, String user, String status) {
        return network + user + ":" + status + ",";
    }

    public static int size(String network) {
        int count = 0;
        for (int i = 0; i < network.length(); i++) {
            if (network.charAt(i) == ',') {
                count++;
            }
        }
        return count;
    }

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

    public static void listUsers(String network) {
        int lastComma = -1;
        for (int i = 0; i < network.length(); i++) {
            if (network.charAt(i) == ',') {
                String userStatus = network.substring(lastComma + 1, i);
                int colonIndex = userStatus.indexOf(":");
                if (colonIndex != -1) {
                    System.out.println(userStatus.substring(0, colonIndex));
                }
                lastComma = i;
            }
        }
    }

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
}
