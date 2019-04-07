package comp3350.flashy.tests.integration;

import org.junit.Test;

import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.logic.DeckManager;
import comp3350.flashy.logic.UserManager;

import static org.junit.Assert.assertEquals;

public class GetFlashcardTest {

    /**
     * getCardTest
     * <p>
     * This test will confirm the getCard method is retrieving flashcards as expected.
     */
    @Test
    public void getFlashcardTest() {
        System.out.println("\n Running getCard test\n");
        DeckManager deckMgr = new DeckManager();
        UserManager uMgr = new UserManager();
        String user = "John Doe";
        uMgr.addUserToDatabase(user, "");

        String deckName = "Test_Deck";
        String cardName = deckName + "-" + 0;
        Flashcard newCard = new Flashcard(cardName, "defaultQ", "defaultA", );
        deckMgr.putFlashcardInDeck(user, "Test_Deck", newCard);
        assertEquals("getCardTest failed.", newCard, deckMgr.getDeck(user, deckName).getCard(cardName));
    }
}

