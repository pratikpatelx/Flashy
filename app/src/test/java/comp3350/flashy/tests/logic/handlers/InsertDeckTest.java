package comp3350.flashy.tests.logic.handlers;

import org.junit.Before;
import org.junit.Test;

import comp3350.flashy.logic.DeckHandler;
import comp3350.flashy.persistence.Interfaces.DeckDatabaseImplementation;
import comp3350.flashy.persistence.Translators.DataTranslationLayer;
import comp3350.flashy.tests.persistence.DeckStub;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class InsertDeckTest {

    private DeckHandler testDH;
    private DeckDatabaseImplementation testDB;
    private DeckStub testDeck;
    private DataTranslationLayer testDT;

    @Before
    public void setUp(){
        testDB = mock(DeckDatabaseImplementation.class);
        testDT = mock(DataTranslationLayer.class);
        testDH = spy(new DeckHandler(testDB, testDT));
        testDeck = new DeckStub("testDeck");
    }

    @Test
    public void insertDeckTest(){
        System.out.println("\nrunning Insert Deck unit test\n");
        when(testDT.encodeDeck(testDeck)).thenReturn(testDeck);
        testDH.insertDeck("",testDeck.getName(), testDeck);

        verify(testDB).inputDeck("","testDeck", testDeck);
        System.out.println("Insert Deck test complete");

    }
}
