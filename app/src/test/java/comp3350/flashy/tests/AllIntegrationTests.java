package comp3350.flashy.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;



import comp3350.flashy.tests.integration.DeckIntegration;
import comp3350.flashy.tests.integration.UserIntegration;

@RunWith(Suite.class)
@Suite.SuiteClasses({

        //Integration tests

        DeckIntegration.class,
        UserIntegration.class,

})


public class AllIntegrationTests {
}

