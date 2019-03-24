package comp3350.flashy.logic;

import comp3350.flashy.persistence.DatabaseManagement.DatabaseManager;

public class UserHandler {
    private DatabaseManager userList;

    public UserHandler(DatabaseManager givenImpl) {
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

    /**
     * Remove a given user if it exists
     * @param username
     * @return
     */
    public boolean removeUser(String username) {
        try {
            userList.removeUser(username);
            return true;
        } catch (IllegalArgumentException e){
            return false;
        }
    }
}
