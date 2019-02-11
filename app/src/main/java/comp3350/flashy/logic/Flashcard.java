package comp3350.flashy.logic;

//This might need to be changed before Due date

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

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (o instanceof Flashcard) {
            result = this.cardName.equals(((Flashcard) o).getCardName());
        }
        return result;
    }

}