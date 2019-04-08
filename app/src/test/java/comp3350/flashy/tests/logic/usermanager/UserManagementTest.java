package comp3350.flashy.tests.logic.usermanager;

import org.junit.Before;
import org.junit.Test;

import comp3350.flashy.logic.UserManager;
import comp3350.flashy.persistence.Interfaces.UserDatabaseImplementation;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.description;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserManagementTest {
    private UserManager testUH;
    private UserDatabaseImplementation testDB;
    private String user = "testUser";
    private String password = "hunter2";

    @Before
    public void setUp() {

        testDB = mock(UserDatabaseImplementation.class);
        testUH = new UserManager(testDB);
        doNothing().when(testDB).inputUser(user,password);
        doNothing().when(testDB).removeUser(user);
        when(testDB.getUserPassword(user)).thenReturn(password);

    }

    @Test
    public void userManagementTest(){

        testUH.addUserToDatabase(user,password);

        assertTrue("Test Failed: Accurate password not recognized", testUH.verifyUserPassword(user,password));

        assertFalse("Test Failed: Incorrect password passed", testUH.verifyUserPassword(user,"*******"));

        testUH.removeUserFromDatabase(user);

        verify(testDB, description("Test Failed: DB Input method not called")).inputUser(user,password);
        verify(testDB, times(2)).getUserPassword(user);
        verify(testDB, description("Test Failed: DB Remove method not called")).removeUser(user);

        System.out.println("User Management Unit Test complete.");

    }

}

