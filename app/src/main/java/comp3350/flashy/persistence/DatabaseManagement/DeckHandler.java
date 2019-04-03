package comp3350.flashy.persistence.DatabaseManagement;

import java.util.Collection;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.persistence.Interfaces.DeckDatabaseImplementation;
import comp3350.flashy.persistence.Translators.DataTranslationLayer;

public class DeckHandler {
    DeckDatabaseImplementation deckStorage;
    private DataTranslationLayer translationLayer = new DataTranslationLayer();

    /**
     * Constructor
     *
     * @param givenImpl
     */
    public DeckHandler(DeckDatabaseImplementation givenImpl) {
        deckStorage = givenImpl;
    }

    /**
     * puts a deck into the DB and associates it with the given username
     * @param username
     * @param identifier
     * @param inputDeck
     */
    public void inputDeck(String username, String identifier, Deck inputDeck) {
        deckStorage.inputDeck(username, identifier, translationLayer.encodeDeck(inputDeck));
    }

    /**
     * get a deck with that name from the DB (if its associated with the username)
     * @param username
     * @param identifier
     * @return
     */
    public Deck getDeck(String username, String identifier) {
        return translationLayer.decodeDeck(deckStorage.getDeck(username, identifier));
    }

    /**
     * removes a given deck from the DB (if its associated with the username)
     * @param username
     * @param identifier
     */
    public void removeDeck(String username, String identifier) {
        deckStorage.deleteDeck(username, identifier);
    }

    /**
     * Gets a collection of the decks associated with the user in the DB
     * @param username
     * @return
     */
    public Collection getDeckCollection(String username) {
        return deckStorage.getDeckCollection(username);
    }
}
