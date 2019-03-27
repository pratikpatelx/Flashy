package comp3350.flashy.domain;

public class FillInTheBlanksFlashcard extends Flashcard {
    private static final String cardType = "1";

    public FillInTheBlanksFlashcard(String name, String question, String answer) {
        super(name, question, answer);
    }





    public String getCardType () {
        return cardType;
    }

    @Override
    public boolean isRegularFlashcard(){
        return false;
    }

    @Override
    public boolean isFillInTheBlanksFlashcard () {
        return true;
    }

}
