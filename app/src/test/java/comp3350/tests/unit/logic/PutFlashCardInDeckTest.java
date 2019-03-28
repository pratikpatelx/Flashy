package comp3350.tests.unit.logic;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;

import comp3350.flashy.domain.Flashcard;
import comp3350.tests.persistence.DeckStub;
import comp3350.flashy.domain.Deck;
import comp3350.flashy.logic.LogicManager;
import comp3350.flashy.persistence.DatabaseManagement.DatabaseManager;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PutFlashCardInDeckTest {

    private DatabaseManager testDB;
    private LogicManager testLGC;
    private Deck testDeck;
    private Flashcard testCard;

    @Before
    public void setUp(){
        testDB = mock(DatabaseManager.class);
        testDeck = mock(Deck.class);
        testLGC = spy(new LogicManager(testDB));
        testCard = new Flashcard("name","q","a");

        }

   @Test
    public void putFlashCardInDeckTestNull() { //testing null deck
       System.out.println("\nRunning PutFlashCardInDeck - New Deck unit test\n");
       ArgumentCaptor<Flashcard> captor = ArgumentCaptor.forClass(Flashcard.class);


        when(testDB.getDeck("", "testDeck")).thenReturn(null);
        when(testLGC.makeDeck(anyString())).thenReturn(testDeck);

        testLGC.putFlashcardInDeck("", "testDeck", "name", "q", "a");
        verify(testDeck).addCard(captor.capture());
        Flashcard result = captor.getValue();
        assertThat(testCard,is(result));
        verify(testDB).inputDeck("", "testDeck", testDeck);
       System.out.println("PutFlashCardInDeck - New Deck unit test complete\n");
    }

    @Test
    public void putFlashCardInDeckTestDeck(){  //testing an existing deck
        System.out.println("\nRunning PutFlashCardInDeck - Existing Deck unit test\n");
        ArgumentCaptor<Flashcard> captor = ArgumentCaptor.forClass(Flashcard.class);
        when(testDB.getDeck("","testDeck")).thenReturn(testDeck);

        testLGC.putFlashcardInDeck("", "testDeck", "name", "q", "a");

        verify(testDeck).addCard(captor.capture());
        Flashcard result = captor.getValue();
        assertThat(testCard,is(result));

        verify(testDB).inputDeck("", "testDeck", testDeck);

        System.out.println("PutFlashCardInDeck - Existing Deck unit test complete\n");

    }
}
