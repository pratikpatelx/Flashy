package comp3350.flashy.tests.logic.quizmanager;

import org.junit.Before;
import org.junit.Test;

import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.domain.Quiz;
import comp3350.flashy.logic.QuizManager;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class QuizManagementTest {

    private QuizManager testQM;
    private Quiz testQuizObj;
    private Flashcard testCard;

    @Before
    public void setUp(){
        testQuizObj = mock(Quiz.class);
        testCard = mock(Flashcard.class);
        testQM = new QuizManager(testQuizObj);

        when(testQuizObj.takeCard()).thenReturn(testCard);
        when(testQuizObj.getCard()).thenReturn(testCard);
        when(testCard.getCardType()).thenReturn("0");
        when(testQuizObj.getSize()).thenReturn(1);
        when(testQuizObj.isEmpty()).thenReturn(true);
        doNothing().when(testQuizObj).placeCard(testCard);

    }

    @Test
    public void QuizManagementTest(){
        assertEquals(testQM.getCorrect(), 0);
        assertEquals(testQM.getWrong(), 0);

        assertEquals(testQM.getNextCard(), testCard);
        assertEquals(testQM.getNextCardType(), 0);

        assertEquals(testQM.getDeckSize(), 1);
        assertTrue(testQM.done());

        verify(testQuizObj, times(2)).getCard();
        verify(testQuizObj).getSize();
        verify(testQuizObj).isEmpty();
        verify(testCard).getCardType();

        System.out.println("Quiz Management unit test complete");
    }
}