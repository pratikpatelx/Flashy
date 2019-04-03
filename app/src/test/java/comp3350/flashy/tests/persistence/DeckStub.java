package comp3350.flashy.tests.persistence;


import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;

public class DeckStub extends Deck {

    private String firstCardName = "testDeck-" + 0;
    private String secondCardName = "testDeck-" + 1;
    private String thirdCardName = "testDeck-" + 2;
    private String question = "testQ";
    private String firstAnswer = "testA1";
    private String secondAnswer = "testA2";
    private String thirdAnswer = "testA3";

    public DeckStub() {
        super("testDeck");
        fillStub();
    }

    public DeckStub(String deckName) {
        super(deckName);
        fillStub();
    }

    private void fillStub() {
        this.addCard(new Flashcard(firstCardName, question, firstAnswer));
        this.addCard(new Flashcard(secondCardName, question, secondAnswer));
        this.addCard(new Flashcard(thirdCardName, question, thirdAnswer));
    }

}


