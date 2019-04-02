package comp3350.flashy.tests.logic;

import org.junit.Test;

import comp3350.flashy.application.Services;
import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.logic.LogicManager;

import static org.junit.Assert.assertEquals;

public class GetandInsertDeckTest {
    @Test
    public void getAndInsertDeckTest() {
        System.out.println("\nrunning GetandInsertDeck test\n");
        LogicManager lgc = new LogicManager(Services.getFlashyPersistence());
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

        Deck sentDeck = new Deck(deckName);


        sentDeck.addCard(new Flashcard(cardName0, question, a0));
        sentDeck.addCard(new Flashcard(cardName1, question, a1));
        sentDeck.addCard(new Flashcard(cardName2, question, a2));

        lgc.insertDeck(user, sentDeck);


        Deck deck = lgc.getDeck(user, deckName);

        Flashcard flashcard = deck.getCard(cardName0);
        assertEquals(a0, flashcard.getAnswer());

        flashcard = deck.getCard(cardName1);
        assertEquals(a1, flashcard.getAnswer());

        flashcard = deck.getCard(cardName2);
        assertEquals(a2, flashcard.getAnswer());


        System.out.println("\nGetandInsertDeck test complete\n");
    }
}
