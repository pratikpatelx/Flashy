package comp3350.flashy.persistence;

import java.util.Collection;

import comp3350.flashy.domain.Deck;

public abstract class DatabaseManager {

    /*
    Deck Methods
     */
    abstract public void inputDeck(String username, String identifier, Deck inputDeck);

    abstract public Deck getDeck(String username, String identifier);

    abstract public void removeDeck(String username, String identifier);

    abstract public Collection getDeckCollection(String username);

    /*
    User Methods
     */
    abstract void inputUser (String username, String password);

    abstract String getUserPassword (String username);

    abstract void removeUser (String username);
}
