package comp3350.flashy.tests.domain;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import comp3350.flashy.domain.MultipleChoiceFlashcard;

import static junit.framework.TestCase.assertTrue;

public class MultipleChoiceFlashcardTest {

    private MultipleChoiceFlashcard card;
    private ArrayList<String> answers;

    @Before
    public void setUp() {
        answers = new ArrayList<String>() {
            {
                add("1");
                add("2");
            }
        };
        card = new MultipleChoiceFlashcard("name", "q", answers, );
    }

    @Test
    public void multipleChoiceFlashcardTest() {
        System.out.println("Running Multiple Choice Flashcard object test");
        assertTrue(card.getAnswers().equals(answers));
        assertTrue(card.getCardType().equals("2"));
        assertTrue(card.isMultipleChoiceFlashcard());
        System.out.println("Multiple Choice Flashcard object test complete.");
    }
}


