package Logic;

import java.util.Hashtable;

public class DeckManager implements DeckManagerInterface, LogicInterface {
    private Hashtable<String, DeckInterface> deckList;

    public DeckManager()
    {
        deckList = new Hashtable<>();
    }

    public Hashtable<String, DeckInterface> getDeckList()
    {
        return deckList;
    }

    public void setDeckList(Hashtable<String, DeckInterface> newDeckList)
    {
        this.deckList = newDeckList;
    }

    @Override
    public void addDeck(String deckName, DeckInterface newDeck) {
        deckList.put(deckName, newDeck);
    }

    @Override
    public void deleteDeck(String deckNameSelected) {
        deckList.remove(deckNameSelected);
    }
}