package comp3350.flashy.tests.logic.deckmanager;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.logic.DeckManager;
import comp3350.flashy.persistence.Interfaces.DeckDatabaseImplementation;
import comp3350.flashy.persistence.Translators.DataTranslationLayer;

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
    private Deck deck1, deck2;

    @Before
    public void setUp() {
        testDB = mock(DeckDatabaseImplementation.class);
        testDH = spy(new DeckManager(testDB, mock(DataTranslationLayer.class)));
        testColl = new ArrayList<>();
        deck1 = mock(Deck.class);
        deck2 = mock(Deck.class);
        testColl.add(deck1);
        testColl.add(deck2);

        when(deck1.getName()).thenReturn("1");
        when(deck2.getName()).thenReturn("2");
        when(testDB.getDeckCollection("")).thenReturn(testColl);

    }


    @Test
    public void getNamesTest() {
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
        verify(deck1).getName();
        verify(deck2).getName();

        System.out.println("GetNames test complete");

    }
}
