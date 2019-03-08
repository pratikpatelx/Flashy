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
        currDeck = new Deck("default");
        deckSize = currDeck.getNumCards();
        logic = new LogicManager();
        logic.insertDeck(currDeck);
        deckName = currDeck.getName();
    }


    //adds cards w/ name (DECKNAME-DECKSIZE++) as to be stored in database
    public void saveCard(String head, String content) {

        logic.putFlashcardInDeck(deckName,(deckName +"-"+ deckSize), head, content);
        deckSize= currDeck.getNumCards();
        System.out.println(deckSize);
    }

    //kyle wrote this
    public void deleteCard() {
        //logic.deleteFlashcardInDeck(deckName, (deckName +"-"+ deckSize));
        //logic.rem
        deckSize= currDeck.getNumCards();
        System.out.println(deckSize);
    }

    public String[] getContent(int index) {

        Flashcard curr = currDeck.getCard(deckName +"-"+ index);

        String[] result = new String[2];
        result[0] = curr.getQuestion();
        result[1] = curr.getAnswer();

        return result;
    }

    public void editContent(int index, String newQuestion,String newAnswer){
        Flashcard curr = logic.getDeck("default").getCard(deckName +"-"+ index);

        curr.setQuestion(newQuestion);
        curr.setAnswer(newAnswer);
    }

    public int getIndexByFlashCard(String name){
        Flashcard curr = logic.getDeck("default").getCard(name);

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

    public void setCurrDeck(String name){
        Deck newDeck = logic.getDeck(name);

        if(newDeck !=null){
            currDeck=newDeck;
            deckName = newDeck.getName();
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


    //test stub for now remove later
    public void addStub(){
        for(int i = 0;i<4;i++)
            saveCard("test"+i,"test"+i);
    }
}
