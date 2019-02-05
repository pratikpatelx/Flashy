package Logic;

import java.util.Hashtable;

public class DeckManager implements DeckManagerInterface, LogicInterface {
    private Hashtable<String, DeckInterface> deckList;

    /**
     * Constructors
     */

    public DeckManager() {
        deckList = new Hashtable<>();
    }

    /**
     * Mutators
     */

    public Hashtable<String, DeckInterface> getDeckList() {
        return deckList;
    }

    public void setDeckList(Hashtable<String, DeckInterface> newDeckList)
    {
        this.deckList = newDeckList;
    }

    /**
     * Methods
     */

    @Override
    public void addDeck(String deckName, DeckInterface newDeck) {
        deckList.put(deckName, newDeck);
    }

    @Override
    public void deleteDeck(String deckNameSelected) {
        deckList.remove(deckNameSelected);
    }
}