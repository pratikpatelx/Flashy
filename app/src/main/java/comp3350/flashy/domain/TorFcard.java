package comp3350.flashy.domain;

/**
 *
 * @author cjung
 */
public class TorFcard extends Flashcard{
    
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
}
