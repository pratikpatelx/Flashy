package comp3350.flashy.application;

import comp3350.flashy.persistence.DatabaseImplementation;
import comp3350.flashy.persistence.DatabaseImplementations.DatabaseHSQLDB;

public class Services {
    private static DatabaseImplementation database = null;

    public static synchronized DatabaseImplementation getDatabase() {
        if (database == null) {
            //database = new DatabaseStub();
            database = new DatabaseHSQLDB(DBSetup.getDBPathName());
        }

        return database;
    }
}
