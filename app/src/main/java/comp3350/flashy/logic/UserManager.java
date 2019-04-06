package comp3350.flashy.logic;

import java.util.Collection;

import comp3350.flashy.application.Services;
import comp3350.flashy.persistence.Interfaces.UserDatabaseImplementation;

public class UserManager {
    private UserDatabaseImplementation userDB;

    public UserManager(){
        this(Services.getUserPersistence());
    }
    public UserManager(UserDatabaseImplementation givenImpl) {
        userDB = givenImpl;
    }

    /**
     * Verify if the username, password are correct
     *
     * @param username
     */
    public boolean verifyUserPassword(String username, String givenPassword) {
        Boolean result = false;
        String password = userDB.getUserPassword(username);
        if (password != null && password.equals(givenPassword)) {
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
    public boolean addUserToDatabase(String username, String password) {
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
    public boolean removeUserFromDatabase(String username) {
        try {
            userDB.removeUser(username);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Gets a collection of the users in the DB
     *
     * @return
     */
    public Collection<String> getAllProfiles() {
        return userDB.getUserCollection();
    }
}
