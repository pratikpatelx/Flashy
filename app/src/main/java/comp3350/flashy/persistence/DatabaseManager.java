package comp3350.flashy.persistence;

import java.util.Collection;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;

public class DatabaseManager {
    DatabaseImplementation storage;

    public DatabaseManager(DatabaseImplementation implementation){
        storage = implementation;
        createDefaultData();
    }

    public void inputDeck(String identifier, Deck inputDeck) {
        storage.inputDeck(identifier, inputDeck);
    }

    public Deck getDeck(String identifier) {
        return storage.getDeck(identifier);
    }

    public Collection getDeckCollection() {
        return storage.getDeckCollection();
    }

    private void createDefaultData() {
        for (int i = 0; i < 3; i++) {
            String deckName = "DefaultDeck" + i;
            Deck tempDeck = new Deck(deckName);
            for (int j = 0; j < 5; j++) {
                tempDeck.addCard(new Flashcard(
                        deckName + "-" + j,
                        "DefaultCardQuestion" + j,
                        "DefaultCardAnswer" + j));
                inputDeck(deckName, tempDeck);
            }
        }
    }
}
