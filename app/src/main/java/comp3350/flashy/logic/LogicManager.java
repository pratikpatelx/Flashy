package comp3350.flashy.logic;

import java.util.ArrayList;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.persistence.DatabaseManager;
import comp3350.flashy.persistence.DatabaseStub;

public class LogicManager implements LogicManagerInterface {
    private DatabaseManager database = new DatabaseManager(new DatabaseStub());

    @Override
    public Deck getDeck(String deckName){
        Deck currDeck = database.getDeck(deckName);
        database.inputDeck(deckName, currDeck);
        return(currDeck);
    }

    @Override
    public void insertDeck(Deck updated){
        Deck old = database.getDeck(updated.getName());
        database.inputDeck(updated.getName(), updated);
    }

    @Override
    public void deleteDeck(Deck curr){
        database.getDeck(curr.getName());
    }

    @Override
    public Deck removeCard(Deck curr, int index){
        System.out.println(curr);
        curr.deleteCard(curr.getName()+"-"+index);
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
        if(currDeck == null){
            currDeck = new Deck(deckName);
        }
        currDeck.editCard(new Flashcard(cardName,newQuestion,newAnswer));
        database.inputDeck(deckName, currDeck);
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
        int num = 0;
        if(currDeck!=null) {
            num = currDeck.getNumCards();
            database.inputDeck(currDeck.getName(), currDeck);
        }
        return num;
    }

    @Override
    public ArrayList<Deck> getAllDecks(){
        return(new ArrayList<Deck>(database.getDeckCollection()));
        //return new ArrayList<>();
    }

    public ArrayList<String> getNames(){
        ArrayList<Deck> temp = getAllDecks();
        ArrayList result = null;

        for (int i  = 0; i < temp.size(); i++) {
            result.add(temp.get(i).getName());
        }

        return result;
    }


}
