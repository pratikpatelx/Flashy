package comp3350.flashy;

import org.junit.Test;
import static org.junit.Assert.*;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.logic.LogicManager;

public class PutCardIntoDeckTest {
    
    /**
     * addCardToDeckTest
     * 
     * This test will test adding flashcards and both methods of putting flashcards
     * into decks.
     */
    @Test
    public void putCardIntoDeckTest(){
        System.out.println("\nrunning putCardToDeck test\n");
        LogicManager lgc = new LogicManager();
        
        String question = "I'm thinking of something cute guess what it is.";
        
        lgc.addFlashcard("Bob", question, "It was a basket filled with corgi puppies. =D");
        lgc.addFlashcard("Jack", question, "It was a basket filled with schnauzer puppies. =D");
        Flashcard jill = new Flashcard("Jill", question, "It was a basket filled with wheaten terrier puppies. =D");
        
        
        //add cards to decks
        lgc.putFlashcardIntoDeck("Boys", "Bob", "I'm thinking of something cute guess what it is.", 
                "It was a basket filled with corgi puppies. =D");
        lgc.putFlashcardIntoDeck("Boys", "Jack", "I'm thinking of something cute guess what it is.", 
                "It was a basket filled with schnauzer puppies. =D");
        lgc.putFlashcardIntoDeck("Girls", jill);
        
        System.out.println("Test to ensure there are 2 cards in the deck 'Boys'.");
        int numCards = lgc.queryDeckSize("Boys");
        
        assertTrue(numCards == 2);
        
        if(numCards == 2){
            System.out.println("There are two cards in the Deck Boys, test passed");
        }
        else{
            System.out.println("There are " + numCards + " cards in the deck 'Boys', test failed");
        }
        
        System.out.println("\nTest to ensure there is 1 card in the deck 'Girls'.");
        
        numCards = lgc.queryDeckSize("Girls");
        assertTrue(numCards == 1);
        if(numCards == 1){
            System.out.println("There is 1 card in the Deck 'Girls', test passed");
        }
        else{
            System.out.println("There are " + numCards + " cards in the deck 'Girls', test failed");
        }
        
        
        System.out.println("\naddCardToDeck test complete\n");
        
    }
    
}
