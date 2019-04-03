package comp3350.flashy.persistence.Interfaces;

import java.util.Collection;

import comp3350.flashy.domain.Deck;

public interface DeckDatabaseImplementation {

    /*
    Deck based methods
     */
    void inputDeck(String username, String identifier, Deck inputDeck);

    Deck getDeck(String username, String identifier);

    void deleteDeck(String username, String identifier);

    Collection getDeckCollection(String username);
}
