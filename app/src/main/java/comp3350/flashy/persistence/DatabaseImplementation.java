package comp3350.flashy.persistence;

import comp3350.flashy.DomainLogic.Deck;

public interface DatabaseImplementation {
    void inputDeck(String identifier, Deck inputDeck);

    Deck getDeck(String identifier);
}
