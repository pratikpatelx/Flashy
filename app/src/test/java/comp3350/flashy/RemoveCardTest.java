package comp3350.flashy;

//import junit.framework.*;
import org.junit.Test;
//import org.junit.runner.JUnitCore;
//import org.junit.runner.Result;
//import org.junit.runner.notification.Failure;
import static org.junit.Assert.*;



import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.logic.LogicManager;

public class RemoveCardTest {
    
    @Test
    public void removeCardTest(){
        System.out.println("\nrunning RemoveCard test\n");
        LogicManager lgc = new LogicManager();
        
        String question = "I'm thinking of something cute guess what it is.";
        
        lgc.addFlashcard("Bob", question, "It was a basket filled with corgi puppies. =D");
        lgc.addFlashcard("Jack", question, "It was a basket filled with schnauzer puppies. =D");
        lgc.addFlashcard("Jill", question, "It was a basket filled with wheaten terrier puppies. =D");
        
        
        System.out.println("Test to ensure there are 3 cards.");
        int numCards = lgc.queryDeckSize("THE_ORACLE_DECK");
        
        assertTrue(numCards == 3);
        
        if(numCards == 3){
            System.out.println("There are 3 cards in the Deck; test passed");
        }
        else{
            System.out.println("There are " + numCards + " cards in the deck; test failed");
        }
        
        lgc.removeCardFromAll("Bob");
        
        System.out.println("\nTest to ensure there is 2 cards in the deck.");
        
        numCards = lgc.queryDeckSize("THE_ORACLE_DECK");
        assertTrue(numCards == 2);
        if(numCards == 2){
            System.out.println("There are 2 cards in the Deck, test passed");
        }
        else{
            System.out.println("There are " + numCards + " cards in the deck, test failed");
        }
        
        lgc.removeFlashcardFromDeck("THE_ORACLE_DECK", "Jack");
        System.out.println("\nTest to ensure there is 1 cards in the deck.");
        
        numCards = lgc.queryDeckSize("THE_ORACLE_DECK");
        assertTrue(numCards == 1);
        if(numCards == 1){
            System.out.println("There are 1 cards in the Deck, test passed");
        }
        else{
            System.out.println("There are " + numCards + " cards in the deck, test failed");
        }
        
        
        System.out.println("\nRemoveCard test complete\n");
    }
    
}
