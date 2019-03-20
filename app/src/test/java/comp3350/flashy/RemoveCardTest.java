package comp3350.flashy;

import org.junit.Test;
import static org.junit.Assert.*;

import comp3350.flashy.logic.LogicManager;
import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;

public class RemoveCardTest {

    @Test
    public void removeCardTest(){
        System.out.println("\nrunning RemoveCard test\n");
        LogicManager lgc = new LogicManager();
        String deckName = "Test_Deck";
        String firstCardName = deckName + "-" + 0;
        String secondCardName = deckName + "-" + 1;
        String question = "I'm thinking of something cute guess what it is.";
        Deck testDeck = new Deck(deckName);

        testDeck.addCard(new Flashcard("Bob", question, "It was a basket filled with corgi puppies. =D"));
        testDeck.addCard(new Flashcard("Jack", question, "It was a basket filled with schnauzer puppies. =D"));
        testDeck.addCard(new Flashcard("Jill", question, "It was a basket filled with wheaten terrier puppies. =D"));

        System.out.println("Test to ensure there are 3 cards.");
        int numCards = testDeck.getNumCards();

        assertTrue(numCards == 3);

        if(numCards == 3){
            System.out.println("There are 3 cards in the Deck; test passed");
        }
        else{
            System.out.println("There are " + numCards + " cards in the deck; test failed");
        }



        System.out.println(testDeck);
        //lgc.removeCard(testDeck, 1);
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
