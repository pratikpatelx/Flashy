package comp3350.flashy.logic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.persistence.DatabaseHSQLDB;
import comp3350.flashy.persistence.DatabaseImplementation;
import comp3350.flashy.persistence.DatabaseStub;
import comp3350.flashy.persistence.DeckDatabaseManager;
import comp3350.flashy.persistence.UserDatabaseManager;

public class LogicManager implements LogicManagerInterface {
    //private DatabaseImplementation databaseType = new DatabaseHSQLDB();
    private DatabaseImplementation databaseType = new DatabaseStub();
    private DeckDatabaseManager database = new DeckDatabaseManager(databaseType);
    private UserDatabaseManager userDatabase = new UserDatabaseManager(databaseType);
    private UserHandler userHandler = new UserHandler(userDatabase);

    @Override
    public Deck getDeck(String username, String deckName){
        return(database.getDeck(username, deckName));
    }

    @Override
    public void insertDeck(String username, Deck updated){
        database.inputDeck(username, updated.getName(), updated);
    }

    @Override
    public void deleteDeck(String username, Deck curr){
        database.removeDeck(username, curr.getName());
    }

    @Override
    public Deck removeCard(String username, Deck curr, String cardName){
        curr.deleteCard(cardName);
        return curr;
    }

    @Override
    public void putFlashcardInDeck(String username, String deckName, String cardName, String question, String answer) {
        Deck currDeck = database.getDeck(username, deckName);
        //System.out.println(currDeck.toString());

        if(currDeck == null){
            currDeck = new Deck(deckName);
        }

        currDeck.addCard(new Flashcard(cardName,question,answer));
        database.inputDeck(username, deckName, currDeck);
    }

    @Override
    public void editFlashcard(String username, String deckName, String cardName, String newQuestion, String newAnswer) {
        Deck currDeck = database.getDeck(username, deckName);
        if(currDeck == null){currDeck = new Deck(deckName);}
        currDeck.editCard(new Flashcard(cardName,newQuestion,newAnswer));
        database.inputDeck(username, deckName,currDeck);
    }

    /**
     * printDeck()
     *
     * @param deckName
     *  The name of the deck to be printed
     *
     *  This method is primarily for testing. It outputs the contents of a deck from
     * The database.
     */
    public void printDeck(String username, String deckName){
        Deck currDeck = database.getDeck(username, deckName);
        System.out.println(currDeck.toString());
    }

    /**
     * queryDeckMethod()
     *
     * @param deckName
     *      The name of the deck we want to know the size of
     *
     * @return
     *      The number of cards in the deck
     *
     * This method is primarily for testing purposes, it returns the size of the
     * deck requested
     */
    public int queryDeckSize(String username, String deckName){
        Deck currDeck = database.getDeck(username, deckName);
        if(currDeck!=null){ return currDeck.getNumCards();}
        return 0;
    }

    @Override
    public ArrayList<Deck> getAllDecks(String username){
        return(new ArrayList<Deck>(database.getDeckCollection(username)));
    }

    public ArrayList<String> getNames(String username){
        ArrayList<Deck> temp = getAllDecks(username);
        System.out.println(getAllDecks(username));
        ArrayList result = new ArrayList<Deck>();

        for (int i  = 0; i < temp.size(); i++) {
            result.add(temp.get(i).getName());
            System.out.println(temp.get(i).getName());
        }

        return result;
    }

    public void addUsertoDatabase(String username, String password){
        userHandler.addUser(username, password);
    }


}
