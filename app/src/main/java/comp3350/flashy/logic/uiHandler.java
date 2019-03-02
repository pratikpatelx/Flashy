package comp3350.flashy.logic;

import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.persistence.DatabaseManager;

public class uiHandler {

    private int index; // for now, we will use the index to name the flashcards within the master deck
    private DatabaseManager flashCardDB;
    //implemented with string id, should be integer.

    private String cardName = "c";
    private int dbSize; // this is to hold max index

    //TODO add checks and error handling.

    public uiHandler() {
        index = 1;
        dbSize = 1;
        flashCardDB = new DatabaseManager();
    }

    public void saveCard(String head, String content) {
        flashCardDB.addCard(new Flashcard((cardName + dbSize), head, content));
        dbSize++;
    }

    public String[] getContent() {
        Flashcard curr = flashCardDB.getCard((cardName + index));
        System.out.println(index);

        String[] result = new String[2];

        result[0] = curr.getQuestion();
        result[1] = curr.getAnswer();

        return result;
    }


    public void setNextCardContent() {
        //Flashcard nextCard;
        index++;
        if (index < dbSize) {
            return;
        } else {
            index--;
        }

    }

    public void setPrevCardContent() {
        //Flashcard nextCard;
        index--;
        if (index > 0) {
            return;
        } else {
            index++;
        }

    }

    //for now we'll use a test stub for the viewer
    public void addStub() {
        String arr[][] = DatabaseManager.getStub();

        for (int i = 0; i < arr.length; i++) {
            saveCard(arr[i][0], arr[i][1]);
        }
    }

}
