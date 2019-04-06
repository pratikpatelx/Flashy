package comp3350.flashy.logic;

import java.util.ArrayList;
import java.util.Collection;

import comp3350.flashy.application.Services;
import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.persistence.Interfaces.DeckDatabaseImplementation;
import comp3350.flashy.persistence.Translators.DataTranslationLayer;

public class DeckManager {
    private DeckDatabaseImplementation deckStorage;
    private DataTranslationLayer translationLayer;


    public DeckManager(){this(Services.getDeckPersistence(), new DataTranslationLayer());
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

    public Deck makeDeck(String deckName) {
        return new Deck(deckName);
    }

    /**
     * puts a deck into the DB and associates it with the given username
     * @param username
     * @param identifier
     * @param inputDeck
     */
    public void insertDeck(String username, String identifier, Deck inputDeck) {
        deckStorage.inputDeck(username, identifier, translationLayer.encodeDeck(inputDeck));
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
     * Gets a collection of the decks associated with the user in the DB
     * @param username
     * @return
     */
    public Collection getAllDecks(String username) {
        return deckStorage.getDeckCollection(username);
    }

    public Collection getNames(String username) {
        ArrayList<Deck> temp = new ArrayList<Deck>(getAllDecks(username));
        Collection result = new ArrayList<Deck>();

        for (int i = 0; i < temp.size(); i++) {
            result.add(temp.get(i).getName());
        }

        return result;
    }

    public int queryDeckSize(String username, String deckName) {
        Deck currDeck = getDeck(username, deckName);
        if (currDeck != null) {
            return currDeck.getNumCards();
        }
        return 0;
    }

    public void putFlashcardInDeck(String username, String deckName, Flashcard card) {
        Deck currDeck = getDeck(username, deckName);
        if (currDeck == null) {
            currDeck = makeDeck(deckName);
        }
        currDeck.addCard(card);
        insertDeck(username, deckName, currDeck);
    }

    public Deck removeCard(String username, Deck curr, String cardName) {
        curr.deleteCard(cardName);
        insertDeck(username, curr.getName(), curr);
        return curr;
    }



    public void editFlashcard(String username, String deckName, Flashcard card) {
        Deck currDeck = getDeck(username, deckName);
        if (currDeck == null) {
            currDeck = makeDeck(deckName);
        }
        currDeck.editCard(card);
        insertDeck(username, deckName, currDeck);
    }


    /**
     * @param username     the name of the account taking the quiz
     * @param quizDeckName the name of the deck to make a quiz with
     * @return the QuizManager that the presentation layer will use to take the quiz
     * NOTE this method will return null if the deck requested does not exist
     */
    public QuizManager startQuiz(String username, String quizDeckName) {
        Deck quizDeck = getDeck(username, quizDeckName);
        QuizManager qMngr = null;

        if (quizDeck != null) {
            qMngr = new QuizManager(username, quizDeck);
        }

        return qMngr;
    }
}
