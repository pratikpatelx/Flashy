package comp3350.flashy.tests.logic.logicmanager;

import org.junit.Before;
import org.junit.Test;

import comp3350.flashy.logic.PersistenceHandlers.DeckHandler;
import comp3350.flashy.logic.PersistenceHandlers.UserHandler;
import comp3350.flashy.tests.persistence.DeckStub;
import comp3350.flashy.domain.Deck;
import comp3350.flashy.logic.LogicManager;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetDeckTest {

    private DeckHandler testDB;
    private LogicManager testLGC;

    @Before
    public void setUp(){
        testDB = mock(DeckHandler.class);
        testLGC = new LogicManager(testDB, mock(UserHandler.class));

    }

    @Test
    public void getDeckTest(){
        System.out.println("\nrunning Get Deck unit test\n");
        Deck original = new DeckStub("testDeck");

        when(testDB.getDeck("","testDeck")).thenReturn(original);

        Deck result = testLGC.getDeck("", "testDeck");
        assertNotNull(result);
        assertEquals(result.getCard("testDeck-0"),original.getCard("testDeck-0"));

        verify(testDB).getDeck("","testDeck");
        System.out.println("Get Deck test complete");

    }
}
