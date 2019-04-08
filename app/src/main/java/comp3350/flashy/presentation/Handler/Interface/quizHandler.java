package comp3350.flashy.presentation.Handler.Interface;

import java.util.ArrayList;

import comp3350.flashy.domain.FillInTheBlanksFlashcard;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.domain.MultipleChoiceFlashcard;
import comp3350.flashy.domain.Quiz;
import comp3350.flashy.logic.DeckManager;
import comp3350.flashy.logic.QuizManager;


//purpose: Handle quiz interface in ui
public class quizHandler {

    private QuizManager quiz;
    private DeckManager deckM;
    private int quizCardType;


    public quizHandler(){
        deckM = new DeckManager();
    }


    public void startQuiz(String Username, String deckName) {
        quiz = new QuizManager(new Quiz(deckM.getDeck(Username, deckName)));
    }


    public int[] getQuizInfo() {
        int[] result = new int[3];

        result[0] = quiz.getDeckSize();
        result[1] = quiz.getCorrect();
        result[2] = quiz.getWrong();

        return result;
    }

    public String[] getContent(){
        String[] result = new String[3];

        Flashcard curr = quiz.getNextCard();
        quizCardType = Integer.parseInt(curr.getCardType());

        if (curr.getCardType().equals("1"))
            result[2] = ((FillInTheBlanksFlashcard) curr).getFirstPart();


        result[0] = curr.getQuestion();
        result[1] = curr.getAnswer();

        return result;
    }

    public ArrayList<String> getMCContent(){
        MultipleChoiceFlashcard card = (MultipleChoiceFlashcard) quiz.getNextCard();

        ArrayList<String> result = new ArrayList<>();
        result.add(card.getQuestion());
        result.add(card.getAnswer());
        result.addAll(card.getAnswers());

        return result;
    }

    public int getCurrentType() {
        return quizCardType;
    }

    public int getNextCardType() {
        return quiz.getNextCardType();
    }

    public boolean isQuizDone() {
        return quiz.done();
    }

    public void setAnswer(boolean correct) {
        quiz.evaluateAnswer(correct);
    }

}
