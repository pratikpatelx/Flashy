package comp3350.flashy.persistence;

import org.hsqldb.DatabaseManager;

import java.util.Collection;

public class UserDatabaseManager extends DatabaseManager {
    private DatabaseImplementation storage;

    public UserDatabaseManager(DatabaseImplementation implementation){
        storage = implementation;
        createDefaultData();
    }

    public void inputUser (String username, String password)
    {
        storage.inputUser(username, password);
    }

    public String getUserPassword (String username){
        return storage.getUserPassword(username);
    }

    public void removeUser (String username){
        storage.removeUser(username);
    }

    public Collection getUserCollection() {
        return storage.getUserCollection();
    }

    private void createDefaultData() {
        //storage.inputUser("","");

        for (int i = 0; i < 3; i++) {
            storage.inputUser(("test"+i),"");
        }
    }
}