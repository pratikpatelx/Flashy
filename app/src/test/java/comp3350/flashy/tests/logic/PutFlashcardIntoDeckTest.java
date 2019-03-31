package comp3350.flashy;

import org.junit.Test;
import static org.junit.Assert.*;

import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.logic.LogicManager;

import java.lang.String;

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
        String aStart = "It was a basket filled with ";
        String aEnd = " puppies! =D";
        String a0 = aStart.concat("schnauzer".concat(aEnd));
        String a1 = aStart.concat("corgi".concat(aEnd));
        String a2 = aStart.concat("wheaten terrier".concat(aEnd));
        Flashcard card0 = new Flashcard(cardName0, question, a0);
        Flashcard card1 = new Flashcard(cardName1, question, a1);
        Flashcard card2 = new Flashcard(cardName2, question, a2);

        lgc.putFlashcardInDeck(user, deckName, card0);
        lgc.putFlashcardInDeck(user, deckName, card1);
        lgc.putFlashcardInDeck(user, deckName, card2);



        System.out.println("Test to ensure there are 3 cards in the deck.");

        assertEquals(3,  lgc.queryDeckSize(user, deckName));

        if(lgc.queryDeckSize(user, deckName) == 3){
            System.out.println("There are 3 cards in the Deck, test passed");
        }
        else{
            System.out.println("There are " + lgc.queryDeckSize(user, deckName) + " cards in the deck test failed");
        }


        System.out.println("\nPutCardIntoDeck test complete\n");

    }

}

