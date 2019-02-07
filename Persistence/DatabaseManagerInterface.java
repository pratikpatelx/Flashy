package Persistence;

import DomainLogic.Flashcard;

public interface DatabaseManagerInterface {

    //Deck Methods
    void inputDeck(String identifier, String[][] inputDeck);

    String[] getallDeckNames();

    void renameDeck(String identifier, String newName);

    void removeDeck(String identifier);

    //Card Methods

    void addCard(Flashcard newCard);

    void addCardToDeck(String identifier, Flashcard newCard);

    void editCard(String cardName, String newQuestion, String newAnswer);

    void removeCardFromDeck(String identifier, String cardName);

    void removeCardFromAll(String cardName);

    //Get Data Methods

    String[][] getAllCards();

    String[][] getDeckContents(String identifier);
}