package comp3350.flashy.tests.logic.logicmanager;

import org.junit.Before;
import org.junit.Test;

import comp3350.flashy.logic.PersistenceHandlers.DeckHandler;
import comp3350.flashy.logic.PersistenceHandlers.UserHandler;
import comp3350.flashy.tests.persistence.DeckStub;
import comp3350.flashy.domain.Deck;
import comp3350.flashy.logic.LogicManager;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class InsertDeckTest {

    private DeckHandler testDB;
    private LogicManager testLGC;

    @Before
    public void setUp(){
        testDB = mock(DeckHandler.class);
        testLGC = new LogicManager(testDB,mock(UserHandler.class));
    }

    @Test
    public void insertDeckTest(){
        System.out.println("\nrunning Insert Deck unit test\n");
        Deck original = new DeckStub("testDeck");

        testLGC.insertDeck("",original);

        verify(testDB).inputDeck("",original.getName(), original);
        System.out.println("Insert Deck test complete");

    }
}
