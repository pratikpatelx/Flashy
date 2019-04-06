package comp3350.flashy.tests.logic.logicmanager;

import org.junit.Before;
import org.junit.Test;

import comp3350.flashy.logic.DeckHandler;
import comp3350.flashy.persistence.Interfaces.DeckDatabaseImplementation;
import comp3350.flashy.tests.persistence.DeckStub;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class InsertDeckTest {

    private DeckHandler testDH;
    private DeckDatabaseImplementation testDB;
    private DeckStub testDeck;

    @Before
    public void setUp(){
        testDB = mock(DeckDatabaseImplementation.class);
        testDH = new DeckHandler(testDB);
        testDeck = new DeckStub("testDeck");
    }

    @Test
    public void insertDeckTest(){
        System.out.println("\nrunning Insert Deck unit test\n");

        testDH.insertDeck("",testDeck.getName(), testDeck);

        verify(testDB).inputDeck("","testDeck", testDeck);
        System.out.println("Insert Deck test complete");

    }
}
