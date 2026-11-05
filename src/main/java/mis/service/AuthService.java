package mis.service;

import java.util.HashMap;
import java.util.Map;

/**
 * AuthService.java Handles all authentication logic for the application.
 *
 * This is a UTILITY CLASS which means it has no instance fields and all its methods are static,
 * meaning you call them directly on the class (AuthService.login(...)) rather than
 * creating an object first (new AuthService().login(...)).
 *
 * The private constructor enforces this it prevents anyone from accidentally
 * doing 'new AuthService()' since that would make no sense for a utility class.
 *
 * Hard coded for demo purposes only. In a real application, you would never store passwords in plain text.
 */
public class AuthService {
    // HashMap is a data structure that stores key value pairs.
    // Here we use it to store username password mappings.
    // It allows us to look up a password instantly by providing the username as the key.
    private static final Map<String, String> USERS = new HashMap<>();
    private static String currentUser;

    static {
        USERS.put("admin", "admin123");
        USERS.put("user", "1234");
    }

    private AuthService() {}

    /**
     * login() - Checks if the given username and password match a known account.
     *
     * Step by step:
     *   1. Guard against null inputs (would cause a crash otherwise)
     *   2. Look up the expected password for the given username in the USERS map
     *   3. Compare the expected password to the one the user typed
     *   4. If they match, store the username as the currentUser and return true
     *   5. If they don't match, return false (currentUser stays unchanged)
     *
     */
    public static boolean login(String username, String password) {
        if (username == null || password == null) return false;
        String expected = USERS.get(username.trim());

        // Check two things in one line using &&:
        //   1. expected != null  --> the username actually exists in our map
        //   2. expected.equals(password)  --> the password matches
        // Both must be true for 'ok' to be true.
        // We check != null FIRST because if expected is null, calling .equals() would crash.
        boolean ok = expected != null && expected.equals(password);
        if (ok) currentUser = username.trim();
        return ok;
    }
    
    /**
     * logout() - Clears the current user session.
     * Called when the user clicks the Log Out button on the Dashboard.
     * Setting currentUser to null means no one is logged in anymore.
     */
    public static void logout() {
        currentUser = null;
    }

    public static String getCurrentUser() {
        return currentUser;
    }
}
