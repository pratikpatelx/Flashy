package comp3350.flashy.logic;

import comp3350.flashy.persistence.DatabaseImplementation;
import comp3350.flashy.persistence.DatabaseManager;
import comp3350.flashy.persistence.UserDatabaseManager;

public class UserHandler {
    private UserDatabaseManager userList;

    public UserHandler(UserDatabaseManager givenImpl) {
        userList = givenImpl;
    }

    /**
     * Verify if the username, password are correct
     * @param username
     */
    public boolean verifyUser(String username, String password) {
        Boolean result = false;
            String upassword = userList.getUserPassword(username);
            if (upassword != null && upassword.equals(password)){
                result = true;
            }
            return result;
    }

    /**
     * Add a user with the given username and password
     * @param username
     * @param password
     */
    public boolean addUser(String username, String password) {
        try {
            userList.inputUser(username, password);
            return true;
        } catch (IllegalArgumentException e){
            return false;
        }
    }

    public boolean removeUser(String usename) {
        try {
            userList.removeUser(usename);
            return true;
        } catch (IllegalArgumentException e){
            return false;
        }
    }
}
