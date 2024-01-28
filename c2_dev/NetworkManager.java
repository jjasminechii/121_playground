package c2_dev;

public class NetworkManager {
    public static void main(String[] args) {
        String network = "";
        network = SocialNetworkOld.addUser(network, "Jasmine", "caffeinated");
        network = SocialNetworkOld.addUser(network, "Nic", "eating frans chocolates");
        network = SocialNetworkOld.addUser(network, "Jessie", "drawing stick figures");
        network = SocialNetworkOld.addUser(network, "Belle", "drinking Boba");
        network = SocialNetworkOld.addUser(network, "Miya", "teaching CSE 122");

        System.out.println("network status:");
        System.out.println(network);
        System.out.println("total users: " + SocialNetworkOld.networkSize(network));

        System.out.println("\nOOPS\n");

        network = SocialNetworkOld.removeUser(network, "Jessie");
        network = SocialNetworkOld.addUser(network, "Amy", "social computing");

        System.out.println("updated network status:");
        System.out.println(network);
        System.out.println("total users: " + SocialNetworkOld.networkSize(network));

        System.out.println();
        SocialNetworkOld.listUsers(network);
    }
}
