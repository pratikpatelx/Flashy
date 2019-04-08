package comp3350.flashy.tests.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import comp3350.flashy.logic.UserManager;
import comp3350.flashy.persistence.DatabaseImplementations.HSQLDB.UserDatabaseHSQLDB;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class UserIntegration {

    private UserManager userM;
    private File testDB;
    private String user, password;

    @Before
    public void setUp() throws IOException {
        this.testDB = DatabaseDuplicator.copyDB();
        final UserDatabaseHSQLDB userDB= new UserDatabaseHSQLDB(this.testDB.getAbsolutePath().replace(".script", ""));
        this.userM = new UserManager(userDB);
        user = "user";
        password = "hunter2";
    }

    @Test
    public void userIntegrationTest(){
        Collection users = userM.getAllProfiles();
        assertEquals(0,users.size());

        userM.addUserToDatabase(user,password);
        users = userM.getAllProfiles();

        assertEquals(1,users.size());

        assertTrue(userM.verifyUserPassword(user,password));
        assertFalse(userM.verifyUserPassword(user,"admin123"));

        userM.removeUserFromDatabase(user);

        users = new ArrayList<String>(userM.getAllProfiles());
        assertEquals(0,users.size());

        System.out.println("UserManager Integration test complete.");
    }




    @After
    public void tearDown(){
        testDB.delete();
    }
}
