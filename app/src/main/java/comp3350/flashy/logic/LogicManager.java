package comp3350.flashy.logic;

import java.util.ArrayList;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.persistence.DatabaseImplementations.DatabaseHSQLDB;
import comp3350.flashy.persistence.DatabaseImplementation;
import comp3350.flashy.persistence.DatabaseManagement.DatabaseManager;

public class LogicManager implements LogicManagerInterface {
    private DatabaseImplementation databaseType = new DatabaseHSQLDB();
    //private DatabaseImplementation databaseType = new DatabaseStub();
    private DatabaseManager database = new DatabaseManager(databaseType);
    private UserHandler userHandler = new UserHandler(database);

    @Override
    public Deck getDeck(String username, String deckName){
        return(database.getDeck(username, deckName));
    }

    @Override
    public void insertDeck(String username, Deck updated){
        database.inputDeck(username, updated.getName(), updated);
    }

    @Override
    public void deleteDeck(String username, String deckName){
        database.removeDeck(username, deckName);
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

    public ArrayList<String> getAllProfiles(){
        System.out.println(userDatabase.getUserCollection());
        return(new ArrayList<String>(userDatabase.getUserCollection()));
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

    public ArrayList<String> getProfileNames(){
        ArrayList<String> temp = getAllProfiles();
        System.out.println(getAllProfiles());
        ArrayList result = new ArrayList<Deck>();

        for (int i  = 0; i < temp.size(); i++) {
            result.add(temp.get(i));
            System.out.println(temp.get(i));
        }

        return result;
    }


    //TODO ADD ARGUMENT CONVENTION REQUIREMENTS
    public boolean addUserToDatabase(String username, String password){
        return userHandler.addUser(username, password);
    }

    public boolean removeUserFromDatabase(String username){
        return userHandler.removeUser(username);
    }

    public boolean verifyUserPassword(String username, String password){
        return userHandler.verifyUser(username,password);
    }


}
