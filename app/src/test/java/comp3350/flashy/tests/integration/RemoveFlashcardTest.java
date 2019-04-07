package comp3350.flashy.tests.integration;

import org.junit.Test;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.logic.DeckManager;
import comp3350.flashy.logic.UserManager;

import static org.junit.Assert.assertEquals;

public class RemoveFlashcardTest {

    @Test
    public void removeCardTest() {
        System.out.println("\nrunning RemoveCard test\n");
        DeckManager lgc = new DeckManager();
        UserManager uMgr = new UserManager();
        String user = "John Doe";
        uMgr.addUserToDatabase(user, "");
        String deckName = "Test_Deck";
        String cardName0 = deckName + "-" + 0;
        String cardName1 = deckName + "-" + 1;
        String cardName2 = deckName + "-" + 2;

        String question = "I'm thinking of something cute guess what it is.";
        String aStart = "It was a basket filled with ";
        String aEnd = " puppies! =D";
        String a0 = aStart.concat("schnauzer".concat(aEnd));
        String a1 = aStart.concat("corgi".concat(aEnd));
        String a2 = aStart.concat("wheaten terrier".concat(aEnd));
        Flashcard card0 = new Flashcard(cardName0, question, a0, );
        Flashcard card1 = new Flashcard(cardName1, question, a1, );
        Flashcard card2 = new Flashcard(cardName2, question, a2, );

        lgc.putFlashcardInDeck(user, deckName, card0);
        lgc.putFlashcardInDeck(user, deckName, card1);
        lgc.putFlashcardInDeck(user, deckName, card2);

        Deck testDeck = lgc.getDeck(user, deckName);
        System.out.println("Test to ensure there are 3 cards.");
        int numCards = testDeck.getNumCards();

        assertEquals(3, numCards);

        if (numCards == 3) {
            System.out.println("There are 3 cards in the Deck; test passed");
        } else {
            System.out.println("There are " + numCards + " cards in the deck; test failed");
        }

        lgc.removeCard(user, testDeck, cardName1);

        System.out.println(testDeck);
        System.out.println("\nTest to ensure there is 2 cards in the deck.");

        numCards = testDeck.getNumCards();
        assertEquals(2, numCards);
        if (numCards == 2) {
            System.out.println("There are 2 cards in the Deck, test passed");
        } else {
            System.out.println("There are " + numCards + " cards in the deck, test failed");
        }


        System.out.println("\nRemoveCard test complete\n");
    }

}
