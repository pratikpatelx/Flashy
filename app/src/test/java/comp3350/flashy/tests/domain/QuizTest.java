package comp3350.flashy.tests.domain;

public class QuizTest {
}
//TODO

/* Moved this method to Quiz so itll have to be a quiztest now

@Before
    public void setUp() {
        testFITB = mock(FillInTheBlanksFlashcard.class);
        testMC = mock(MultipleChoiceFlashcard.class);
        testStandard = mock(Flashcard.class);
        testPrep = spy(new CardPrepper());
        captor = ArgumentCaptor.forClass(String.class);

        when(testFITB.getCardType()).thenReturn("1");
        when(testMC.getCardType()).thenReturn("2");


    }

    @Test
    public void prepareCardTest() {
        System.out.println("Running Prepare Card unit test");

        testPrep.prepareCard(testFITB);
        testPrep.prepareCard(testMC);

        verify(testPrep).prepareFitBCard(testFITB);
        verify(testPrep).prepareMultipleChoiceFlashcard(testMC);

        System.out.println("Prepare Card unit test complete.");
    }

}
 */