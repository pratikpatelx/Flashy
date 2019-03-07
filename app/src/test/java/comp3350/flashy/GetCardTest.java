package comp3350.flashy;

import org.junit.Test;
import static org.junit.Assert.*;

import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.logic.LogicManager;

public class GetCardTest {
    
    /**getCardTest
     *
     * This test will confirm the getCard method is retrieving flashcards as expected.
     */
    @Test
    public void getCardTest(){
        System.out.println("\n Running getCard test\n");
        LogicManager lgc = new LogicManager();
        String cardName = "newCard";
        Flashcard newCard = new Flashcard(cardName,"defaultQ","defaultA");
        lgc.addFlashcard(cardName,"defaultQ","defaultQ");
        assertEquals("getCardTest failed.",newCard,lgc.getCard(cardName));
    }
    

}
