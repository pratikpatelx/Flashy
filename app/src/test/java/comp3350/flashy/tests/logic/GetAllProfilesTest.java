package comp3350.flashy.tests.logic;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import comp3350.flashy.tests.persistence.DeckStub;
import comp3350.flashy.logic.LogicManager;
import comp3350.flashy.persistence.DatabaseManagement.DatabaseManager;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetAllProfilesTest {

    private DatabaseManager testDB;
    private LogicManager testLGC;
    private ArrayList<String> testList;
    private Collection<String> testColl;
    private String testUser;

    @Before
    public void setUp(){
        testDB = mock(DatabaseManager.class);
        testLGC = new LogicManager(testDB);
        testUser = "testU";
        testColl = new ArrayList();
        testColl.add(testUser);
    }


    //DOES THIS METHOD NEED A SYSTEM.OUT.PRINTLN?

    @Test
    public void getAllDeckTest(){
        System.out.println("\nrunning GetAllProfiles unit test\n");

        when(testDB.getUserCollection()).thenReturn(testColl);

        ArrayList<String> result = testLGC.getAllProfiles();
        assertTrue("Test failed: Result does not contain testDeck.",result.contains(testUser));
        testList = new ArrayList<String>(testColl);
        assertEquals(testList,result);

        verify(testDB).getUserCollection();

        System.out.println("GetAllProfiles test complete");

    }
}
