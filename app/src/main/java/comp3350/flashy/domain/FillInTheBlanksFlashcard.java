package comp3350.flashy.domain;

public class FillInTheBlanksFlashcard extends Flashcard {
    String firstPart = null;
    String cardType = "1";

    public FillInTheBlanksFlashcard(String name, String question, String answer, String givenFirstPart, int pos) {
        super(name, question, answer, pos);
        firstPart = givenFirstPart;

    }

    public FillInTheBlanksFlashcard(String name, String question, String answer, String givenFirstPart) {
        super(name, question, answer, 0);
        firstPart = givenFirstPart;

    }

    public String getFirstPart() {
        return firstPart;
    }

    public void setFirstPart(String answer) {
        this.firstPart = answer;
    }

    public String getCardType() {
        return cardType;
    }

    public boolean isFillInTheBlanksFlashcard() {
        return true;
    }

    @Override
    public boolean isRegularFlashcard() {
        return false;
    }
}
