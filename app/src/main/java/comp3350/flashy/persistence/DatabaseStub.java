package comp3350.flashy.persistence;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import comp3350.flashy.domain.Deck;

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

    public ArrayList<String> getAllDeckNames(){
        ArrayList<String> names = new ArrayList<String>();

        for(Map.Entry<String,Deck> entry : storage.entrySet()){
           names.add(entry.getKey());
        }

        return names;
    }
}
