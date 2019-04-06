package comp3350.flashy.tests.logic.handlers;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.logic.DeckManager;
import comp3350.flashy.persistence.Interfaces.DeckDatabaseImplementation;
import comp3350.flashy.persistence.Translators.DataTranslationLayer;
import comp3350.flashy.tests.persistence.DeckStub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetNamesTest {

    private DeckManager testDH;
    private DeckDatabaseImplementation testDB;
    private ArrayList<String> expectedList;
    private Collection<Deck> testColl;

    @Before
    public void setUp() {
        testDB = mock(DeckDatabaseImplementation.class);
        testDH = spy(new DeckManager(testDB, mock(DataTranslationLayer.class)));
        testColl = new ArrayList<>();
        testColl.add(new DeckStub("1"));
        testColl.add(new DeckStub("2"));
    }


    @Test
    public void getNamesTest() {
        System.out.println("\nrunning GetNames unit test\n");

        when(testDB.getDeckCollection("")).thenReturn(testColl);

        Collection result = testDH.getNames("");
        verify(testDB).getDeckCollection("");

        expectedList = new ArrayList<String>() {
            {
                add("1");
                add("2");
            }
        };

        assertEquals(expectedList, result);
        assertTrue("Test failed: ArrayLists do not match.", expectedList.toString().contentEquals(result.toString()));


        System.out.println("GetNames test complete");

    }
}
