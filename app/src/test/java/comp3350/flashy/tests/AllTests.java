package comp3350.flashy.tests;

//TODO: Fix integration tests with new logic layer, write quiz&cardprepper unit tests


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.flashy.tests.domain.FillInTheBlanksFlashcardTest;
import comp3350.flashy.tests.domain.FlashcardTest;
import comp3350.flashy.tests.domain.DeckTest;
import comp3350.flashy.tests.domain.MultipleChoiceFlashcardTest;

import comp3350.flashy.tests.logic.managers.GetAllDecksTest;
import comp3350.flashy.tests.logic.managers.GetAllProfilesTest;
import comp3350.flashy.tests.logic.managers.GetDeckTest;
import comp3350.flashy.tests.logic.managers.GetNamesTest;
import comp3350.flashy.tests.logic.managers.InsertDeckTest;
import comp3350.flashy.tests.logic.managers.PutFlashcardInDeckTest;
import comp3350.flashy.tests.logic.managers.EditFlashcardTest;
import comp3350.flashy.tests.logic.managers.UserManagementTest;

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

        //domain object tests
        DeckTest.class,
        FlashcardTest.class,
        FillInTheBlanksFlashcardTest.class,
        MultipleChoiceFlashcardTest.class,
})

public class AllTests {}