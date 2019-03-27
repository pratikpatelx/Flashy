package comp3350.flashy;

import org.junit.Test;
import static org.junit.Assert.*;

import comp3350.flashy.logic.LogicManager;

public class PutFlashcardIntoDeckTest {
    
    /**
     * addCardToDeckTest
     * 
     * This test will test adding flashcards and both methods of putting flashcards
     * into decks.
     */
    @Test
    public void putCardIntoDeckTest(){
        System.out.println("\nrunning putCardIntoDeck test\n");
        LogicManager lgc = new LogicManager();
        String user = "John Doe";
        lgc.addUserToDatabase(user, "");
        String deckName = "Test_Deck";
        String cardName0 = deckName + "-" + 0;
        String cardName1 = deckName + "-" + 1;
        String cardName2 = deckName + "-" + 2;

        
        String question = "I'm thinking of something cute guess what it is.";
        
        lgc.putFlashcardInDeck(user, deckName, cardName0, question, "It was a basket filled with corgi puppies. =D");
        lgc.putFlashcardInDeck(user, deckName, cardName1, question,"It was a basket filled with schnauzer puppies. =D");
        lgc.putFlashcardInDeck(user, deckName, cardName2, question, "It was a basket filled with wheaten terrier puppies. =D");
        

        
        System.out.println("Test to ensure there are 3 cards in the deck.");
        
        assertEquals(3,  lgc.queryDeckSize(user, deckName));
        
        if(lgc.queryDeckSize(user, deckName) == 3){
            System.out.println("There are 3 cards in the Deck, test passed");
        }
        else{
            System.out.println("There are " + lgc.queryDeckSize(user, deckName) + " cards in the deck 'Boys', test failed");
        }

        
        System.out.println("\nPutCardIntooDeck test complete\n");
        
    }
    
}
