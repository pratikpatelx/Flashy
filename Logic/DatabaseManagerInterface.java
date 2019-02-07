package Logic;

import Persistence.Flashcard;

public interface DatabaseManagerInterface {

    //Deck Methods

    void renameDeck(String identifier, String newName);

    void removeDeck(String identifier);

    //Card Methods

    void addCard(Flashcard newCard);

    void addCardToDeck(String identifier, Flashcard newCard);

    void removeCardFromDeck(String identifier, String cardName);

    void editCard(String cardName, String newQuestion, String newAnswer);

    //Get Data Methods

    String[][] getAllCards();

    String[][] getDeckContents(String identifier);
}