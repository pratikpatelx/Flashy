package comp3350.flashy.tests.logic;

import org.junit.Test;

import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.logic.LogicManager;

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
        LogicManager lgc = new LogicManager();
        String user = "John Doe";
        lgc.addUserToDatabase(user, "");

        String deckName = "Test_Deck";
        String cardName = deckName + "-" + 0;
        Flashcard newCard = new Flashcard(cardName, "defaultQ", "defaultA");
        lgc.putFlashcardInDeck(user, "Test_Deck", newCard);
        assertEquals("getCardTest failed.", newCard, lgc.getDeck(user, deckName).getCard(cardName));
    }


}
