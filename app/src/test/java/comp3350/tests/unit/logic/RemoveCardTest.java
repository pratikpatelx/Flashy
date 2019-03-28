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

public class RemoveCardTest {

    private DatabaseManager testDB;
    private LogicManager testLGC;
    private DeckStub testDeck;

    @Before
    public void setUp(){
        testDB = mock(DatabaseManager.class);
        testDeck = mock(DeckStub.class);
        testLGC = new LogicManager(testDB);
    }

    /* Method being tested:

     @Override
        public Deck removeCard(String username, Deck curr, String cardName){
            curr.deleteCard(cardName);
            insertDeck(username, curr);
            return curr;

     */

    @Test
    public void removeCardTest(){
        System.out.println("\nRunning Remove Card unit test\n");

        Deck result = testLGC.removeCard("",testDeck,"testName");

        verify(testDeck).deleteCard("testName");
        verify(testDB).inputDeck("",testDeck.getName(), testDeck);
        System.out.println("Remove Card unit test complete");

    }
}
