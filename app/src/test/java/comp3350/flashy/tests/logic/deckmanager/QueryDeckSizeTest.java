package comp3350.flashy.tests.logic.deckmanager;


import android.provider.ContactsContract;

import org.junit.Before;
import org.junit.Test;

import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.logic.DeckManager;
import comp3350.flashy.persistence.Interfaces.DeckDatabaseImplementation;
import comp3350.flashy.persistence.Translators.DataTranslationLayer;
import comp3350.flashy.domain.Deck;


import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class QueryDeckSizeTest {

    private DeckManager testDH;
    private Deck testDeck;

    @Before
    public void setUp(){

        testDH = spy(new DeckManager(mock(DeckDatabaseImplementation.class),mock(DataTranslationLayer.class)));
        testDeck = mock(Deck.class);
        doReturn(testDeck).when(testDH).getDeck("","testDeck");
        doReturn(null).when(testDH).getDeck("","null deck");
        when(testDeck.getNumCards()).thenReturn(69);


    }

    @Test
    public void queryDeckSizeTest(){
        int result = testDH.queryDeckSize("","testDeck");
        assertEquals(69, result);

        result = testDH.queryDeckSize("","null deck");
        assertEquals(result,0);

        verify(testDeck).getNumCards();
        verify(testDH, times(2)).getDeck(anyString(),anyString());

        System.out.println("Query Deck Size test complete");

    }
}
