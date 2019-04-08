package comp3350.flashy.tests.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


public class DeckTest {
    private Deck deck;
    private Flashcard card1, card2, card3, card4;
    private Collection<Flashcard> testColl;

    @Before
    public void setUp() {
        deck = new Deck("testDeck");
        card1 = new Flashcard("testDeck-0", "q1", "a1");
        card2 = new Flashcard("testDeck-1", "q2", "a2");
        card3 = new Flashcard("testDeckwrong  - 123", "q3", "a3");
    }

    @Test
    public void testDeck() {
        assertEquals(deck.getName(),("testDeck")); //Testing getName method

        deck.addCard(card1);
        deck.addCard(card2);
        assertEquals(2,deck.getNumCards()); //Testing getNumCards method

        card1.setQuestion("q4");
        card4 = deck.getCard("testDeck-0");
        assertEquals(card4,(card1));
        assertEquals(card4.getQuestion(),(card1.getQuestion())); //Testing getCard method

        deck.deleteCard(card1.getCardName());
        assertEquals(1,deck.getNumCards()); //Testing deleteCard method

        deck.addCard(card3);
        deck.correctCards();
        assertEquals(deck.getCard("testDeck-1").getQuestion(),("q3")); //Testing validateNames method

        testColl = deck.getFlashcards();
        assertTrue(testColl.contains(card2)); //Testing getFlashcards method

        System.out.println("Deck object test complete.");

    }
}


