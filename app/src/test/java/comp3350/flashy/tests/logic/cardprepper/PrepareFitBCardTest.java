package comp3350.flashy.tests.logic.cardprepper;

import org.junit.Before;
import org.junit.Test;

import comp3350.flashy.domain.FillInTheBlanksFlashcard;
import comp3350.flashy.logic.CardPrepper;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class PrepareFitBCardTest{

    private FillInTheBlanksFlashcard testCard;
    private FillInTheBlanksFlashcard fraud;
    private CardPrepper testPrep;

@Before
public void setUp() {
       testCard = mock(FillInTheBlanksFlashcard.class);
       fraud = mock(FillInTheBlanksFlashcard.class);
       testPrep = new CardPrepper();

       when(testCard.getCardType()).thenReturn("1");
       when(testCard.getAnswer()).thenReturn("One Two");

       when(fraud.getCardType()).thenReturn("2"); //It is a FITB mock, but it will not act like one
        //new FillInTheBlanksFlashcard("fakeDeck-0", "q1", "a1", "p1");
        }

@Test
public void prepareFitBCardTest() {

        System.out.println("Running Prepare FITB card unit test");

        testPrep.prepareFitBCard(fraud);
        verify(fraud).getCardType();
        verify(fraud, times(0)).getAnswer();

        testPrep.prepareFitBCard(testCard);
        verify(testCard).getCardType();
        verify(testCard).getAnswer();

        System.out.println("Fill In The Blanks Flashcard object test complete.");
        }

        }