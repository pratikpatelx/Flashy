package comp3350.flashy.tests.domain;


//Card methods: getters, editcard, getcardtype, mark

import org.junit.Before;
import org.junit.Test;

import comp3350.flashy.domain.FillInTheBlanksFlashcard;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class FillInTheBlanksFlashcardTest {

    private FillInTheBlanksFlashcard card;

    @Before
    public void setUp() {
        card = new FillInTheBlanksFlashcard("fakeDeck-0", "q1", "a1", "p1");

    }

    @Test
    public void fillInTheBlanksTest() {

        assertEquals(card.getFirstPart(),"p1");
        assertEquals(card.getCardType(),"1");
//        assertTrue(card.isFillInTheBlanksFlashcard());

        System.out.println("Fill In The Blanks Flashcard object test complete.");
    }

}
