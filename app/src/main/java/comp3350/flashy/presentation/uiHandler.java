package comp3350.flashy.presentation;

import java.util.ArrayList;
import java.util.Objects;

import comp3350.flashy.domain.FillInTheBlanksFlashcard;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.MultipleChoiceFlashcard;
import comp3350.flashy.logic.LogicManager;
import comp3350.flashy.logic.QuizManager;

public class uiHandler {

    private LogicManager logic;
    private String username;

    private QuizManager quiz;
    private int quizCardType;

    private Deck currDeck;
    private String deckName;
    private static int deckSize = 0; // this is to hold max index

    //TODO add checks and error handling.

    public uiHandler() {
        logic = new LogicManager();
    }

    //adds cards w/ name (DECKNAME-DECKSIZE++) as to be stored in database
    public void saveCard(String head, String content, int type) {
        switch(type){
            case 0: logic.putFlashcardInDeck(username, deckName,new Flashcard((deckName +"-"+ deckSize), head, content));
            //deckSize++;
            break;
            case 1: logic.putFlashcardInDeck(username, deckName,new FillInTheBlanksFlashcard((deckName +"-"+ deckSize), head, content," "));
            break;
        }
    }

    public void saveMCCard(String question, ArrayList<String> answer) {
        logic.putFlashcardInDeck(username,deckName, new MultipleChoiceFlashcard((deckName +"-"+ deckSize), question, answer));
        deckSize++;
    }

    public void deleteCard(int index) {
        logic.removeCard(username,currDeck, (currDeck+"-"+index));
        deckSize--;
    }

    //this returns the the head and body of the flashcard
    public String[] getContent(int index) {
        Flashcard curr;
        String[] result = new String[3];


        if(index != -1) {
            curr = currDeck.getCard(deckName + "-" + index);
        } else {
            curr = quiz.getNextCard();
            quizCardType = Integer.parseInt(curr.getCardType());
            if(curr.isFillInTheBlanksFlashcard()){
                FillInTheBlanksFlashcard temp = (FillInTheBlanksFlashcard) curr;
                result[2] = temp.getFirstPart();

            }


        }


        result[0] = curr.getQuestion();
        result[1] = curr.getAnswer();

        return result;
    }

    public ArrayList<String> getMCContent(int index){
        ArrayList<String> result = new ArrayList<>();


        if(index != -1){
            MultipleChoiceFlashcard card = (MultipleChoiceFlashcard) currDeck.getCard(deckName + "-" + index);
            result.add(card.getQuestion());
            result.addAll(card.getAnswers());
        }else {
            MultipleChoiceFlashcard card = (MultipleChoiceFlashcard) quiz.getNextCard();

            result.add(card.getQuestion());
            result.add(card.getAnswer());
            result.addAll(card.getAnswers());
        }

        return result;
    }

    public int getFlashcardTypeByIndex(int index){
        Flashcard curr = currDeck.getCard(deckName + "-" + index);

        return Integer.parseInt(curr.getCardType());
    }



    //note to self: do a better job...
    public int getIndexByFlashCard(String name){
        Flashcard curr = currDeck.getCard(name);

        //change this when changing (deckName + index) to (deckName +"-"+ index)
        String[] token = curr.getCardName().split("-");
        int index = Integer.parseInt(token[1]);

        return index;
    }

    public ArrayList<String> getMenuData(){
        ArrayList<String> result = new ArrayList<String>();

        result.add("Standard");
        result.add("Fill in the blank");
        result.add("Multiple choice");

        return result;

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

    public String getUsername(){
        return username;
    }

    public ArrayList<String> getNames(){
        return logic.getNames(username);
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

    public void deleteUser(String user){
        logic.removeUserFromDatabase(user);
    }

    public ArrayList<String> getAllProfileNames(){
        return logic.getAllProfiles();
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


    public int getCurrentType(){
        return quizCardType;
    }

    public int getNextCardType(){
        return quiz.getNextCardType();
    }

}
