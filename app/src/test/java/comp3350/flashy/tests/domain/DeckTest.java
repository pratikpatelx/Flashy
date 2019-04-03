package comp3350.flashy.tests.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;

import static org.junit.Assert.assertTrue;


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
        System.out.println("Running Deck object test");
        assertTrue(deck.getName().equals("testDeck")); //Testing getName method

        deck.addCard(card1);
        deck.addCard(card2);
        assertTrue(deck.getNumCards() == 2); //Testing getNumCards method

        card1.setQuestion("q4");
        deck.editCard(card1);
        card4 = deck.getCard("testDeck-0");
        assertTrue(card4.equals(card1));
        assertTrue(card4.getQuestion().equals(card1.getQuestion())); //Testing getCard and editCard method

        deck.deleteCard(card1.getCardName());
        assertTrue(deck.getNumCards() == 1); //Testing deleteCard method

        deck.addCard(card3);
        deck.validateNames();
        assertTrue(deck.getCard("testDeck-1").getQuestion().equals("q3")); //Testing validateNames method

        testColl = deck.getFlashcards();
        assertTrue(testColl.contains(card2)); //Testing getFlashcards method

        System.out.println("Deck object test complete.");

    }
}


