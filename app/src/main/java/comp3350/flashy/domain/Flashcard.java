package comp3350.flashy.domain;

public class Flashcard implements FlashcardInterface {
    private static final String cardType = "0";
    private String inDeck;//the name of the deck the card is in
    private int position;//the card's position in the deck
    private String answer;
    private String question;

    public Flashcard(String deckName, String question, String answer, int pos) {
        this.inDeck = deckName;
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
        info += this.inDeck + "\n";
        info += "Q: " + this.question + "\n";
        info += "A: " + this.answer + "\n";
        return info;
    }


    /**
     * Mutators and Accessors
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
        return inDeck;
    }

    public void setCardName(String newName) {
        inDeck = newName;
    }

    /**
     * editCard()
     * This method will change question and answer to be the same as that of
     * mimic
     *
     * @param mimic
     * @return true if and only if mimic has the same name as this
     */
    public boolean editCard(Flashcard mimic) {
        boolean success = false;
        if (mimic.equals(this)) {
            this.question = mimic.getQuestion();
            this.answer = mimic.getAnswer();
            success = true;
        }
        return success;
    }

    public String getCardType() {
        return cardType;
    }


    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (o instanceof Flashcard) {
            result = this.inDeck.equals(((Flashcard) o).getCardName());
        }
        return result;
    }


    @Override
    public boolean mark(String response) {
        return this.answer.equalsIgnoreCase(response);
    }

    public boolean isRegularFlashcard() {
        return true;
    }

    public boolean isFillInTheBlanksFlashcard() {
        return false;
    }

    public boolean isTrueFalseFlashcard() {
        return false;
    }

    public boolean isMultipleChoiceFlashcard() {
        return false;
    }


}
