package comp3350.flashy.domain;

public class TorFcard extends Flashcard implements FlashcardInterface {

    private static final String cardType = "2";

    public TorFcard(String name, String question, String answer) {

        super(name, question, answer);

    }

    //Place True or False card specific functions below
    /**
     * for this type of Flashcard the answer must be a string representation of true or false
     * @param newAnswer
     */
    public void setAnswer(String newAnswer) {
        if(newAnswer.contentEquals(String.valueOf(true))
                || newAnswer.contentEquals(String.valueOf(false))){
            super.setAnswer(newAnswer);
        }
    }





    @Override
    public boolean mark(String response){
        boolean correct = false;
        if(response.contentEquals(String.valueOf(true))
                || response.contentEquals(String.valueOf(false))){
            correct = this.getAnswer().equals(response);
        }
        else{
            System.out.println(response+" is an invalid response to a true or false question");
        }
        return correct;
    }


    public String getCardType () {
        return cardType;
    }

    public boolean isTrueFalseFlashcard () {
        return true;
    }


}
