package comp3350.flashy.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.flashy.tests.domain.FillInTheBlanksFlashcardTest;
import comp3350.flashy.tests.domain.FlashcardTest;
import comp3350.flashy.tests.domain.DeckTest;
import comp3350.flashy.tests.domain.MultipleChoiceFlashcardTest;
import comp3350.flashy.tests.domain.QuizTest;

import comp3350.flashy.tests.logic.cardprepper.PrepareFitBCardTest;
import comp3350.flashy.tests.logic.cardprepper.PrepareMultipleChoiceFlashCardTest;
import comp3350.flashy.tests.logic.deckmanager.GetDeckTest;
import comp3350.flashy.tests.logic.deckmanager.GetNamesTest;
import comp3350.flashy.tests.logic.deckmanager.InsertAndDeleteDeckTest;
import comp3350.flashy.tests.logic.deckmanager.PutFlashcardInDeckTest;
import comp3350.flashy.tests.logic.deckmanager.QueryDeckSizeTest;
import comp3350.flashy.tests.logic.deckmanager.RemoveCardTest;
import comp3350.flashy.tests.logic.quizmanager.EvaluateAnswerTest;
import comp3350.flashy.tests.logic.quizmanager.QuizManagementTest;
import comp3350.flashy.tests.logic.usermanager.GetAllProfilesTest;
import comp3350.flashy.tests.logic.usermanager.UserManagementTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({

        //Unit Tests

        //Logic Tests
        PutFlashcardInDeckTest.class,
        GetDeckTest.class,
        InsertAndDeleteDeckTest.class,
        GetNamesTest.class,
        RemoveCardTest.class,
        QueryDeckSizeTest.class,

        GetAllProfilesTest.class,
        UserManagementTest.class,

        EvaluateAnswerTest.class,
        QuizManagementTest.class,

        PrepareFitBCardTest.class,
        PrepareMultipleChoiceFlashCardTest.class,

        //domain object tests
        DeckTest.class,
        FlashcardTest.class,
        FillInTheBlanksFlashcardTest.class,
        MultipleChoiceFlashcardTest.class,
        QuizTest.class,
})

public class AllUnitTests {}