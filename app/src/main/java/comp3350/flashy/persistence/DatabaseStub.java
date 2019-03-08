package comp3350.flashy.persistence;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Set;

import comp3350.flashy.domain.Deck;

public class DatabaseStub implements DatabaseImplementation{
    private Hashtable<String, Deck> storage;

    public DatabaseStub() {
        storage = new Hashtable();
    }

    @Override
    public void inputDeck(String identifier, Deck inputDeck) {
        storage.put(identifier, inputDeck);
    }

    @Override
    public Deck getDeck(String identifier) {
        Deck result = storage.get(identifier);
        storage.remove(identifier);
        return result;
    }

    @Override
    public Collection<String> getDeckCollection(){
        Collection result = new ArrayList();

        Set<String> keys = storage.keySet();
        for(String key: keys) {
            result.add(storage.get(key));
        }

        return result;
    }
}
