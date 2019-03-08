package comp3350.flashy;

import org.junit.Test;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.logic.LogicManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DeleteDeckTest {

    @Test
    public void DeleteDeckTest(){
        System.out.println("\nDeleteDeck test\n");
        LogicManager lgc = new LogicManager();
        String deckAName = "AlphaDeck";
        String deckBName = "BrovoDeck";
        String cardNameA1 = deckAName + "-" + 0;
        String cardNameA2 = deckAName + "-" + 1;
        String cardNameA3 = deckAName + "-" + 2;
        String cardNameB1 = deckBName + "-" + 0;
        String cardNameB2 = deckBName + "-" + 1;
        String question = "I'm thinking of something cute guess what it is.";
        String firstAnswer = "It was a basket filled with corgi puppies. =D";
        String secondAnswer = "It was a basket filled with schnauzer puppies. =D";
        String thirdAnswer = "It was a basket filled with wheaten terrier puppies. =D";
        String fourthAnswer = "It was a basket of labrador puppies. =D";



        lgc.putFlashcardInDeck(deckAName, cardNameA1, question, firstAnswer);
        lgc.putFlashcardInDeck(deckAName, cardNameA2, question, secondAnswer);
        lgc.putFlashcardInDeck(deckAName, cardNameA3, question, thirdAnswer);
        lgc.putFlashcardInDeck(deckBName, cardNameB1, question, firstAnswer);
        lgc.putFlashcardInDeck(deckBName, cardNameB2, question, fourthAnswer);
        int numDecks = lgc.getNames().size();
        assertTrue(lgc.getNames().size() >= 2);

        Deck deckB = lgc.getDeck(deckBName);
        assertEquals(2, deckB.getNumCards());

        lgc.deleteDeck(deckB);

        assertEquals(numDecks-1, lgc.getNames().size());
        assertEquals(3, lgc.getDeck(deckAName).getNumCards());
        System.out.println("DeleteDeck test complete\n");
    }
}
