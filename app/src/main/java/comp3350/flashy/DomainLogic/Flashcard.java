package comp3350.flashy.DomainLogic;

import comp3350.flashy.DomainLogic.FlashcardInterface;

public class Flashcard implements FlashcardInterface {
    private String cardName;
    private String answer;
    private String question;

    public Flashcard(String name, String question, String answer) {
        this.cardName = name;
        this.question = question;
        this.answer = answer;
    }


    /**
     * toString
     * <p>
     * A function useful for testing
     */
    public String toString() {
        String info = "Flashcard: ";
        info += this.cardName + "\n";
        info += "Q: " + this.question + "\n";
        info += "A: " + this.answer + "\n";
        return info;
    }



    /**
     * Mutators
     */

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String newAnswer) {
        this.answer = newAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String newQuestion) {
        this.question = newQuestion;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String newName) {
        cardName = newName;
    }

    /**
     * editCard()
     * This method will change question and answer to be the same as that of
     * mimic
     *
     * @param mimic
     * @return true if and only if mimic has the same name as this
     */
    public boolean editCard(Flashcard mimic){
        boolean success = false;
        if(mimic.equals(this)){
            this.question = mimic.getQuestion();
            this.answer = mimic.getAnswer();
            success = true;
        }
        return success;
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (o instanceof Flashcard) {
            result = this.cardName.equals(((Flashcard) o).getCardName());
        }
        return result;
    }

}
