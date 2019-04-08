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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RemoveCardTest {

    private DeckManager testDH;
    private Deck testDeck;


    @Before
    public void setUp(){
        testDH = spy(new DeckManager(mock(DeckDatabaseImplementation.class),mock(DataTranslationLayer.class)));
        testDeck = mock(Deck.class);
        when(testDeck.getName()).thenReturn("testDeck");
        when(testDeck.deleteCard("testDeck-0")).thenReturn(true);
        doNothing().when(testDH).insertDeck("",testDeck);


    }

    @Test
    public void removeCardTest(){
        Deck result = testDH.removeCard("",testDeck,"testDeck-0");

        assertEquals(result,testDeck);

        verify(testDeck).deleteCard("testDeck-0");
        verify(testDH).insertDeck("",testDeck);

        System.out.println("Remove Card test complete");

    }
}
