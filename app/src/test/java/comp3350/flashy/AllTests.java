package comp3350.flashy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ExampleTest.class,
        GetCardTest.class,
        PrintDeckTest.class,
        PutCardIntoDeckTest.class,
        RemoveCardTest.class,
        EditCardTest.class,
        GetandInsertDeckTest.class,
        DeleteDeckTest.class
})

public class AllTests {}