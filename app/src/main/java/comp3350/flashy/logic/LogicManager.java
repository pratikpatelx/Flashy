package comp3350.flashy.logic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.persistence.DatabaseHSQLDB;
import comp3350.flashy.persistence.DatabaseManager;
import comp3350.flashy.persistence.DatabaseStub;

public class LogicManager implements LogicManagerInterface {
    private DatabaseManager database = new DatabaseManager(new DatabaseStub());
    //private DatabaseManager database = new DatabaseManager(new DatabaseHSQLDB());

    @Override
    public Deck getDeck(String deckName){
        return(database.getDeck(deckName));
    }

    @Override
    public void insertDeck(Deck updated){
        database.inputDeck(updated.getName(), updated);
    }

    @Override
    public void deleteDeck(Deck curr){
        database.removeDeck(curr.getName());
    }

    @Override
    public Deck removeCard(Deck curr, String cardName){
        curr.deleteCard(cardName);
        return curr;
    }

    @Override
    public void putFlashcardInDeck(String deckName, String cardName, String question, String answer) {
        Deck currDeck = database.getDeck(deckName);
        //System.out.println(currDeck.toString());

        if(currDeck == null){
            currDeck = new Deck(deckName);
        }

        currDeck.addCard(new Flashcard(cardName,question,answer));
        database.inputDeck(deckName, currDeck);
    }

    @Override
    public void editFlashcard(String deckName, String cardName, String newQuestion, String newAnswer) {
        Deck currDeck = database.getDeck(deckName);
        if(currDeck == null){currDeck = new Deck(deckName);}
        currDeck.editCard(new Flashcard(cardName,newQuestion,newAnswer));
        database.inputDeck(deckName,currDeck);
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
    public void printDeck(String deckName){
        Deck currDeck = database.getDeck(deckName);
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
    public int queryDeckSize(String deckName){
        Deck currDeck = database.getDeck(deckName);
        if(currDeck!=null){ return currDeck.getNumCards();}
        return 0;
    }

    @Override
    public ArrayList<Deck> getAllDecks(){
        return(new ArrayList<Deck>(database.getDeckCollection()));
        //return new ArrayList<>();
    }

    public ArrayList<String> getNames(){
        ArrayList<Deck> temp = getAllDecks();
        ArrayList result = new ArrayList<Deck>();

        for (int i  = 0; i < temp.size(); i++) {
            result.add(temp.get(i).getName());
            System.out.println(temp.get(i).getName());
        }

        return result;
    }


}
