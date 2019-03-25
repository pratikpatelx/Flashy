package comp3350.flashy;

import org.junit.Test;
import static org.junit.Assert.*;

import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.logic.LogicManager;

public class GetFlashcardTest {
    
    /**getCardTest
     *
     * This test will confirm the getCard method is retrieving flashcards as expected.
     */
    @Test
    public void getCardTest(){
        System.out.println("\n Running getCard test\n");
        LogicManager lgc = new LogicManager();
        String deckName = "Test_Deck";
        String cardName = deckName + "-" + 0;
        Flashcard newFlashcard = new Flashcard(cardName,"defaultQ","defaultA");
        lgc.putFlashcardInDeck("Test_Deck", cardName, "defaultQ", "defaultQ");
        assertEquals("getCardTest failed.", newFlashcard,lgc.getDeck(deckName).getCard(cardName));
    }
    

}
