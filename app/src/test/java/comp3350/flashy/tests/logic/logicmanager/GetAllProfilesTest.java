package comp3350.flashy.tests.logic.logicmanager;

import org.hsqldb.rights.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import comp3350.flashy.logic.UserHandler;
import comp3350.flashy.persistence.Interfaces.UserDatabaseImplementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetAllProfilesTest {

    private UserHandler testUH;
    private UserDatabaseImplementation testDB;
    private ArrayList<String> expectedList;
    private Collection<String> testColl;

    @Before
    public void setUp() {

        testDB = mock(UserDatabaseImplementation.class);
        testUH = new UserHandler(testDB);
        testColl = new ArrayList<String>() {
            {
                add("1");
                add("2");
            }
        };
    }

    @Test
    public void getAllProfilesTest() {
        System.out.println("\nrunning GetAllProfiles unit test\n");
        when(testDB.getUserCollection()).thenReturn(testColl);
        Collection result = testUH.getAllProfiles();
        verify(testDB).getUserCollection();
        expectedList = new ArrayList<String>() {
            {
                add("1");
                add("2");
            }
        };
        assertEquals(expectedList, result);
        assertTrue("Test failed: ArrayLists do not match.", expectedList.toString().contentEquals(result.toString()));
        System.out.println("GetAllProfiles test complete");
    }
}
