package comp3350.flashy.persistence.DatabaseManagement;

import java.util.Collection;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.persistence.DatabaseImplementation;
import comp3350.flashy.persistence.Translators.DataTranslationLayer;

public class DeckHandler {
    DatabaseImplementation deckStorage;
    private DataTranslationLayer translationLayer = new DataTranslationLayer();

    public DeckHandler(DatabaseImplementation givenImpl) {
        deckStorage = givenImpl;
    }

    public void inputDeck(String username, String identifier, Deck inputDeck) {
        deckStorage.inputDeck(username, identifier, translationLayer.encodeDeck(inputDeck));
    }


    public Deck getDeck(String username, String identifier) {
        return translationLayer.decodeDeck(deckStorage.getDeck(username, identifier));
    }

    public void removeDeck(String username, String identifier) {
        deckStorage.deleteDeck(username, identifier);
    }

    public Collection getDeckCollection(String username) {
        return deckStorage.getDeckCollection(username);
    }
}
