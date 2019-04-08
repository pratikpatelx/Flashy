package comp3350.flashy.tests.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.domain.Quiz;

import static net.bytebuddy.matcher.ElementMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class QuizTest {

    private Deck testDeck;
    private Quiz testQuiz;
    private Flashcard testCard,testCard2;
    private ArrayList<Flashcard> testList;


    @Before
    public void setUp(){
        testDeck = mock(Deck.class);
        testCard = mock(Flashcard.class);
        testCard2 = mock(Flashcard.class);
        testList = new ArrayList<Flashcard>();
        testList.add(testCard);
        testList.add(testCard2);
        when(testCard.getCardType()).thenReturn("0");
        when(testCard2.getCardType()).thenReturn("0");
        when(testDeck.getFlashcards()).thenReturn(testList);

    }


    @Test
    public void quizTest(){
        testQuiz = new Quiz(testDeck);

        assertEquals(testQuiz.getSize(),2);
        assertFalse(testQuiz.isEmpty());
        Flashcard result = testQuiz.getCard();
        assertTrue(result==testCard||result==testCard2);

        testQuiz.takeCard();

        assertEquals(testQuiz.getSize(),1);

        testQuiz.placeCard(testCard);

        assertTrue(result==testCard||result==testCard2);
        assertEquals(testQuiz.getSize(),2);

        verify(testCard).getCardType();
        verify(testCard2).getCardType();
        verify(testDeck).getFlashcards();

        System.out.println("Quiz object unit test complete. \n");
    }
}

