package comp3350.flashy.logic;

import java.util.ArrayList;

import comp3350.flashy.domain.Deck;


public interface LogicManagerInterface {

    Deck getDeck(String deckName);

    void insertDeck(Deck updated);

    void deleteDeck(Deck curr);

    Deck removeCard(Deck curr, int index);

    void putFlashcardInDeck(String deckName, String cardName, String question, String answer);

    //void deleteFlashcardInDeck(String deckName, String cardName);

    void editFlashcard(String deckName, String cardName, String newQuestion, String newAnswer);

    void printDeck(String deckName);

    int queryDeckSize(String deckName);

    ArrayList<Deck> getAllDecks();
}