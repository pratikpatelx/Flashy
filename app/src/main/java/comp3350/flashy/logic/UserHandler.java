package comp3350.flashy.logic;

import comp3350.flashy.persistence.DatabaseImplementation;
import comp3350.flashy.persistence.DatabaseManager;
import comp3350.flashy.persistence.UserDatabaseManager;

public class UserHandler {
    private UserDatabaseManager userList = null;

    public UserHandler(UserDatabaseManager givenImpl) {
        userList = givenImpl;
    }

    /**
     * Verify if the username, password are correct
     * @param username
     */
    public boolean verifyUser(String username, String password) {
        String truePass = userList.getUserPassword(username);
        return truePass.equals(password);
    }

    /**
     * Add a user with the given username and password
     * @param username
     * @param password
     */
    public void addUser(String username, String password) {
        userList.inputUser(username, password);
    }
}
