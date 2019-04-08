package comp3350.flashy.tests.logic.deckmanager;

import org.junit.Before;
import org.junit.Test;

import comp3350.flashy.domain.Deck;

import comp3350.flashy.logic.DeckManager;
import comp3350.flashy.persistence.Interfaces.DeckDatabaseImplementation;
import comp3350.flashy.persistence.Translators.DataTranslationLayer;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class InsertAndDeleteDeckTest {

    private DeckManager testDH;
    private DeckDatabaseImplementation testDB;
    private Deck testDeck;
    private DataTranslationLayer testDT;

    @Before
    public void setUp(){
        testDB = mock(DeckDatabaseImplementation.class);
        testDT = mock(DataTranslationLayer.class);
        testDH = spy(new DeckManager(testDB, testDT));
        testDeck = mock(Deck.class);


    }

    @Test
    public void insertDeckTest(){
        when(testDeck.getName()).thenReturn("testDeck");
        when(testDT.encodeDeck(testDeck)).thenReturn(testDeck);

        testDH.insertDeck("",testDeck);

        verify(testDB).inputDeck("","testDeck", testDeck);
        verify(testDeck).getName();

        System.out.println("Insert Deck test complete");

    }

    @Test
    public void deleteDeckTest(){
        doNothing().when(testDB).deleteDeck("","testDeck");

        testDH.deleteDeck("","testDeck");

        verify(testDB).deleteDeck("","testDeck");

        System.out.println("Delete Deck test complete");
    }
}
