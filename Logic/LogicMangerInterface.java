package Logic;

import DomainLogic.Flashcard;

public interface LogicMangerInterface {
    void addFlashcard(String cardName, String question, String answer);

    void putFlashcardIntoDeck(String deckName, String cardName, String question, String answer);

    void putFlashcardIntoDeck(String deckName, Flashcard flashcard);

    void editFlashcard(String cardName, String newQuestion, String newAnswer);

    void removeFlashcardFromDeck(String deckName, String cardName);

    String[][] shuffleDeck(String deckname);
}