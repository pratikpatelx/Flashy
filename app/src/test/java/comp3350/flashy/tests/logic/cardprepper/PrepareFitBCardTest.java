package comp3350.flashy.tests.logic.cardprepper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import comp3350.flashy.domain.FillInTheBlanksFlashcard;
import comp3350.flashy.logic.CardPrepper;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class PrepareFitBCardTest{

	private FillInTheBlanksFlashcard testCard;
	private FillInTheBlanksFlashcard fraud;
	private CardPrepper testPrep;
	private ArgumentCaptor<String> captor;

@Before
public void setUp() {
	   testCard = mock(FillInTheBlanksFlashcard.class);
	   testPrep = new CardPrepper();
	   captor = ArgumentCaptor.forClass(String.class);
	   when(testCard.getCardType()).thenReturn("1");
	   when(testCard.getAnswer()).thenReturn("One Two");
	   when(testCard.getFirstPart()).thenReturn("One");
	   doNothing().when(testCard).setFirstPart(anyString());
	   doNothing().when(testCard).setAnswer(anyString());

		}

@Test
public void prepareFitBCardTest() {
		CardPrepper.prepareFitBCard(testCard);

		verify(testCard).setAnswer(captor.capture());
		verify(testCard, times(2)).getAnswer();
		String result = captor.getValue();
		assertEquals(result,("One Two".replace("One","_____")));
		System.out.println("Prepare FITB card unit test complete.");
	}

}