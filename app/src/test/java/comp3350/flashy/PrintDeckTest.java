
package comp3350.flashy;

import junit.framework.*;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import static org.junit.Assert.*;



import DomainLogic.Flashcard;
import Logic.LogicManager;
//import Persistence.DatabaseManager;



/**
 *
 * @author cjung
 */
public class PrintDeckTest {
    
    @Test
    public void printDeckTest(){
        System.out.println("\nrunning printDeck test\n");
        LogicManager lgc = new LogicManager();
        
        String question = "I'm thinking of something cute guess what it is.";
        String answer1 = "It was a basket filled with corgi puppies. =D";
        String answer2 = "It was a basket filled with schnauzer puppies. =D";
        String answer3 = "It was a basket filled with wheaten terrier puppies. =D";
        
        lgc.addFlashcard("Bob", question, answer1);
        lgc.addFlashcard("Jack", question, answer2);
        lgc.addFlashcard("Jill", question, answer3);
        
        lgc.printDeck("THE_ORACLE_DECK");
        System.out.println("\nPrintDeck test completed\n");
        
    }
    
}
