package comp3350.flashy.persistence;

import java.util.Collection;

import comp3350.flashy.domain.Deck;

public interface DatabaseImplementation {

    /*
    Deck based methods
     */
    void inputDeck(String username, String identifier, Deck inputDeck);

    Deck getDeck(String username, String identifier);

    void deleteDeck(String username, String identifier);

    Collection getDeckCollection(String username);

    /*
    User based methods
     */
    void inputUser(String username, String password);

    String getUserPassword(String username);

    void removeUser(String username);

    Collection<String> getUserCollection();
}
