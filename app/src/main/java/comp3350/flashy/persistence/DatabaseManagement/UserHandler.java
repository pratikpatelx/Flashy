package comp3350.flashy.persistence.DatabaseManagement;

import java.util.Collection;

import comp3350.flashy.persistence.DatabaseImplementation;

public class UserHandler {
    private DatabaseImplementation userDB;

    public UserHandler(DatabaseImplementation givenImpl) {
        userDB = givenImpl;
    }

    /**
     * Verify if the username, password are correct
     *
     * @param username
     */
    public boolean verifyUser(String username, String password) {
        Boolean result = false;
        String upassword = userDB.getUserPassword(username);
        if (upassword != null && upassword.equals(password)) {
            result = true;
        }
        return result;
    }

    /**
     * Add a user with the given username and password
     *
     * @param username
     * @param password
     */
    public boolean addUser(String username, String password) {
        try {
            userDB.inputUser(username, password);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Remove a given user if it exists
     *
     * @param username
     * @return
     */
    public boolean removeUser(String username) {
        try {
            userDB.removeUser(username);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public Collection<String> getUserCollection() {
        return userDB.getUserCollection();
    }
}
