package comp3350.flashy.persistence.DatabaseManagement;

import org.hsqldb.DatabaseManager;

import java.util.Collection;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.persistence.Codifiers.DataTranslationLayer;

public class DeckDatabaseManager extends DatabaseManager {
    private DatabaseImplementation storage;
    private DataTranslationLayer translationLayer;

    public DeckDatabaseManager(DatabaseImplementation implementation){
        storage = implementation;
        translationLayer = new DataTranslationLayer();
        createDefaultData();
    }

    public void inputDeck(String username, String identifier, Deck inputDeck) {
        storage.inputDeck(username,identifier, translationLayer.encodeDeck(inputDeck));
    }

    public Deck getDeck(String username, String identifier) {
        return translationLayer.decodeDeck(storage.getDeck(username, identifier));
    }

    public void removeDeck(String username, String identifier) {
        storage.deleteDeck(username, identifier);
    }

    public Collection getDeckCollection(String username) {
        return storage.getDeckCollection(username);
    }

    private void createDefaultData() {
        storage.inputUser("","");

        for (int i = 0; i < 3; i++) {
            String deckName = "DefaultDeck" + i;
            Deck tempDeck = new Deck(deckName);
            for (int j = 0; j < 5; j++) {
                tempDeck.addCard(new Flashcard(
                        deckName + "-" + j,
                        "DefaultCardQuestion" + j,
                        "DefaultCardAnswer" + j));
            }
            inputDeck("" ,deckName, tempDeck);
        }
    }
}