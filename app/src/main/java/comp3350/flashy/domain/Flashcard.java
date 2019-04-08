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
        this.position = pos;
    }


    public Flashcard(String deckName, String question, String answer) {
        this.inDeck = deckName;
        this.question = question;
        this.answer = answer;
        this.position = 0;
    }



    /**
     * toString
     * <p>
     * A function useful for debugging
     */
    public String toString() {
        String info = "Flashcard: ";
        info += this.getCardName() + "\n";
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
        String id;
        if(this.inDeck.contains("-")){
            id = this.inDeck;
        }
        else{
            id =inDeck.concat("-" + this.position);
        }
        return id;
    }


    public int getPosition(){
        return this.position;
    }

    public void setPosition(int pos){
        this.position = pos;
    }

    public String getInDeck(){
        return this.inDeck;
    }

    public void setInDeck(String deckName){
        this.inDeck = deckName;
    }


    public String getCardType() {
        return cardType;
    }


    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (o instanceof Flashcard) {
            result = this.inDeck.equals(((Flashcard) o).getInDeck()) &&
                    (this.position == ((Flashcard)o).getPosition());
        }
        return result;
    }

}
