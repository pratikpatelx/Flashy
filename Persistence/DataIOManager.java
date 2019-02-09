package Persistence;

import java.util.ArrayList;

import DomainLogic.Flashcard;

public class DataIOManager extends DatabaseManager implements DataIOInterface {

    @Override
    public void inputDeck(String identifier, String[][] inputDeck) {
        storage.remove(identifier);

        ArrayList result = new ArrayList();

        for (String[] anInputDeck : inputDeck) {
            String tempCardName = anInputDeck[0];
            String tempQuestion = anInputDeck[1];
            String tempAnswer = anInputDeck[2];
            result.add(new Flashcard(tempCardName, tempQuestion, tempAnswer));
        }

        storage.put(identifier, result);
    }

    @Override
    public String[][] getAllCards() {
        int deckSize = storage.get(defaultDeck).size();
        String[][] result = new String[deckSize][3];
        ArrayList temp = storage.get(defaultDeck);

        if (temp != null) {
            result = cardDataGenerator(deckSize, temp);
        }

        return result;
    }

    @Override
    public String[][] getDeckContents(String identifier) {
        int deckSize = storage.get(identifier).size();
        String[][] result = new String[deckSize][3];
        ArrayList temp = storage.get(identifier);

        if (temp != null) {
            result = cardDataGenerator(deckSize, temp);
        }

        return result;
    }

    //Private methods

    public String[][] cardDataGenerator(int numCards, ArrayList deck) {
        String[][] result = new String[numCards][3];

        for (int i = 0; i < numCards; i++) {
            result[i][0] = ((Flashcard) deck.get(i)).getCardName();
            result[i][1] = ((Flashcard) deck.get(i)).getQuestion();
            result[i][2] = ((Flashcard) deck.get(i)).getAnswer();
        }
        return result;
    }
}
