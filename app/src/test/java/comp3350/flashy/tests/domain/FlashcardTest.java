package comp3350.flashy.tests.domain;


//Card methods: getters, editcard, getcardtype, mark

import org.junit.Before;
import org.junit.Test;

import comp3350.flashy.domain.Flashcard;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class FlashcardTest {

    private Flashcard card1, card2;
    private String name, testQ, testA;

    @Before
    public void setUp() {
        name = "fakeDeck-0";
        testQ = "q1";
        testA = "a1";
        card1 = new Flashcard(name, testQ, testA);
        card2 = new Flashcard(name, testQ + "2", testA + "2");
    }

    @Test
    public void flashcardTest() {

        assertEquals(card1.getAnswer(),(testA));
        assertEquals(card1.getQuestion(),(testQ));
        assertEquals(card1.getCardName(),(name));
        assertEquals(card1.getCardType(),("0"));

        card1.setAnswer(card2.getAnswer());

        assertEquals(card1.getAnswer(),(card2.getAnswer()));
        assertTrue(card1.equals(card1));

        System.out.println("Flashcard object test complete.");
    }

}
