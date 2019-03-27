package comp3350.flashy.domain;

public class FillInTheBlanksFlashcard extends Flashcard {
    String firstPart = null;
    String lastPart = null;
    String cardType = "1";

    public FillInTheBlanksFlashcard (String name, String question, String answer, String givenFirstPart, String givenLastPart) {
        super(name, question, answer);
        firstPart = givenFirstPart;
        lastPart = givenLastPart;
    }

    public String getFirstPart () {
        return firstPart;
    }

    public String getLastPart () {
        return lastPart;
    }

    public String getCardType () {
        return cardType;
    }

    public boolean isFillInTheBlanksFlashcard () {
        return true;
    }

    @Override
    public boolean isRegularFlashcard() {
        return false;
    }
}
