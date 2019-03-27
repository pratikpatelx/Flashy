package comp3350.flashy.logic;

import comp3350.flashy.domain.*;


/**
 * This class serves as the sole connection between the presentation layer and the quiz being taken
 */
public class QuizManager {

    private String user;
    private Quiz quiz;
    private int correct;
    private int wrong;

    public QuizManager(String username, Deck quizDeck){
        this.user = username;
        this.quiz = new Quiz(quizDeck);
        this.correct = 0;
        this.wrong = 0;
    }

    public int getCorrect(){
        return this.correct;
    }

    public int getWrong(){
        return this.wrong;
    }

    /**
     * StartQuiz()
     * @return the first card in the quiz
     */
    public Flashcard getNextCard(){
        return this.quiz.getCard();
    }


    /**
     *
     * @param response the user's answer to the current flashcard
     * @return the next flashcard
     */
    public boolean evaluateAnswer(String response){
        Flashcard curr = this.quiz.takeCard();
        boolean correct = false;

        if(curr.mark(response)){
            this.correct++;
            correct = true;
        }
        else{
            //put the card back in the deck if it was wrong
            this.wrong++;
            this.quiz.placeCard(curr);
        }

        return correct;
    }

    /**
     *
     * @param response weather the user got the question right or not
     * @return just an echo of responsr
     */
    public boolean evaluateAnswer(boolean response){
        Flashcard curr = this.quiz.takeCard();
        boolean correct = false;

        if(response){
            this.correct++;
            correct = true;
        }
        else{
            //put the card back in the deck if it was wrong
            this.wrong++;
            this.quiz.placeCard(curr);
        }

        return correct;
    }

    public int getDeckSize(){
        return this.quiz.getSize();
    }

    public boolean done(){
        return this.quiz.isEmpty();
    }






}
