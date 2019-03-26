package comp3350.flashy.domain;


import java.util.Random;

/**
 * An Specialized Flashcard that implements fill in the blank questions
 */
public class FitBcard extends Flashcard implements FlashcardInterface{


    private static final String cardType = "1";

    public FitBcard(String name, String question, String answer) {
        super(name, question, answer);
    }





    public String getCardType () {
        return cardType;
    }

    @Override
    public boolean isRegularFlashcard(){
        return false;
    }

    public boolean isFillInTheBlanksFlashcard () {
        return true;
    }



}
