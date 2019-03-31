package comp3350.flashy.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import comp3350.flashy.tests.logic.DeckDeletionTest;
import comp3350.flashy.tests.logic.ExampleTest;
import comp3350.flashy.tests.logic.GetFlashcardTest;
import comp3350.flashy.tests.logic.PrintDeckTest;
import comp3350.flashy.tests.logic.PutFlashcardIntoDeckTest;
import comp3350.flashy.tests.logic.GetandInsertDeckTest;
import comp3350.flashy.tests.logic.RemoveFlashcardTest;
import comp3350.flashy.tests.logic.DeleteDeckTest;
import comp3350.flashy.tests.logic.GetDeckTest;
import comp3350.flashy.tests.logic.InsertDeckTest;
import comp3350.flashy.tests.logic.PutFlashCardInDeckTest;
import comp3350.flashy.tests.logic.RemoveCardTest;




@RunWith(Suite.class)
@Suite.SuiteClasses({
        ExampleTest.class,
        GetFlashcardTest.class,
        PrintDeckTest.class,
        PutFlashcardIntoDeckTest.class,
        RemoveFlashcardTest.class,
        //EditCardTest.class,
        GetandInsertDeckTest.class,
        DeckDeletionTest.class,
        DeleteDeckTest.class,
        GetDeckTest.class,
        InsertDeckTest.class,
        RemoveCardTest.class,
        PutFlashCardInDeckTest.class,
})

public class AllTests {}