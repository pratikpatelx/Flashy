package comp3350.flashy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({

        ManageFillFlashcardTest.class,
        ManageFlashcardTest.class,
        ManageMCFlashcardTest.class,
        ManageUserProfileTest.class,
        QuizModeTest.class,
        ManageDeckTest.class,
})

public class AllAcceptanceTests {

}
