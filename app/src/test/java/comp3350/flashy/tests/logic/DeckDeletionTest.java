package comp3350.flashy.tests.logic;

import org.junit.Test;

import comp3350.flashy.application.Services;
import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.logic.LogicManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class DeckDeletionTest {

    @Test
    public void DeleteDeckTest() {
        System.out.println("\nDeleteDeck test\n");
        LogicManager lgc = new LogicManager(Services.getFlashyPersistence());
        String user = "John Doe";
        lgc.addUserToDatabase(user, "");
        String deckAName = "AlphaDeck";
        String deckBName = "BrovoDeck";
        String cardNameA1 = deckAName + "-" + 0;
        String cardNameA2 = deckAName + "-" + 1;
        String cardNameA3 = deckAName + "-" + 2;
        String cardNameB1 = deckBName + "-" + 0;
        String cardNameB2 = deckBName + "-" + 1;
        String question = "I'm thinking of something cute guess what it is.";
        String firstAnswer = "It was a basket filled with corgi puppies. =D";
        String secondAnswer = "It was a basket filled with schnauzer puppies. =D";
        String thirdAnswer = "It was a basket filled with wheaten terrier puppies. =D";
        String fourthAnswer = "It was a basket of labrador puppies. =D";


        Flashcard card0 = new Flashcard(cardNameA1, question, firstAnswer);
        Flashcard card1 = new Flashcard(cardNameA2, question, secondAnswer);
        Flashcard card2 = new Flashcard(cardNameA3, question, thirdAnswer);
        Flashcard card3 = new Flashcard(cardNameB1, question, firstAnswer);
        Flashcard card4 = new Flashcard(cardNameB2, question, fourthAnswer);


        lgc.putFlashcardInDeck(user, deckAName, card0);
        lgc.putFlashcardInDeck(user, deckAName, card1);
        lgc.putFlashcardInDeck(user, deckAName, card2);
        lgc.putFlashcardInDeck(user, deckBName, card3);
        lgc.putFlashcardInDeck(user, deckBName, card4);
        int numDecks = lgc.getNames(user).size();
        assertTrue(lgc.getNames(user).size() >= 2);

        Deck deckB = lgc.getDeck(user, deckBName);
        assertEquals(2, deckB.getNumCards());

        lgc.deleteDeck(user, deckBName);

        assertEquals(numDecks - 1, lgc.getNames(user).size());
        assertEquals(3, lgc.getDeck(user, deckAName).getNumCards());
        System.out.println("DeleteDeck test complete\n");
    }
}

