package comp3350.flashy.application;

import comp3350.flashy.persistence.CreateAccountPersistence;
import comp3350.flashy.persistence.hsqldb.CreateAccountPersistenceHSQLDB;

public class Services {
    private static CreateAccountPersistence createAccountPersistence = null;



    public static synchronized CreateAccountPersistence getCreateAccountPersistence() {
        if (createAccountPersistence == null) {
            createAccountPersistence = new CreateAccountPersistenceHSQLDB(Main.getDBPathName());
        }

        return createAccountPersistence;
    }
}
