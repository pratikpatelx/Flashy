package comp3350.flashy.persistence;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import comp3350.flashy.domain.Deck;

public class DatabaseStub implements DatabaseImplementation{
    private Hashtable<String, Hashtable<String, Deck>> storage;
    private Hashtable<String, String> userList;

    public DatabaseStub() {
        storage = new Hashtable();
        userList = new Hashtable();
    }

    @Override
    //was putting new hashtable in everytime we added a new deck, overwriting the Decklist
    //see inputUser
    public void inputDeck(String username, String identifier, Deck inputDeck) {
        //Hashtable deckList = new Hashtable<>();
        //deckList.put(identifier, inputDeck);
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

    //to fix this, decklist now created w/user.
    @Override
    public void inputUser(String username, String password) {
        userList.put(username, password);
        //storage.put(username, null);
        storage.put(username, new Hashtable<String, Deck>());
    }

    @Override
    public String getUserPassword(String username) {
        return userList.get(username);
    }

    @Override
    public void removeUser(String username) {
        userList.remove(username);
        storage.remove(username);
    }
}
