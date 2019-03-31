package comp3350.flashy.tests.logic;

import org.junit.Before;
import org.junit.Test;

import comp3350.flashy.tests.persistence.DeckStub;
import comp3350.flashy.domain.Deck;
import comp3350.flashy.logic.LogicManager;
import comp3350.flashy.persistence.DatabaseManagement.DatabaseManager;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DeleteDeckTest {

    private DatabaseManager testDB;
    private LogicManager testLGC;

    @Before
    public void setUp(){
        testDB = mock(DatabaseManager.class);
        testLGC = new LogicManager(testDB);

    }

    @Test
    public void deleteDeckTest(){
        System.out.println("\nrunning Delete Deck unit test\n");
        Deck original = new DeckStub("testDeck");

        testLGC.deleteDeck("","testDeck");

        verify(testDB).removeDeck("","testDeck");
        System.out.println("Delete Deck test complete");
    }
}

