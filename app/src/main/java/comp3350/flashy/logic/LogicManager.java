package comp3350.flashy.logic;

import java.util.ArrayList;

import comp3350.flashy.application.Services;
import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.persistence.DatabaseManagement.DatabaseManager;

public class LogicManager implements LogicManagerInterface {

    private DatabaseManager database;
    private UserHandler userHandler;


    public LogicManager(){
        database = new DatabaseManager(Services.getFlashyPersistence());
        userHandler = new UserHandler(database);

    }

    public Deck makeDeck(String deckName){
        return new Deck(deckName);
    }

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
        insertDeck(username, curr);
        return curr;
    }

    @Override
    public void putFlashcardInDeck(String username, String deckName, Flashcard card) {
        Deck currDeck = database.getDeck(username, deckName);
        if(currDeck == null) {
            currDeck = makeDeck(deckName);
        }
        currDeck.addCard(card);
        database.inputDeck(username, deckName, currDeck);
    }

    @Override
    public void editFlashcard(String username, String deckName, Flashcard card) {
        Deck currDeck = database.getDeck(username, deckName);
        if(currDeck == null){currDeck = makeDeck(deckName);}
        currDeck.editCard(card);
        database.inputDeck(username, deckName,currDeck);
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
        return(new ArrayList<String>(database.getUserCollection()));
    }

    public ArrayList<String> getNames(String username){
        ArrayList<Deck> temp = getAllDecks(username);
        ArrayList result = new ArrayList<Deck>();

        for (int i  = 0; i < temp.size(); i++) {
            result.add(temp.get(i).getName());
        }

        return result;
    }

    /**
     *
     * @param username the name of the account taking the quiz
     * @param quizDeckName the name of the deck to make a quiz with
     * @return the QuizManager that the presentation layer will use to take the quiz
     *      NOTE this method will return null if the deck requested does not exist
     */
    public QuizManager startQuiz(String username, String quizDeckName){
        Deck quizDeck = this.getDeck(username, quizDeckName);
        QuizManager qMngr = null;

        if(quizDeck != null){
            qMngr = new QuizManager(username, quizDeck);
        }

        return qMngr;
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
