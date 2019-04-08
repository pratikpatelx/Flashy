package comp3350.flashy.tests.logic.deckmanager;

import org.junit.Before;
import org.junit.Test;

import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.logic.DeckManager;
import comp3350.flashy.persistence.Interfaces.DeckDatabaseImplementation;
import comp3350.flashy.persistence.Translators.DataTranslationLayer;
import comp3350.flashy.domain.Deck;


import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetDeckTest {

    private DeckManager testDH;
    private DeckDatabaseImplementation testDB;
    private DataTranslationLayer testDT;
    private Deck testDeck;
    private Flashcard testCard;

    @Before
    public void setUp(){
        testDB = mock(DeckDatabaseImplementation.class);
        testDT = mock(DataTranslationLayer.class);
        testDH = spy(new DeckManager(testDB, testDT));
        testDeck = mock(Deck.class);
        testCard = mock(Flashcard.class);

        when(testDeck.getName()).thenReturn("testDeck");
        when(testDeck.getCard("testDeck-0")).thenReturn(mock(Flashcard.class));
        when(testDB.getDeck("","testDeck")).thenReturn(testDeck);
        when(testDT.decodeDeck(testDeck)).thenReturn(testDeck);

    }

    @Test
    public void getDeckTest(){


        Deck result = testDH.getDeck("", "testDeck");

        assertNotNull(result);
        assertEquals(result.getCard("testDeck-0"),testDeck.getCard("testDeck-0"));
        verify(testDB).getDeck("","testDeck");

        System.out.println("Get Deck test complete");

    }
}
