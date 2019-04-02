package comp3350.flashy.tests.domain;


//Card methods: getters, editcard, getcardtype, mark

import org.junit.Before;
import org.junit.Test;

import comp3350.flashy.domain.Flashcard;

import static junit.framework.TestCase.assertTrue;

public class FlashcardTest {

    private Flashcard card1,card2;
    private String name,testQ,testA;

    @Before
    public void setUp(){
        name = "fakeDeck-0";
        testQ = "q1";
        testA = "a1";
        card1 = new Flashcard(name,testQ,testA);
        card2 = new Flashcard(name,testQ+"2",testA+"2");
    }

    @Test
    public void flashcardTest(){

        System.out.println("Running Flashcard object test");
        assertTrue(card1.getAnswer().equals(testA));
        assertTrue(card1.getQuestion().equals(testQ));
        assertTrue(card1.getCardName().equals(name));
        assertTrue(card1.getCardType().equals("0"));

        assertTrue(card1.mark(testA));

        card1.editCard(card2);
        assertTrue(card1.getAnswer().equals(card2.getAnswer()));
        assertTrue(card1.getAnswer().equals(card2.getAnswer()));

        System.out.println("Flashcard object test complete.");
    }

}
