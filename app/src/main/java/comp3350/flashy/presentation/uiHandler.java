package comp3350.flashy.presentation;

import comp3350.flashy.DomainLogic.Flashcard;
import comp3350.flashy.logic.LogicManager;
import comp3350.flashy.persistence.DatabaseManager;

public class uiHandler {

    private int index; // for now, we will use the index to name the flashcards within the master deck
    private LogicManager logic;
    private DatabaseManager db;
    //implemented with string id, should be integer.

    private String cardName = "c";
    private int dbSize; // this is to hold max index
    //change dbsize to deck size and get deck size for specific viewer.

    //TODO add checks and error handling.

    public uiHandler() {
        index = 1;
        dbSize = 1;
        logic = new LogicManager();
        db = logic.getDatabase();
    }

    public DatabaseManager getDb(){
        return this.db;
    }

    public void saveCard(String head, String content) {

        logic.addFlashcard((cardName + dbSize), head, content);
        //flashCardDB.addCard(new Flashcard((cardName + dbSize), head, content));
        dbSize++;
    }

    public String[] getContent() {
        //Flashcard curr = flashCardDB.getCard((cardName + index));

        Flashcard curr = logic.getCard((cardName + index));
        System.out.println(index);

        String[] result = new String[2];

        result[0] = curr.getQuestion();
        result[1] = curr.getAnswer();

        return result;
    }

    public void setContentByFlashCard(String name){
        Flashcard curr = logic.getCard(name);
        index = Integer.parseInt(curr.getCardName().substring(1));
    }

    public String[] getNextCard(){
        setNextCardContent();
        return getContent();
    }

    public String[] getPrevCard(){
        setPrevCardContent();
        return getContent();
    }

    //clamping methods for flashcard viewer
    private void setNextCardContent() {
        //Flashcard nextCard;
        index++;
        if (index < dbSize) {
            return;
        } else {
            index--;
        }

    }

    private void setPrevCardContent() {
        //Flashcard nextCard;
        index--;
        if (index > 0) {
            return;
        } else {
            index++;
        }

    }

    //test stub for now
    public void addStub(){
        for(int i = 0;i<4;i++)
            saveCard("test"+i,"test"+i);
    }


}
