package Persistence;

import java.util.ArrayList;


interface DataIOInterface {

    void inputDeck(String identifier, String[][] inputDeck);

    String[][] getAllCards();

    String[][] getDeckContents(String identifier);

    String[][] cardDataGenerator(int numCards, ArrayList deck);
}
