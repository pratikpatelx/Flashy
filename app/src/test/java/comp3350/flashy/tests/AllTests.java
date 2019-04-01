package comp3350.flashy.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.flashy.tests.domain.CardTest;
import comp3350.flashy.tests.domain.DeckTest;
import comp3350.flashy.tests.logic.ExampleTest;
import comp3350.flashy.tests.logic.GetAllDecksTest;
import comp3350.flashy.tests.logic.GetAllProfilesTest;
import comp3350.flashy.tests.logic.GetFlashcardTest;
import comp3350.flashy.tests.logic.GetNamesTest;
import comp3350.flashy.tests.logic.PutFlashcardIntoDeckTest;
import comp3350.flashy.tests.logic.GetandInsertDeckTest;
import comp3350.flashy.tests.logic.RemoveFlashcardTest;
import comp3350.flashy.tests.logic.DeckDeletionTest;
import comp3350.flashy.tests.logic.EditFlashcardTest;




@RunWith(Suite.class)
@Suite.SuiteClasses({
        ExampleTest.class,
        GetFlashcardTest.class,
        PutFlashcardIntoDeckTest.class,
        RemoveFlashcardTest.class,
        GetandInsertDeckTest.class,
        DeckDeletionTest.class,
        EditFlashcardTest.class,
        GetNamesTest.class,
        GetAllDecksTest.class,
        GetAllProfilesTest.class,
        DeckTest.class,
        CardTest.class,
})

public class AllTests {}