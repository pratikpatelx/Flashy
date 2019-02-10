package comp3350.flashy.logic;

import java.util.HashMap;

public class uiHandler {

    private HashMap<Integer, Flashcard> test = new HashMap<Integer, Flashcard>();
    private int index;

    private String body;
    private String title;


    public uiHandler() {
        test.put(1, new Flashcard("body1", "title1"));
        test.put(2, new Flashcard("body2", "title2"));
        test.put(3, new Flashcard("body3", "title3"));
        test.put(4, new Flashcard("body4", "title4"));
        test.put(5, new Flashcard("body5", "title5"));
        index = 1;

        body = test.get(index).getAnswer();
        title = test.get(index).getQuestion();

    }

    public void saveCard(String title, String body) {

    }

    public String[] getContent() {
        String[] result = new String[2];

        result[0] = body;
        result[1] = title;

        return result;
    }

    public void setNextCardContent() {
        Flashcard nextCard;
        index++;
        if ((nextCard = test.get(index)) != null) {
            body = nextCard.getAnswer();
            title = nextCard.getQuestion();
        } else {
            index--;
        }

    }

    public void setPrevCardContent() {
        Flashcard nextCard;
        index--;
        if ((nextCard = test.get(index)) != null) {
            body = nextCard.getAnswer();
            title = nextCard.getQuestion();
        } else {
            index++;
        }

    }

}
