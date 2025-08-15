package user_handler;

import java.util.HashMap;
import java.util.Map;

public class UserHandler {
    private static final Map<String, String> PASSWORD = new HashMap<>();

    /**
     * Check if username already exists.
     * @param username username
     * @return true if exists
     */
    public static boolean exist(String username) {
        return PASSWORD.get(username) != null;
    }

    /**
     * Create entry and add to the hashmap.
     * @param username username
     * @param password password
     */
    public static void add(String username, String password) {
        PASSWORD.put(username, password);
    }

    /**
     * Check the password of the username with the recorded one.
     * @param username username
     * @param password password
     * @return true if they match
     */
    public static boolean passwordCorrect(String username, String password) {
        return PASSWORD.get(username).equals(password);
    }
}
