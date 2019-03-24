package comp3350.flashy.persistence.DatabaseImplementations;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Set;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.persistence.DatabaseImplementation;

public class DatabaseStub implements DatabaseImplementation {
    private Hashtable<String, Hashtable<String, Deck>> storage;
    private Hashtable<String, String> userList;

    public DatabaseStub() {
        storage = new Hashtable();
        userList = new Hashtable();
    }

    @Override
    //TODO ADD CHECK FOR NULL USER
    public void inputDeck(String username, String identifier, Deck inputDeck) {
        storage.get(username).put(identifier,inputDeck);
    }

    @Override
    public Deck getDeck(String username, String identifier) {
        Deck resultDeck = null;
        Hashtable userDecks = storage.get(username);
        if (userDecks != null){
            resultDeck = (Deck) userDecks.get(identifier);
        }

        return resultDeck;
    }

    @Override
    public void deleteDeck(String username, String identifier) {
        Hashtable userDecks = storage.get(username);
        if (userDecks != null){
            userDecks.remove(identifier);
        }
    }

    @Override
    public Collection<String> getDeckCollection(String username){
        Hashtable userDecks = storage.get(username);
        Collection result = new ArrayList();

        Set<String> keys = userDecks.keySet();
        for(String key: keys) {
            result.add(userDecks.get(key));
        }

        return result;
    }

    @Override
    public void inputUser(String username, String password) throws IllegalArgumentException {

        if(userList.containsKey(username)){
            throw new IllegalArgumentException();
        }else {
            userList.put(username, password);
            storage.put(username, new Hashtable<String, Deck>());
        }

    }

    @Override
    public String getUserPassword(String username) throws IllegalArgumentException {
        if(userList.containsKey(username))
            return userList.get(username);
        else
            throw new IllegalArgumentException();
    }

    @Override
    public void removeUser(String username) {
        userList.remove(username);
        storage.remove(username);
    }
}
