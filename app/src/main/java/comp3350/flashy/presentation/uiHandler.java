package comp3350.flashy.presentation;

import java.util.ArrayList;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.logic.LogicManager;

public class uiHandler {

    private LogicManager logic;
    private Deck currDeck;

    private String deckName;
    private int deckSize; // this is to hold max index

    //TODO add checks and error handling.

    public uiHandler() {
        logic = new LogicManager();
    }


    //adds cards w/ name (DECKNAME-DECKSIZE++) as to be stored in database
    public void saveCard(String head, String content) {
        logic.putFlashcardInDeck(deckName,(deckName +"-"+ deckSize), head, content);
        deckSize= currDeck.getNumCards();
    }

    public void deleteCard(int index) {
        logic.removeCard(currDeck, (currDeck+"-"+index));
        deckSize = currDeck.getNumCards();
    }

    //this returns the the head and body of the flashcard
    public String[] getContent(int index) {

        Flashcard curr = currDeck.getCard(deckName +"-"+ index);

        String[] result = new String[2];
        result[0] = curr.getQuestion();
        result[1] = curr.getAnswer();

        return result;
    }

    public void editContent(int index, String newQuestion,String newAnswer){
        logic.editFlashcard(currDeck.getName(),(currDeck.getName()+"-"+index),newQuestion,newAnswer);
    }

    //this will parse the current index of a flash card.
    public int getIndexByFlashCard(String name){
        Flashcard curr = currDeck.getCard(name);

        //change this when changing (deckName + index) to (deckName +"-"+ index)
        String[] token = curr.getCardName().split("-");
        int index = Integer.parseInt(token[1]);

        return index;
    }


    public int getDeckSize() {
        //change this to get deck size
        return deckSize;
    }

    public Deck getCurrDeck(){
        return currDeck;
    }

    public String getDeckName(){
        return currDeck.getName();
    }

    public void setCurrDeck(String name){
        Deck newDeck = logic.getDeck(name);

        if(newDeck !=null){
            currDeck=newDeck;
            deckName = newDeck.getName();
            deckSize = currDeck.getNumCards();
            System.out.println("found deck");
        }else {
            currDeck = new Deck(name);
            logic.insertDeck(currDeck);
            deckName = currDeck.getName();
            deckSize = currDeck.getNumCards();
            System.out.println("created new deck");
        }
    }

    public ArrayList<String> getNames(){
        return logic.getNames();
    }

    public void deleteDeck(){
        logic.deleteDeck(currDeck);
    }


}
