package comp3350.flashy.persistence;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Set;

import comp3350.flashy.DomainLogic.Deck;

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
    public Collection getDeckCollection() {
        Collection result = null;
        ArrayList temp = new ArrayList();

        /*
        get each key in the hashtable and put the corresponding Deck into an arraylist
         */
        Set<String> keys = storage.keySet();
        for(String key: keys){
            temp.add(storage.get(key));
        }

        result.add(temp);
        return result;
    }
}