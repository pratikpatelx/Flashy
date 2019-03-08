package comp3350.flashy;

import org.junit.Test;
import static org.junit.Assert.*;

import comp3350.flashy.logic.LogicManager;


/**
 *
 * @author cjung
 */
public class PrintDeckTest {
    
    @Test
    public void printDeckTest(){
        System.out.println("\nrunning printDeck test\n");
        LogicManager lgc = new LogicManager();

        String deckName = "Test_Deck";
        String cardName = deckName + "-" + 0;

        String question = "I'm thinking of something cute guess what it is.";
        String answer1 = "It was a basket filled with corgi puppies. =D";
        String answer2 = "It was a basket filled with schnauzer puppies. =D";
        String answer3 = "It was a basket filled with wheaten terrier puppies. =D";


        
        lgc.putFlashcardInDeck(deckName,"Bob", question, answer1);
        lgc.putFlashcardInDeck(deckName,"Jack", question, answer2);
        lgc.putFlashcardInDeck(deckName,"Jill", question, answer3);

        lgc.printDeck(deckName);
        System.out.println("\nPrintDeck test completed\n");
        assertTrue(true);//Just make sure the test runs thats all that is asked
        
    }
    
}
