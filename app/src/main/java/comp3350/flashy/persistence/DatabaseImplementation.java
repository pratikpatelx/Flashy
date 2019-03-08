package comp3350.flashy.persistence;

import java.util.ArrayList;

import comp3350.flashy.domain.Deck;

public interface DatabaseImplementation {
    void inputDeck(String identifier, Deck inputDeck);

    Deck getDeck(String identifier);
    ArrayList<String> getAllDeckNames();
}
