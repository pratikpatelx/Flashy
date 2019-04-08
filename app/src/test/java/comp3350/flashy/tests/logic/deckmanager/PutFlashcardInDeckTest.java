package comp3350.flashy.tests.logic.deckmanager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;


import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.domain.Deck;
import comp3350.flashy.logic.DeckManager;

import comp3350.flashy.persistence.Interfaces.DeckDatabaseImplementation;
import comp3350.flashy.persistence.Translators.DataTranslationLayer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PutFlashcardInDeckTest {

    private DeckManager testDH;
    private DeckDatabaseImplementation testDB;
    private DataTranslationLayer testDT;
    private Deck testDeck;
    private Flashcard testCard;
    private ArgumentCaptor<Flashcard> captor;

    @Before
    public void setUp(){
        testDB = mock(DeckDatabaseImplementation.class);
        testDT = mock(DataTranslationLayer.class);
        testDH = spy(new DeckManager(testDB, testDT));
        testDeck = mock(Deck.class);
        testCard = new Flashcard("name","q","a");
        captor = ArgumentCaptor.forClass(Flashcard.class);
        when(testDT.encodeDeck(testDeck)).thenReturn(testDeck);
        when(testDH.getDeck("","testDeck")).thenReturn(testDeck);
        when(testDeck.getName()).thenReturn("testDeck");


    }

    @Test
    public void putFlashCardInDeckTestDeck(){

        testDH.putFlashcardInDeck("", "testDeck",testCard);

        verify(testDeck).addCard(captor.capture());
        Flashcard result = captor.getValue();
        assertThat(testCard,is(result));
        verify(testDB).inputDeck("", "testDeck", testDeck);

        System.out.println("PutFlashCardInDeck unit test complete");

    }
}