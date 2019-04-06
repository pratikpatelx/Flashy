package comp3350.flashy.tests;

//TODO: Fix integration tests with new logic layer, write quiz&cardprepper unit tests


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.flashy.tests.domain.FillInTheBlanksFlashcardTest;
import comp3350.flashy.tests.domain.FlashcardTest;
import comp3350.flashy.tests.domain.DeckTest;
import comp3350.flashy.tests.domain.MultipleChoiceFlashcardTest;

import comp3350.flashy.tests.logic.cardprepper.PrepareFitBCardTest;
import comp3350.flashy.tests.logic.cardprepper.PrepareMultipleChoiceFlashCardTest;
import comp3350.flashy.tests.logic.deckmanager.GetAllDecksTest;
import comp3350.flashy.tests.logic.deckmanager.GetDeckTest;
import comp3350.flashy.tests.logic.deckmanager.GetNamesTest;
import comp3350.flashy.tests.logic.deckmanager.InsertDeckTest;
import comp3350.flashy.tests.logic.deckmanager.PutFlashcardInDeckTest;
import comp3350.flashy.tests.logic.deckmanager.EditFlashcardTest;

import comp3350.flashy.tests.logic.quizmanager.EvaluateAnswerTest;
import comp3350.flashy.tests.logic.quizmanager.QuizManagementTest;
import comp3350.flashy.tests.logic.usermanager.GetAllProfilesTest;
import comp3350.flashy.tests.logic.usermanager.UserManagementTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({

        //integration tests
        /*
        GetFlashcardTest.class,
        RemoveFlashcardTest.class,
        GetandInsertDeckTest.class,
        DeckDeletionTest.class,*/

        //Unit Tests

            //Logic Tests
        PutFlashcardInDeckTest.class,
        GetDeckTest.class,
        InsertDeckTest.class,
        EditFlashcardTest.class,
        GetNamesTest.class,
        GetAllDecksTest.class,

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
})

public class AllTests {}