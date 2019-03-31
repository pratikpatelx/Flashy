package comp3350.flashy.tests.logic;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.logic.LogicManager;
import comp3350.flashy.persistence.DatabaseManagement.DatabaseManager;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EditFlashcardTest {

    private DatabaseManager testDB;
    private LogicManager testLGC;
    private Deck testDeck;
    private Flashcard testCard;

    @Before
    public void setUp() {
        testDB = mock(DatabaseManager.class);
        testDeck = mock(Deck.class);
        testLGC = spy(new LogicManager(testDB));
        testCard = new Flashcard("name", "newQ", "newA");

    }

    @Test
    public void editFlashcardTestNull() { //null test deck
        System.out.println("\nRunning Edit Flashcard - New Deck unit test\n");
        ArgumentCaptor<Flashcard> captor = ArgumentCaptor.forClass(Flashcard.class);


        when(testDB.getDeck("", "testDeck")).thenReturn(null);
        when(testLGC.makeDeck(anyString())).thenReturn(testDeck);




        testLGC.editFlashcard("", "testDeck", testCard);

        verify(testLGC).makeDeck("testDeck");
        verify(testDeck).editCard(captor.capture());
        Flashcard result = captor.getValue();

        assertThat(testCard, is(result));
        assertThat(testCard.getQuestion(), is(result.getQuestion()));
        assertThat(testCard.getAnswer(), is(result.getAnswer()));

        verify(testDB).inputDeck("", "testDeck", testDeck);
        System.out.println("Edit Flashcard - New Deck unit test complete\n");
    }

    @Test
    public void editFlashcardTestDeck() {  //testing an existing deck
        System.out.println("\nRunning Edit Flashcard - Existing Deck unit test\n");
        ArgumentCaptor<Flashcard> captor = ArgumentCaptor.forClass(Flashcard.class);
        when(testDB.getDeck("", "testDeck")).thenReturn(testDeck);

        testLGC.editFlashcard("", "testDeck", testCard);

        verify(testDeck).editCard(captor.capture());
        Flashcard result = captor.getValue();

        assertThat(testCard, is(result));
        assertThat(testCard.getQuestion(), is(result.getQuestion()));
        assertThat(testCard.getAnswer(), is(result.getAnswer()));

        verify(testLGC, never()).makeDeck(anyString());
        verify(testDB).inputDeck("", "testDeck", testDeck);

        System.out.println("Edit Flashcard - Existing Deck unit test complete\n");

    }
}