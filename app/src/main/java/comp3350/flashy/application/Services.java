package comp3350.flashy.application;

import comp3350.flashy.persistence.DatabaseImplementations.HSQLDB.DeckDatabaseHSQLDB;
import comp3350.flashy.persistence.DatabaseImplementations.HSQLDB.UserDatabaseHSQLDB;
import comp3350.flashy.persistence.Interfaces.DeckDatabaseImplementation;
import comp3350.flashy.persistence.Interfaces.UserDatabaseImplementation;


public class Services {
    private static DeckDatabaseImplementation deckPersistence = null;
    private static UserDatabaseImplementation userPersistence = null;

    /**
     * returns a singleton instance of the DeckDatabaseImplementation
     *
     * @return
     */
    public static synchronized DeckDatabaseImplementation getDeckPersistence() {
        if (deckPersistence == null) {
            deckPersistence = new DeckDatabaseHSQLDB(Main.getDBPathName());
        }

        return deckPersistence;
    }

    /**
     * Returns a singleton instance of the UserDatabaseImplementation
     * @return
     */
    public static synchronized UserDatabaseImplementation getUserPersistence() {
        if (userPersistence == null) {
            userPersistence = new UserDatabaseHSQLDB(Main.getDBPathName());
        }

        return userPersistence;
    }
}
