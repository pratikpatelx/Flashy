package comp3350.tests.unit.logic;

import org.junit.Before;
import org.junit.Test;

import comp3350.tests.persistence.DeckStub;
import comp3350.flashy.domain.Deck;
import comp3350.flashy.logic.LogicManager;
import comp3350.flashy.persistence.DatabaseManagement.DatabaseManager;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetDeckTest {

    private DatabaseManager testDB;
    private LogicManager testLGC;

    @Before
    public void setUp(){
        testDB = mock(DatabaseManager.class);
        testLGC = new LogicManager(testDB);

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
