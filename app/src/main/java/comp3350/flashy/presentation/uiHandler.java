package comp3350.flashy.presentation;

import java.util.ArrayList;

import comp3350.flashy.domain.FillInTheBlanksFlashcard;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.domain.Deck;
import comp3350.flashy.logic.LogicManager;
import comp3350.flashy.logic.QuizManager;

public class uiHandler {

    private LogicManager logic;
    private String username;
    private QuizManager quiz;

    private Deck currDeck;
    private String deckName;
    private int deckSize; // this is to hold max index

    //TODO add checks and error handling.

    public uiHandler() {
        logic = new LogicManager();

    }


    //adds cards w/ name (DECKNAME-DECKSIZE++) as to be stored in database
    public void saveCard(String head, String content) {
        logic.putFlashcardInDeck(username,deckName,(deckName +"-"+ deckSize), head, content);
        deckSize= currDeck.getNumCards();
    }

    public void deleteCard(int index) {
        logic.removeCard(username,currDeck, (currDeck+"-"+index));
        deckSize = currDeck.getNumCards();
    }

    //this returns the the head and body of the flashcard
    public String[] getContent(int index) {
        Flashcard curr;


        if(index != -1)
            curr = currDeck.getCard(deckName +"-"+ index);
        else
            curr = quiz.getNextCard();



        String[] result = new String[2];
        result[0] = curr.getQuestion();
        result[1] = curr.getAnswer();

        return result;
    }



    //note to self: do a better job...
    public int getIndexByFlashCard(String name){
        Flashcard curr = currDeck.getCard(name);

        //change this when changing (deckName + index) to (deckName +"-"+ index)
        String[] token = curr.getCardName().split("-");
        int index = Integer.parseInt(token[1]);

        return index;
    }


    public int getDeckSize() {
        return deckSize;
    }

    public Deck getCurrDeck(){
        return currDeck;
    }

    public String getDeckName(){
        return currDeck.getName();
    }

    public void setCurrDeck(String name){
        Deck newDeck = logic.getDeck(username,name);

        if(newDeck !=null){
            currDeck=newDeck;
            deckName = newDeck.getName();
            deckSize = currDeck.getNumCards();
            System.out.println("found deck");
        }else {
            currDeck = new Deck(name);
            logic.insertDeck(username,currDeck);
            deckName = currDeck.getName();
            deckSize = currDeck.getNumCards();
            System.out.println("created new deck");
        }
    }

    public void setUsername(String name){
        username = name;
    }

    public ArrayList<String> getNames(){
        return logic.getNames(username);
    }

    public ArrayList<String> getAllProfileNames(){
        return logic.getProfileNames();
    }

    public ArrayList<Flashcard> getAllCards(){
        currDeck = logic.getDeck(username,deckName);

        return new ArrayList<Flashcard>(currDeck.getFlashcards());
    }

    public void deleteDeck(String dName) {
        logic.deleteDeck(username,dName);
    }

    public boolean registerUser(String username, String password){
        return logic.addUserToDatabase(username,password);
    }

    public boolean Verified(String username, String password){
        return logic.verifyUserPassword(username,password);
    }

    public void startQuiz() {
        quiz = logic.startQuiz(username,deckName);
    }

    public void setAnswer(boolean correct){
        quiz.evaluateAnswer(correct);
    }

    public int[] getQuizInfo(){
        int[] result = new int[3];

        result[0] = quiz.getDeckSize();
        result[1] = quiz.getCorrect();
        result[2] = quiz.getWrong();

        return result;
    }

    public boolean isQuizDone(){
        return quiz.done();
    }


}
