package it.tozzi;

import java.util.ArrayList;

public class ServerManager {

    private static ArrayList<String> users = new ArrayList<String>();
    private static ArrayList<ServerThread> threads = new ArrayList<ServerThread>();

    public static boolean checkUser(String username) {
        if(users.contains(username)) {
            return true;
        } else {
            users.add(username);
            return false;
        }   
    }

}
