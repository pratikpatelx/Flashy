package comp3350.flashy.tests.domain;


//Card methods: getters, editcard, getcardtype, mark

import org.junit.Before;
import org.junit.Test;

import comp3350.flashy.domain.FillInTheBlanksFlashcard;

import static junit.framework.TestCase.assertTrue;

public class FillInTheBlanksFlashcardTest {

    private FillInTheBlanksFlashcard card;

    @Before
    public void setUp(){
        card = new FillInTheBlanksFlashcard("fakeDeck-0","q1","a1","p1");

    }

    @Test
    public void fillInTheBlanksTest(){

        System.out.println("Running Fill In The Blanks Flashcard object test");
        assertTrue(card.getFirstPart().equals("p1"));

        assertTrue(card.getCardType().equals("1"));
        assertTrue(card.isFillInTheBlanksFlashcard());

        System.out.println("Fill In The Blanks Flashcard object test complete.");
    }

}
