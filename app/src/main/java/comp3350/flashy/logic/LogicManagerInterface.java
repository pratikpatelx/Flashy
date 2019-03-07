package comp3350.flashy.logic;

import comp3350.flashy.domain.Deck;


public interface LogicManagerInterface {

    Deck getDeck(String deckName);

    void insertDeck(Deck updated);

    void putFlashcardInDeck(String deckName, String cardName, String question, String answer);

    void copyFlashcard(Deck orig, String destDeck, String cardName);

    void editFlashcard(String deckName, String cardName, String newQuestion, String newAnswer);

    void printDeck(String deckName);

    int queryDeckSize(String deckName);
}