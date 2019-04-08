package comp3350.flashy.logic;

import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.domain.Quiz;


// This class serves as the sole connection between the presentation layer and the quiz being taken

public class QuizManager {

    private Quiz quiz;
    private int correct;
    private int wrong;

    public QuizManager(Quiz quiz){
        this.quiz = quiz;
        this.correct = 0;
        this.wrong = 0;
    }


    public int getCorrect() {
        return this.correct;
    }

    public int getWrong() {
        return this.wrong;
    }

    /**
     * StartQuiz()
     *
     * @return the next card in the quiz
     */
    public Flashcard getNextCard() {
        return this.quiz.getCard();
    }

    public int getNextCardType() {
        return Integer.parseInt(this.quiz.getCard().getCardType());
    }

    /**
     * @param response whether the user got the question right or not
     * @return just an echo of response
     */
    public boolean evaluateAnswer(boolean response) {
        Flashcard curr = this.quiz.takeCard();
        boolean correct = false;

        if (response) {
            this.correct++;
            correct = true;
        } else {
            //put the card back in the deck if it was wrong
            this.wrong++;
            this.quiz.placeCard(curr);
        }

        return correct;
    }

    public int getDeckSize() {
        return this.quiz.getSize();
    }

    public boolean done() {
        return this.quiz.isEmpty();
    }


}
