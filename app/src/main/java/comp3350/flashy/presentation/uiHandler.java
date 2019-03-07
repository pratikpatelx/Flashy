package comp3350.flashy.presentation;

import comp3350.flashy.DomainLogic.Flashcard;
import comp3350.flashy.logic.LogicManager;
import comp3350.flashy.persistence.DatabaseManager;

public class uiHandler {

    private LogicManager logic;
    private DatabaseManager db;

    private String deckName = "c";
    //this will be the deckname

    //deck name deckname-index
    private int dbSize; // this is to hold max index

    //TODO add checks and error handling.

    public uiHandler() {
        dbSize = 1;
        logic = new LogicManager();
        db = logic.getDatabase();
    }

    public DatabaseManager getDb(){
        return this.db;
    }


    //adds cards w/ name (DECKNAME-DECKSIZE++) as to be stored in database
    public void saveCard(String head, String content) {

        logic.addFlashcard((deckName + dbSize), head, content);
        dbSize++;
    }

    public String[] getContent(int index) {
        Flashcard curr = logic.getCard((deckName + index));

        String[] result = new String[2];
        result[0] = curr.getQuestion();
        result[1] = curr.getAnswer();

        return result;
    }

    public void editContent(int index, String newQuestion,String newAnswer){
        Flashcard curr = logic.getCard((deckName + index));

        curr.setQuestion(newQuestion);
        curr.setAnswer(newAnswer);
    }

    public int getIndexByFlashCard(String name){
        Flashcard curr = logic.getCard(name);

        //change this when changing (deckName + index) to (deckName +"-"+ index)
        int index = Integer.parseInt(curr.getCardName().substring(1));

        return index;
    }


    public int getDeckSize() {
        //change this to get deck size
        return dbSize;
    }


    //test stub for now remove later
    public void addStub(){
        for(int i = 0;i<4;i++)
            saveCard("test"+i,"test"+i);
    }


}
