package comp3350.flashy.application;

import comp3350.flashy.persistence.DatabaseImplementation;
import comp3350.flashy.persistence.DatabaseImplementations.DatabaseHSQLDB;
import comp3350.flashy.persistence.DatabaseImplementations.DatabaseStub;


public class Services {
    private static DatabaseImplementation persistence = null;


    public static synchronized DatabaseImplementation getFlashyPersistence() {
        if (persistence == null) {
            persistence = new DatabaseHSQLDB(Main.getDBPathName());
            //persistence = new DatabaseStub();
        }

        return persistence;
    }
}
