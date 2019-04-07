package comp3350.flashy.domain;


public class TrueFalseCard extends Flashcard {
    private String truthValue = null;
    private String cardType = "2";

    public TrueFalseCard(String name, String question, String answer, String givenTruthValue) {
        super(name, question, answer, );
        truthValue = givenTruthValue;
    }

    public String getTruthValue() {
        return truthValue;
    }

    public String getCardType() {
        return cardType;
    }

    public boolean isTrueFalseFlashcard() {
        return true;
    }
}
