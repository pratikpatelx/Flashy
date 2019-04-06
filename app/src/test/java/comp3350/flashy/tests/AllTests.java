package comp3350.flashy.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.flashy.tests.domain.FillInTheBlanksFlashcardTest;
import comp3350.flashy.tests.domain.FlashcardTest;
import comp3350.flashy.tests.domain.DeckTest;
import comp3350.flashy.tests.domain.MultipleChoiceFlashcardTest;

import comp3350.flashy.tests.logic.logicmanager.GetAllDecksTest;
import comp3350.flashy.tests.logic.logicmanager.GetAllProfilesTest;
import comp3350.flashy.tests.integration.GetFlashcardTest;
import comp3350.flashy.tests.logic.logicmanager.GetDeckTest;
import comp3350.flashy.tests.logic.logicmanager.GetNamesTest;
import comp3350.flashy.tests.integration.GetandInsertDeckTest;
import comp3350.flashy.tests.logic.logicmanager.InsertDeckTest;
import comp3350.flashy.tests.logic.logicmanager.PutFlashcardInDeckTest;
import comp3350.flashy.tests.integration.RemoveFlashcardTest;
import comp3350.flashy.tests.integration.DeckDeletionTest;
import comp3350.flashy.tests.logic.logicmanager.EditFlashcardTest;





@RunWith(Suite.class)
@Suite.SuiteClasses({
        GetFlashcardTest.class,
        RemoveFlashcardTest.class,
        GetandInsertDeckTest.class,
        PutFlashcardInDeckTest.class,
        GetDeckTest.class,
        InsertDeckTest.class,
        DeckDeletionTest.class,
        EditFlashcardTest.class,
        GetNamesTest.class,
        GetAllDecksTest.class,
        GetAllProfilesTest.class,
        DeckTest.class,
        FlashcardTest.class,
        FillInTheBlanksFlashcardTest.class,
        MultipleChoiceFlashcardTest.class,
})

public class AllTests {}