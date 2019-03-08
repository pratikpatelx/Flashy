package comp3350.flashy;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

import comp3350.flashy.logic.LogicManager;

public class ShuffleDeckTest {

    /**shuffleDeckTest
     *
     * This test will confirm that the shuffleDeck method is returning a shuffled deck.
     */
    @Test
    public void shuffleDeckTest(){
        System.out.println("\n Running shuffleDeck test \n");
        LogicManager lgc = new LogicManager();
        String testDeck = "tempD";
        String[][] initial = {{"F1","Q1","A1"},{"F2","Q2","A2"},{"F3","Q3","A3"}};
        for(int i=0;i<initial.length;i++){
            lgc.putFlashcardInDeck(testDeck,initial[i][0],initial[i][1],initial[i][2]);
        }
        assertFalse("shuffleDeck test failed",Arrays.deepEquals(initial,lgc.shuffleDeck(testDeck)));
        System.out.println("shuffleDeck test complete.");
    }
}
