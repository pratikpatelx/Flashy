package comp3350.flashy.tests.logic.logicmanager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.logic.DeckHandler;
import comp3350.flashy.persistence.Interfaces.DeckDatabaseImplementation;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EditFlashcardTest {

    private DeckHandler testDH;
    private Deck testDeck;
    private Flashcard testCard;
    private DeckDatabaseImplementation testDB;

    @Before
    public void setUp() {

        testDB = mock(DeckDatabaseImplementation.class);
        testDH = new DeckHandler(testDB);
        testDeck = mock(Deck.class);
        testCard = new Flashcard("name", "newQ", "newA");

    }

    @Test
    public void editFlashcardTestNull() { //null test deck
        System.out.println("\nRunning Edit Flashcard - New Deck unit test\n");
        ArgumentCaptor<Flashcard> captor = ArgumentCaptor.forClass(Flashcard.class);


        when(testDB.getDeck("", "testDeck")).thenReturn(null);
        when(testDH.makeDeck(anyString())).thenReturn(testDeck);


        testDH.editFlashcard("", "testDeck", testCard);

        verify(testDH).makeDeck("testDeck");
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

        testDH.editFlashcard("", "testDeck", testCard);

        verify(testDeck).editCard(captor.capture());

        Flashcard result = captor.getValue();

        assertThat(testCard, is(result));
        assertThat(testCard.getQuestion(), is(result.getQuestion()));
        assertThat(testCard.getAnswer(), is(result.getAnswer()));

        verify(testDH, never()).makeDeck(anyString());
        verify(testDB).inputDeck("", "testDeck", testDeck);

        System.out.println("Edit Flashcard - Existing Deck unit test complete\n");

    }
}