package comp3350.flashy.logic;

import java.util.ArrayList;

import comp3350.flashy.domain.Deck;


public interface LogicManagerInterface {

    Deck getDeck(String username, String deckName);

    Deck makeDeck(String deckName);

    void insertDeck(String username, Deck updated);

    void deleteDeck(String username, String deckName);

    Deck removeCard(String username, Deck curr, String cardName);

    void putFlashcardInDeck(String username, String deckName, String cardName, String question, String answer);

    //void deleteFlashcardInDeck(String deckName, String cardName);

    void editFlashcard(String username, String deckName, String cardName, String newQuestion, String newAnswer);

    void printDeck(String username, String deckName);

    int queryDeckSize(String username, String deckName);

    ArrayList<Deck> getAllDecks(String username);
}