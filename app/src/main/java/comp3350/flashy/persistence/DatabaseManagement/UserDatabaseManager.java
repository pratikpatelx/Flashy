package comp3350.flashy.persistence.DatabaseManagement;

import org.hsqldb.DatabaseManager;

public class UserDatabaseManager extends DatabaseManager {
    private DatabaseImplementation storage;

    public UserDatabaseManager(DatabaseImplementation implementation){
        storage = implementation;
    }

    public void inputUser (String username, String password) {
        storage.inputUser(username, password);
    }

    public String getUserPassword (String username){
        return storage.getUserPassword(username);
    }

    public void removeUser (String username){
        storage.removeUser(username);
    }
}