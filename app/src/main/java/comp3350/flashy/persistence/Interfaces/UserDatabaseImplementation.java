package comp3350.flashy.persistence.Interfaces;

import java.util.Collection;

public interface UserDatabaseImplementation {
    /*
    User based methods
     */
    void inputUser(String username, String password);

    String getUserPassword(String username);

    void removeUser(String username);

    Collection<String> getUserCollection();
}
