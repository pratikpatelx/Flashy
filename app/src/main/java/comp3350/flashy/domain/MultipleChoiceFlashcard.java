package comp3350.flashy.domain;

import java.util.ArrayList;

public class MultipleChoiceFlashcard extends Flashcard {
    private String[] choices;
    private static final String cardType = "3";

    public MultipleChoiceFlashcard(String name, String question, String answer,
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
        return this.cardType;
    }


    @Override
    public boolean isRegularFlashcard(){
        return false;
    }

    @Override
    public boolean isMultipleChoiceFlashcard () {
        return true;
    }

}
