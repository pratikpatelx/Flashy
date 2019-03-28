package comp3350.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import comp3350.tests.integration.DeckDeletionTest;
import comp3350.tests.integration.ExampleTest;
import comp3350.tests.integration.GetFlashcardTest;
import comp3350.tests.integration.PrintDeckTest;
import comp3350.tests.integration.PutFlashcardIntoDeckTest;
import comp3350.tests.integration.GetandInsertDeckTest;
import comp3350.tests.integration.RemoveFlashcardTest;
import comp3350.tests.unit.logic.DeleteDeckTest;
import comp3350.tests.unit.logic.GetDeckTest;
import comp3350.tests.unit.logic.InsertDeckTest;
import comp3350.tests.unit.logic.PutFlashCardInDeckTest;
import comp3350.tests.unit.logic.RemoveCardTest;




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