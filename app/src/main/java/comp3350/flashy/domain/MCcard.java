package comp3350.flashy.domain;

import java.util.Arrays;


/**
 * An extension to flashcard that adds functionality for multiple choice questions
 * REQUIRES 5 possible answers including the single correct answer
 */
public class MCcard extends Flashcard implements FlashcardInterface {

    private String[] choices;
    private static final String cardType = "3";

    public MCcard(String name, String question, String answer,
                  String second,
                  String third,
                  String fourth,
                  String fifth) {

        super(name, question, answer);
        this.choices = new String[4];
        this.choices[0] = answer;
        this.choices[1] = second;
        this.choices[2] = third;
        this.choices[3] = fourth;
        this.choices[4] = fifth;
    }



    /***************** Accessors *****************/

    public String getFirst(){
        return this.choices[0];
    }

    public String getSecond(){
        return this.choices[1];
    }

    public String getThird(){
        return this.choices[2];
    }

    public String getFourth(){
        return this.choices[3];
    }

    public String getFifth(){
        return this.choices[4];
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

    public boolean isMultipleChoiceFlashcard () {
        return true;
    }

}
