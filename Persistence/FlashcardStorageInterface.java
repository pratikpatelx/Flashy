package Persistence;

import DomainLogic.Flashcard;

interface FlashcardStorageInterface {
    void addCard(Flashcard newCard);

    void addCardToDeck(String identifier, Flashcard newCard);

    void removeCardFromDeck(String identifier, String cardName);

    void removeCardFromAll(String cardName);

    void editCard(String cardName, String newQuestion, String newAnswer);
}
