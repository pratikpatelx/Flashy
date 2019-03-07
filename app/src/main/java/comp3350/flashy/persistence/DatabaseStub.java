package comp3350.flashy.persistence;
import java.util.Hashtable;

import comp3350.flashy.DomainLogic.Deck;

public class DatabaseStub implements DatabaseImplementation{
    private Hashtable<String, Deck> storage;

    public DatabaseStub() {
        storage = new Hashtable();
    }

    public void inputDeck(String identifier, Deck inputDeck) {
        storage.put(identifier, inputDeck);
    }

    public Deck getDeck(String identifier) {
        Deck result = storage.get(identifier);
        storage.remove(identifier);
        return result;
    }
}