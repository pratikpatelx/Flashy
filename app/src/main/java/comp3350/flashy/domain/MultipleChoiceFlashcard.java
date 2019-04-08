package comp3350.flashy.domain;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceFlashcard extends Flashcard {
    private ArrayList answers;
    private String cardType = "2";

    public MultipleChoiceFlashcard(String name, String question, List<String> answerList) {
        super(name, question, "",0);
        this.answers = new ArrayList<String>(answerList);
    }


    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> newAnswers) {
        this.answers = new ArrayList<String>(newAnswers);
    }

    public String getCardType() {
        return cardType;
    }

}
