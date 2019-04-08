package comp3350.flashy.tests.logic.quizmanager;

import org.junit.Before;
import org.junit.Test;

import comp3350.flashy.domain.Deck;

import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.domain.Quiz;
import comp3350.flashy.logic.DeckManager;
import comp3350.flashy.logic.QuizManager;
import comp3350.flashy.persistence.Interfaces.DeckDatabaseImplementation;
import comp3350.flashy.persistence.Translators.DataTranslationLayer;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EvaluateAnswerTest {

    private QuizManager testQM;
    private Quiz testQuizObj;
    private Flashcard testCard;

    @Before
    public void setUp(){
        testQuizObj = mock(Quiz.class);
        testCard = mock(Flashcard.class);
        testQM = new QuizManager(testQuizObj);
        when(testQuizObj.takeCard()).thenReturn(testCard);
        doNothing().when(testQuizObj).placeCard(testCard);

    }

    @Test
    public void evaluateAnswerTest(){
        assertTrue(testQM.getCorrect() == 0);

        assertTrue(testQM.evaluateAnswer(true));
        assertFalse(testQM.evaluateAnswer(false));

        assertEquals(testQM.getCorrect(), 1);
        assertEquals(testQM.getWrong(), 1);

        verify(testQuizObj, times(2)).takeCard();
        verify(testQuizObj).placeCard(testCard);

        System.out.println("Evaluate Answer test complete");

    }
}