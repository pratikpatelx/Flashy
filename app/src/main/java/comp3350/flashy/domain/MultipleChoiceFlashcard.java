package comp3350.flashy.domain;

import java.util.ArrayList;

public class MultipleChoiceFlashcard extends Flashcard {
    ArrayList answers = null;
    private String cardType = "3";

    public MultipleChoiceFlashcard (String name, String question, ArrayList answerList) {
        super(name, question,"");
        answers = answerList;
    }

    public void setAnswers (ArrayList newAnswers) {
        answers = newAnswers;
    }

    public ArrayList getAnswers () {
        return answers;
    }

    public String getCardType () {
        return cardType;
    }

    public boolean isMultipleChoiceFlashcard () {
        return true;
    }
}
