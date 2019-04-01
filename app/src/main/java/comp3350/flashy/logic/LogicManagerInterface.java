package comp3350.flashy.logic;

import java.util.ArrayList;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;


public interface LogicManagerInterface {

    Deck getDeck(String username, String deckName);

    Deck makeDeck(String deckName);

    void insertDeck(String username, Deck updated);

    void deleteDeck(String username, String deckName);

    Deck removeCard(String username, Deck curr, String cardName);

    //void deleteFlashcardInDeck(String deckName, String cardName);
    void putFlashcardInDeck(String username, String deckName, Flashcard card);

    void editFlashcard(String username, String deckName, Flashcard card);

    int queryDeckSize(String username, String deckName);

    ArrayList<Deck> getAllDecks(String username);
}