package comp3350.flashy.logic;

import java.util.ArrayList;

class Deck implements DeckInterface, LogicInterface{
    private ArrayList<FlashcardInterface> deck;
    private String deckName;

    public Deck() {
        this.deckName = null;
        this.deck = new ArrayList<FlashcardInterface>();
    }

    public Deck(String name, ArrayList<FlashcardInterface> deck) {
        this.deckName = name;
        this.deck = deck;
    }

    /**
     * Mutators
     */

    public String getDeckName()
    {
        return this.deckName;
    }

    public void setDeckName(String newName)
    {
        this.deckName = newName;
    }

    public ArrayList getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<FlashcardInterface> deck) {
        this.deck = deck;
    }

    /**
     * Methods
     */

    //Adds a card to the deck with the given parameters
    @Override
    public void addCard(String newQuestion, String newAnswer) {
        deck.add(new Flashcard(newQuestion, newAnswer));
    }

    //Deletes a given card from the deck
    @Override
    public void deleteCard(FlashcardInterface selectedCard) {
        deck.remove(selectedCard);
    }

    //Edits a given card in the deck, with the new parameters
    @Override
    public void editCard(FlashcardInterface selectedCard, String newQuestion, String newAnswer) {
        if (deck.remove(selectedCard))
            addCard(newQuestion, newAnswer);
    }
}
