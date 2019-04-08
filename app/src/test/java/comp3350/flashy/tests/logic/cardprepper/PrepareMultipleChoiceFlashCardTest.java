package comp3350.flashy.tests.logic.cardprepper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.lang.reflect.Array;
import java.util.ArrayList;

import comp3350.flashy.domain.MultipleChoiceFlashcard;
import comp3350.flashy.logic.CardPrepper;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PrepareMultipleChoiceFlashCardTest {

    private MultipleChoiceFlashcard testCard;
    private CardPrepper testPrep;
    private ArgumentCaptor<String> strCaptor;
    private ArgumentCaptor<ArrayList<String>> listCaptor;
    private ArrayList<String> testA;

    @Before
    public void setUp(){
        testA = new ArrayList<>();
        testA.add("a1");
        testA.add("a2");
        testCard = mock(MultipleChoiceFlashcard.class);
        testPrep = new CardPrepper();
        strCaptor = ArgumentCaptor.forClass(String.class);
        listCaptor = ArgumentCaptor.forClass(ArrayList.class);
        when(testCard.getAnswers()).thenReturn(testA);
        doNothing().when(testCard).setAnswer(anyString());

    }

    @Test
    public void prepareMultipleChoiceFlashcardTest(){
        CardPrepper.prepareMultipleChoiceFlashcard(testCard);

        verify(testCard).setAnswer(strCaptor.capture());
        assertEquals("a1",strCaptor.getValue());
        verify(testCard).setAnswers(listCaptor.capture());
        assertTrue(listCaptor.getValue().contains("a2"));

        System.out.println("Prepare Multiple Choice Card unit test complete");
    }
}
