package comp3350.flashy.tests.integration;

import org.junit.Test;

import comp3350.flashy.application.Services;
import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.logic.DeckHandler;
import comp3350.flashy.logic.UserHandler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class DeckDeletionTest {

    @Test
    public void DeleteDeckTest() {
        System.out.println("\nDeleteDeck test\n");
        DeckHandler deckMgr = new DeckHandler();
        UserHandler uMgr = new UserHandler();
        String user = "John Doe";
        uMgr.addUserToDatabase(user, "");
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


        deckMgr.putFlashcardInDeck(user, deckAName, card0);
        deckMgr.putFlashcardInDeck(user, deckAName, card1);
        deckMgr.putFlashcardInDeck(user, deckAName, card2);
        deckMgr.putFlashcardInDeck(user, deckBName, card3);
        deckMgr.putFlashcardInDeck(user, deckBName, card4);
        int numDecks = deckMgr.getNames(user).size();
        assertTrue(deckMgr.getNames(user).size() >= 2);

        Deck deckB = deckMgr.getDeck(user, deckBName);
        assertEquals(2, deckB.getNumCards());

        deckMgr.deleteDeck(user, deckBName);

        assertEquals(numDecks - 1, deckMgr.getNames(user).size());
        assertEquals(3, deckMgr.getDeck(user, deckAName).getNumCards());
        System.out.println("DeleteDeck test complete\n");
    }
}

