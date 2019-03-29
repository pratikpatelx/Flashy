package comp3350.flashy;

import org.junit.Test;
import static org.junit.Assert.*;

import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.logic.LogicManager;
import comp3350.flashy.domain.Deck;

public class RemoveFlashcardTest {

    @Test
    public void removeCardTest(){
        System.out.println("\nrunning RemoveCard test\n");
        LogicManager lgc = new LogicManager();
        String user = "John Doe";
        lgc.addUserToDatabase(user, "");
        String deckName = "Test_Deck";
        String firstCardName = deckName + "-" + 0;
        String secondCardName = deckName + "-" + 1;
        String question = "I'm thinking of something cute guess what it is.";



        lgc.putFlashcardInDeck(user, deckName,"Bob", question, "It was a basket filled with corgi puppies. =D");
        lgc.putFlashcardInDeck(user, deckName, "Jack", question, "It was a basket filled with schnauzer puppies. =D");
        lgc.putFlashcardInDeck(user, deckName, "Jill", question, "It was a basket filled with wheaten terrier puppies. =D");

        Deck testDeck = lgc.getDeck(user, deckName);
        System.out.println("Test to ensure there are 3 cards.");
        int numCards = testDeck.getNumCards();

        assertEquals(3, numCards);

        if(numCards == 3){
            System.out.println("There are 3 cards in the Deck; test passed");
        }
        else{
            System.out.println("There are " + numCards + " cards in the deck; test failed");
        }

        lgc.removeCard(user, testDeck, secondCardName);

        System.out.println(testDeck);
        System.out.println("\nTest to ensure there is 2 cards in the deck.");

        numCards = testDeck.getNumCards();
        assertEquals(2, numCards );
        if(numCards == 2){
            System.out.println("There are 2 cards in the Deck, test passed");
        }
        else{
            System.out.println("There are " + numCards + " cards in the deck, test failed");
        }


        System.out.println("\nRemoveCard test complete\n");
    }

}
