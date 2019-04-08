package comp3350.flashy.tests.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.logic.DeckManager;
import comp3350.flashy.persistence.DatabaseImplementations.HSQLDB.DeckDatabaseHSQLDB;
import comp3350.flashy.persistence.Translators.DataTranslationLayer;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class DeckIntegration {

    private DeckManager deckM;
    private File testDB;
    private String user;
    private Deck testDeck;
    private Flashcard testCard;

    @Before
    public void setUp() throws IOException {
        this.testDB = DatabaseDuplicator.copyDB();
        final DeckDatabaseHSQLDB deckDB= new DeckDatabaseHSQLDB(this.testDB.getAbsolutePath().replace(".script", ""));
        this.deckM = new DeckManager(deckDB, new DataTranslationLayer());
        user = "user";
        testDeck = new Deck("testDeck");
        testCard = new Flashcard("testDeck-0","q","a");

    }

    @Test
    public void InsertTest(){
        deckM.insertDeck(user,testDeck);
        List result = deckM.getNames(user);
        assertNotNull(result);
        assertTrue(result.contains(testDeck.getName()));
        System.out.println("InsertDeck Integration test complete.");
    }

    @Test
    public void deleteTest(){
       testDeck.addCard(testCard);
       deckM.insertDeck(user,testDeck);
       deckM.deleteDeck(user,"testDeck");
       Deck result = deckM.getDeck(user,"testDeck");
       assertNotEquals(result.getNumCards(),testDeck.getNumCards());
        System.out.println("Delete Deck Integration test complete.");
    }

    @Test
    public void getNamesTest(){
        List expected = new ArrayList<String>();
        expected.add("deck");
        expected.add("deck2");
        deckM.insertDeck(user,new Deck("deck"));
        deckM.insertDeck(user,new Deck("deck2"));
        List result = deckM.getNames(user);
        assertEquals(expected,result);
        System.out.println("GetNames Integration test complete.");
    }

    @Test
    public void putCardTest(){
        deckM.insertDeck(user,testDeck);
        deckM.putFlashcardInDeck(user,testDeck.getName(),testCard);
        Deck result = deckM.getDeck(user,"testDeck");
        Deck empty = deckM.getDeck(user,"empty");

        assertEquals(result.getCard("testDeck-0"),testCard);
        assertEquals(1,result.getNumCards());
        assertEquals(0,empty.getNumCards());
        System.out.println("PutFlashcardInDeck Integration test complete.");
    }

    @Test
    public void removeCardTest(){
        testDeck.addCard(testCard);
        assertEquals(1,testDeck.getNumCards());
        Deck result = deckM.removeCard(user,testDeck,"testDeck-0");
        assertEquals(0,result.getNumCards());
        assertEquals(null, result.getCard("testDeck-0"));
        System.out.println("Remove Card Integration test complete.");
    }

    @Test
    public void deckSizeTest(){
        testDeck.addCard(testCard);
        deckM.insertDeck(user, testDeck);
        int result = deckM.queryDeckSize(user,"testDeck");
        assertEquals(1,result);
        System.out.println("QueryDeckSize Integration test complete.");
    }

    @After
    public void tearDown(){
        testDB.delete();
    }

}
