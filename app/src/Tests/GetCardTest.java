
package Tests;

//import junit.framework.*;
import org.junit.Test;
//import org.junit.runner.JUnitCore;
//import org.junit.runner.Result;
//import org.junit.runner.notification.Failure;
import static org.junit.Assert.*;



import DomainLogic.Flashcard;
import Logic.LogicManager;

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
