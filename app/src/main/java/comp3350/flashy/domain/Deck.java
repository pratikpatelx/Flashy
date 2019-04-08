package comp3350.flashy.domain;

import java.util.ArrayList;
import java.util.Collection;


public class Deck {


    private static final String DUMMYNAME = "DUMMY";
    private static final String DUMMYQUESTION = "Why did you give me this dummy?";
    private static final String DUMMYANSWER = "There are either no cards in the "
            + "deck or you asked for a specific card that does not exist";

    private ArrayList<Flashcard> cards;
    private String name;

    public Deck(String name) {
        this.name = name;
        cards = new ArrayList();
    }

    public Deck(String name, Deck other) {
        this.name = name;
        cards = new ArrayList<>();
        cards.addAll(other.getFlashcards());
        correctCards();
    }


    public Collection<Flashcard> getFlashcards() {
        return cards;
    }

    public void clearDeck() {
        cards.clear();
    }

    public void setDeck(Deck other) {
        this.cards = new ArrayList<Flashcard>();
        this.cards.addAll(other.getFlashcards());
        this.correctCards();
    }


    public String getName() {
        return this.name;
    }

    public void setDeckName(String newName) {
        this.name = newName;
        this.correctCards();
    }


    /**
     * correctCards()
     * This function will iterate through the cards and ensures each one has the
     * correct name
     */
    public final void correctCards() {
        Flashcard card;
        for (int i = 0; i < this.cards.size(); i++) {
            card = this.cards.get(i);
            card.setInDeck(this.name);
            card.setPosition(i);
        }
    }

    /*
     * addCard()
     *
     * Parameters:
     *  String question: The Question on the flashcard
     *  String answer: The answer to the question, no it's not 42, well not neccesarily
     *
     * This function will execute the process for putting a new "Flashcard" to
     * the deck
     * This function will be called when the "Add Card/Done" button is pressed in the
     * card creation GUI.
     * This function handles the creation of the simple flashcard
     */
    public void addCard(Flashcard noob) {
        noob.setInDeck(this.name);
        noob.setPosition(this.getNumCards());
        cards.add(noob);
    }


    /**
     * deleteCard()
     * <p>
     * This method removes the card in the ArrayList specified by the card to be
     * removed.
     *
     *
     * @param pos@return a boolean of whether a card was actually removed from the deck
     */
    public boolean deleteCard(int pos) {
        boolean success = false;
        if (pos >= 0) {
            this.cards.remove(pos);
            this.correctCards();
            success = true;
        }
        return success;
    }


    /**
     * editCard()
     * This method will change the card with the same name as the parameter changed
     * to be the same as the parameter changed.
     *
     * @param changed
     * @return returns weather or not the card was successfully edited;
     */

    public boolean editCard(Flashcard changed) {
        boolean success = false;
        if (cards.size() >= 0) {
            int idx = cards.indexOf(changed);
            if (idx >= 0) {
                cards.get(idx).editCard(changed);
                success = true;
            }

        }
        return success;
    }



     /**
     * getCard()
     * <p>
     * this function returns the Flashcard object in this deck at the position equal to "pos"
     * if there are no cards in the deck then then a dummy card will be returned.
     *
     *
     * @param pos
     * @return the Flashcard object with the specified name the method will return
     * a dummy card if it can't find the card requested
     */
    public Flashcard getCard(int pos) {
        Flashcard card;
        if (this.cards.size() == 0) {
            card = makeDummy();
        } else {
            if (pos >= 0 && pos < this.cards.size()) {
                card = this.cards.get(pos);
            } else {
                card = makeDummy();
            }
        }
        return card;
    }

    public Flashcard getCard(String id) {
        Flashcard card = null;
        if (this.cards.size() == 0) {
            card = makeDummy();

        } else {
            boolean found = false;
            int i = 0;
            while(i < this.getNumCards() && !found){
                card = this.cards.get(i);
                if(card.getCardName().equals(id)){
                    found = true;
                }
                i++;
            }

            if (!found) {
                card = makeDummy();
            }
        }
        return card;
    }


    private Flashcard makeDummy() {
        return new Flashcard(DUMMYNAME, DUMMYQUESTION, DUMMYANSWER, 0);
    }


    /**
     * getNumCards()
     *
     * @return the number of flashcards in this Deck
     */
    public int getNumCards() {
        return this.cards.size();
    }


    /**
     * toString()
     * <p>
     * will return a string containing information on the deck
     * along with its contents
     *
     * @return
     */
    @Override
    public String toString() {
        String info = "Deck: " + this.name + "\n";
        for (int i = 0; i < this.cards.size(); i++) {
            info += cards.get(i) + "\n";
        }
        return info;
    }

}