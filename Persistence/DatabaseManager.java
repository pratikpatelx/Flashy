package Persistence;

import java.util.ArrayList;
import java.util.Hashtable;

import DomainLogic.Flashcard;

public class DatabaseManager {

    Hashtable<String, ArrayList> storage;

    //The Oracle Deck contains all flashcards currently in existence.
    String defaultDeck = "THE_ORACLE_DECK";

    /**
     * Constructors
     */

    public DatabaseManager() {
        storage = new Hashtable<>();
    }

    /**
     * Mutators
     */

    public void setStorage(Hashtable<String, ArrayList> newStorage) {
        this.storage = newStorage;
    }

    public Hashtable getStorage() {
        return storage;
    }

    //Deck Methods
    public void inputDeck(String identifier, String[][] inputDeck) {
    }

    String[] getallDeckNames() {
        return null;
    }

    public void renameDeck(String identifier, String newName) {
    }

    public void removeDeck(String identifier) {
    }

    //Card Methods

    public void addCard(Flashcard newCard) {
    }

    public void addCardToDeck(String identifier, Flashcard newCard) {
    }

    public void editCard(String cardName, String newQuestion, String newAnswer) {
    }

    public void removeCardFromDeck(String identifier, String cardName) {
    }

    public void removeCardFromAll(String cardName) {
    }

    //Get Data Methods

    public String[][] getAllCards() {
        return null;
    }

    public String[][] getDeckContents(String identifier) {
        return null;
    }
}
