package comp3350.flashy.domain;


/**
 * An Specialized Flashcard that implements fill in the blank questions
 */
public class FitBcard extends Flashcard implements FlashcardInterface{

    private static final String BLANK = "_____";
    private static final int THRESHOLD = 5;
    private static final String cardType = "1";

    public FitBcard(String name, String question, String answer) {
        super(name, question, answer);
    }


    /**
     * prepare()
     *
     * prepares the flashcard for use in a quiz
     */
    public void prepare(){
        String[] words = this.getQuestion().split(" ,.!?;:\"");
        int nWords = words.length;
        int count = 0;
        for(int i = 0; i < nWords; i++){
            if(words[i].length() >= THRESHOLD ){
                count++;
            }
        }

        if(count == 0){
            //if no words are long enough pick one of the longest words
        }
        else{
            //select a random word
        }

    }


    public String getCardType () {
        return cardType;
    }

    public boolean isFillInTheBlanksFlashcard () {
        return true;
    }



}
