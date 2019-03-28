package comp3350.tests.integration;

import org.junit.Test;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.logic.LogicManager;

import static org.junit.Assert.assertEquals;

public class GetandInsertDeckTest {
    @Test
    public void getAndInsertDeckTest(){
        System.out.println("\nrunning GetandInsertDeck test\n");
        LogicManager lgc = new LogicManager();
        String user = "John Doe";
        lgc.addUserToDatabase(user, "");
        String deckName = "Test_Deck";
        String firstCardName = deckName + "-" + 0;
        String secondCardName = deckName + "-" + 1;
        String thirdCardName = deckName + "-" + 2;
        String question = "I'm thinking of something cute guess what it is.";
        String firstAnswer = "It was a basket filled with corgi puppies. =D";
        String secondAnswer = "It was a basket filled with schnauzer puppies. =D";
        String thirdAnswer = "It was a basket filled with wheaten terrier puppies. =D";

        Deck sentDeck = new Deck(deckName);

        sentDeck.addCard(new Flashcard(firstCardName, question, firstAnswer));
        sentDeck.addCard(new Flashcard(secondCardName, question, secondAnswer));
        sentDeck.addCard(new Flashcard(thirdCardName, question, thirdAnswer));

        lgc.insertDeck(user, sentDeck);


        Deck deck = lgc.getDeck(user, deckName);

        Flashcard flashcard = deck.getCard(firstCardName);
        assertEquals(firstAnswer, flashcard.getAnswer());

        flashcard = deck.getCard(secondCardName);
        assertEquals(secondAnswer, flashcard.getAnswer());

        flashcard = deck.getCard(thirdCardName);
        assertEquals(thirdAnswer, flashcard.getAnswer());


        System.out.println("\nGetandInsertDeck test complete\n");
    }
}
