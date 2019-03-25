package comp3350.flashy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ExampleTest.class,
        GetFlashcardTest.class,
        PrintDeckTest.class,
        PutFlashcardIntoDeckTest.class,
        RemoveFlashcardTest.class,
        //EditCardTest.class,
        GetandInsertDeckTest.class,
        DeleteDeckTest.class
})

public class AllTests {}