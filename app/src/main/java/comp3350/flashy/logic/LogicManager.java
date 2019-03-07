package comp3350.flashy.logic;

import java.util.ArrayList;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.persistence.DatabaseManager;

public class LogicManager implements LogicManagerInterface {
    private DatabaseManager database = new DatabaseManager();

    public Deck getDeck(String deckName){
        return(database.getDeck(deckName));
    }

    public void insertDeck(Deck updated){
        database.inputDeck(updated.getName(), updated);
    }

    @Override
    public void putFlashcardInDeck(String deckName, String cardName, String question, String answer) {
        Deck currDeck = database.getDeck(deckName);
        if(currDeck == null){currDeck = new Deck(deckName);}
        currDeck.addCard(new Flashcard(cardName,question,answer));
        database.inputDeck(deckName, currDeck);
    }

    /**
    * Copying a flashcard from one deck to another, or a new one
    *
    * SUBJECT TO CHANGE BASED ON WHATEVER NAMING CONVENTION
    * ENDS UP BEING USED FOR CARD NAMES
    *
    */

    @Override
    public void copyFlashcard(Deck orig, String destDeck, String cardName) {
        Flashcard copy = orig.getCard();
        Deck dest = database.getDeck(destDeck);
        if(dest == null){dest = new Deck(destDeck);}
        dest.addCard(copy);
        database.inputDeck(destDeck,dest);
    } //

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
        return(new ArrayList<Deck>(database.getAllDecks()));
    }

}
