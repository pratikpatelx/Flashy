package comp3350.flashy.tests.logic;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import comp3350.flashy.application.Services;
import comp3350.flashy.logic.LogicManager;
import comp3350.flashy.persistence.DatabaseManagement.UserHandler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetAllProfilesTest {

    private UserHandler testDB;
    private LogicManager testLGC;
    private ArrayList<String> expectedList;
    private Collection<String> testColl;

    @Before
    public void setUp() {
        testDB = mock(UserHandler.class);
        testLGC = spy(new LogicManager(Services.getFlashyPersistence()));
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
        ArrayList<String> result = testLGC.getAllProfiles();
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
