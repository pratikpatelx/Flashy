package comp3350.flashy.logic;

import java.util.ArrayList;
import java.util.List;

import comp3350.flashy.application.Services;
import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.persistence.Interfaces.DeckDatabaseImplementation;
import comp3350.flashy.persistence.Translators.DataTranslationLayer;

public class DeckManager {
    private DeckDatabaseImplementation deckStorage;
    private DataTranslationLayer translationLayer;


    public DeckManager(){
        this(Services.getDeckPersistence(), new DataTranslationLayer());
    }

    /**
     * Constructor
     *
     * @param givenImpl
     */

    public DeckManager(DeckDatabaseImplementation givenImpl, DataTranslationLayer layerImpl) {
        deckStorage = givenImpl;
        translationLayer = layerImpl;
    }

    /**
     * puts a deck into the DB and associates it with the given username
     * @param username
     * @param inputDeck
     */
    public void insertDeck(String username, Deck inputDeck) {
        deckStorage.inputDeck(username, inputDeck.getName(), translationLayer.encodeDeck(inputDeck));
    }

    /**
     * get a deck with that name from the DB (if its associated with the username)
     * @param username
     * @param identifier
     * @return
     */
    public Deck getDeck(String username, String identifier) {
        return translationLayer.decodeDeck(deckStorage.getDeck(username, identifier));
    }

    /**
     * removes a given deck from the DB (if its associated with the username)
     * @param username
     * @param identifier
     */
    public void deleteDeck(String username, String identifier) {
        deckStorage.deleteDeck(username, identifier);
    }

    /**
     * Gets a List of the decks associated with the user in the DB
     * @param username
     * @return
     */
    public List getNames(String username) {
        ArrayList<Deck> temp = new ArrayList<Deck>(deckStorage.getDeckCollection(username));
        List result = new ArrayList<String>();
        for (Deck i: temp) {
            result.add(i.getName());
        }
        return result;
    }

    /**
     * Gets the size of the specified deck, linked to the specified username
     *
     * @param username
     * @param deckName
     * @return
     */
    public int queryDeckSize(String username, String deckName) {
        Deck curr = this.getDeck(username, deckName);

        if (curr != null) {
            return curr.getNumCards();
        }
        return 0;
    }

    /**
     * Puts a flashcard with the given name into the deck, linked to the given username
     * @param username
     * @param deckName
     * @param card
     */
    public void putFlashcardInDeck(String username, String deckName, Flashcard card) {
        Deck currDeck = getDeck(username, deckName);
        currDeck.addCard(card);
        insertDeck(username, currDeck);
    }

    /**
     * Removes a card from the deck, linked to the given username
     * @param username
     * @param curr
     * @param cardName
     * @return
     */
    public Deck removeCard(String username, Deck curr, String cardName) {
        curr.deleteCard(cardName);
        insertDeck(username,curr);
        return curr;
    }


}
