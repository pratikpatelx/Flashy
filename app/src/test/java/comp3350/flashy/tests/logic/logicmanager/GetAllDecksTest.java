package comp3350.flashy.tests.logic.logicmanager;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import comp3350.flashy.application.Services;
import comp3350.flashy.domain.Deck;
import comp3350.flashy.logic.LogicManager;
import comp3350.flashy.logic.PersistenceHandlers.DeckHandler;
import comp3350.flashy.logic.PersistenceHandlers.UserHandler;
import comp3350.flashy.tests.persistence.DeckStub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetAllDecksTest {

    private DeckHandler testDB;
    private UserHandler fakeUH;
    private LogicManager testLGC;
    private ArrayList<Deck> testList;
    private Collection<Deck> testColl;
    private Deck testDeck;

    @Before
    public void setUp() {
        testDB = mock(DeckHandler.class);
        fakeUH = mock(UserHandler.class);
        testLGC = new LogicManager(testDB,fakeUH);
        testDeck = new DeckStub();
        testColl = new ArrayList<>();
        testColl.add(testDeck);
    }


    //Needs to return a COLLECTION. Need a way to confirm that testCollection cast to a new list is the same as our returned collection cast to list

    @Test
    public void getAllDecksTest() {
        System.out.println("\nrunning GetAllDecks unit test\n");

        when(testDB.getDeckCollection("")).thenReturn(testColl);

        ArrayList<Deck> result = testLGC.getAllDecks("");
        assertTrue("Test failed: Result does not contain testDeck.", result.contains(testDeck));
        testList = new ArrayList<>(testColl);
        assertEquals(testList, result);

        verify(testDB).getDeckCollection("");

        System.out.println("GetAllDecks test complete");

    }
}
