package comp3350.tests.unit.logic;

import org.junit.Before;
import org.junit.Test;

import comp3350.tests.persistence.DeckStub;
import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.logic.LogicManager;
import comp3350.flashy.persistence.DatabaseImplementations.DatabaseStub;
import comp3350.flashy.persistence.DatabaseImplementation;
import comp3350.flashy.persistence.DatabaseManagement.DatabaseManager;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

