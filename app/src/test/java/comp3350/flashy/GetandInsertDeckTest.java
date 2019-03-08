package comp3350.flashy;

import org.junit.Test;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.logic.LogicManager;

import static org.junit.Assert.assertEquals;

public class GetandInsertDeckTest {
    @Test
    public void getAndInsertDeckTest(){
        System.out.println("\nrunning RemoveCard test\n");
        LogicManager lgc = new LogicManager();
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

        lgc.insertDeck(sentDeck);


        Deck deck = lgc.getDeck(deckName);

        Flashcard card = deck.getCard(firstCardName);
        assertEquals(firstAnswer, card.getAnswer());

        card = deck.getCard(secondCardName);
        assertEquals(secondAnswer, card.getAnswer());

        card = deck.getCard(thirdCardName);
        assertEquals(thirdAnswer, card.getAnswer());


        System.out.println("\nRemoveCard test complete\n");
    }
}
