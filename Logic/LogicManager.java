package Logic;

import java.util.Random;

import DomainLogic.Flashcard;
import Persistence.DatabaseManager;

public class LogicManager implements LogicMangerInterface {
    private DatabaseManager database = new DatabaseManager();

    @Override
    public void addFlashcard(String cardName, String question, String answer) {
        database.addCard(new Flashcard(cardName, question, answer));
    }

    @Override
    public void putFlashcardIntoDeck(String deckName, String cardName, String question, String answer) {
        database.addCardToDeck(deckName, new Flashcard(cardName, question, answer));
    }

    @Override
    public void putFlashcardIntoDeck(String deckName, Flashcard flashcard) {
        database.addCardToDeck(deckName, flashcard);
    }

    @Override
    public void editFlashcard(String cardName, String newQuestion, String newAnswer) {
        database.editCard(cardName, newQuestion, newAnswer);
    }

    @Override
    public void removeFlashcardFromDeck(String deckName, String cardName) {
        database.removeCardFromDeck(deckName, cardName);
    }

    @Override
    public void removeCardFromAll(String cardName) {
        database.removeCardFromAll(cardName);
    }

    //Shuffles the deck randomly
    @Override
    public String[][] shuffleDeck(String deckName) {
        Random rand = new Random();
        String[][] result = database.getDeckContents(deckName);
        int deckLength = result.length;
        int n;

        int i = -1;
        while (i++ < deckLength - 1) {
            n = rand.nextInt(deckLength);
            String tempCardName = result[n][0];
            String tempQuestion = result[n][1];
            String tempAnswer = result[n][2];

            result[n][0] = result[i][0];
            result[n][1] = result[i][1];
            result[n][2] = result[i][2];

            result[i][0] = tempCardName;
            result[i][1] = tempQuestion;
            result[i][2] = tempAnswer;

        }

        database.inputDeck(deckName, result);
        return result;
    }
}
