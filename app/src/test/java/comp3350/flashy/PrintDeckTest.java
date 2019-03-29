package comp3350.flashy;

import org.junit.Test;
import static org.junit.Assert.*;

import comp3350.flashy.domain.Flashcard;
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

        lgc.printDeck(user, deckName);
        assertTrue(true);//Just make sure the test runs thats all that is asked
        System.out.println("\nPrintDeck test completed\n");


    }

}
