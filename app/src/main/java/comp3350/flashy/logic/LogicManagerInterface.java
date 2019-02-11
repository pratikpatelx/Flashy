package comp3350.flashy.logic;

import comp3350.flashy.DomainLogic.Flashcard;


public interface LogicManagerInterface {
    void addFlashcard(String cardName, String question, String answer);

    void putFlashcardIntoDeck(String deckName, String cardName, String question, String answer);

    void putFlashcardIntoDeck(String deckName, Flashcard flashcard);

    void editFlashcard(String cardName, String newQuestion, String newAnswer);

    void removeFlashcardFromDeck(String deckName, String cardName);

    void removeCardFromAll(String cardName);

    String[][] shuffleDeck(String deckName);
}