package comp3350.flashy.persistence;

import comp3350.flashy.DomainLogic.Deck;
import comp3350.flashy.DomainLogic.Flashcard;

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

    private void createDefaultData() {
        for (int i = 0; i < 10; i++) {
            String deckName = "DefaultDeck" + i;
            Deck tempDeck = new Deck(deckName);
            tempDeck.addCard(new Flashcard(
                    "DefaultCardName" + i,
                    "DefaultCardQuestion" + i,
                    "DefaultCardAnswer" + i));
            inputDeck(deckName, tempDeck);
        }
    }
}
