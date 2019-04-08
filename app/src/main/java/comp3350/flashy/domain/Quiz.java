package comp3350.flashy.domain;

import java.util.ArrayList;
import java.util.Random;

import comp3350.flashy.logic.CardPrepper;

public class Quiz {


    private int curr;

    private ArrayList<Flashcard> cards;

    public Quiz(Deck deck) {
        this.cards = new ArrayList<Flashcard>();
        this.cards.addAll(deck.getFlashcards());
        this.prepareCards();
        this.shuffle();
    }


    private void prepareCards() {
        for (int i = 0; i < this.cards.size(); i++) {
            switch (cards.get(i).getCardType()){
                case "1":
                    CardPrepper.prepareFitBCard((FillInTheBlanksFlashcard) cards.get(i));
                    break;
                case "2":
                    CardPrepper.prepareMultipleChoiceFlashcard((MultipleChoiceFlashcard) cards.get(i));
                    break;
                default:
                    break;
            }
        }
    }


    /********** Accessors ***********/

    public Flashcard getCard() {
        return this.cards.get(0);
    }

    public Flashcard takeCard() {
        return this.cards.remove(0);
    }


    /**
     * placeCard()
     * This method places the flashcard card into a random position in the deck
     *
     * @param card the card to be added
     */
    public void placeCard(Flashcard card) {

        if (!this.cards.isEmpty()) {
            this.cards.add(card);
        }


    }

    public int getSize() {
        return this.cards.size();
    }

    public boolean isEmpty() {
        return this.cards.isEmpty();
    }


    private void shuffle() {
        ArrayList<Flashcard> deck = this.cards;
        ArrayList<Flashcard> mixed = new ArrayList<Flashcard>();
        int num = deck.size();
        Random rand = new Random();

        Flashcard picked;

        for (int i = 0; i < num; i++) {
            picked = deck.get(rand.nextInt(deck.size()));
            deck.remove(picked);
            mixed.add(picked);
        }
        this.cards = mixed;
    }


}
