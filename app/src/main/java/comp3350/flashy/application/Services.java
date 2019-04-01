package comp3350.flashy.application;

import comp3350.flashy.persistence.DatabaseImplementations.DatabaseHSQLDB;


public class Services {
    private static DatabaseHSQLDB persistence = null;



    public static synchronized DatabaseHSQLDB getFlashyPersistence() {
        if (persistence == null) {
            persistence = new DatabaseHSQLDB(Main.getDBPathName());
        }

        return persistence;
    }
}
