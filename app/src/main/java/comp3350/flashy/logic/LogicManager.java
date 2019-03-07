package comp3350.flashy.logic;

import java.util.Random;

import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.persistence.DatabaseManager;

public class LogicManager implements LogicManagerInterface {
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

    public Flashcard getCard(String cardName){
        return database.getCard(cardName);
    }



    /**
     * printDeck()
     *
     * @param deckName
     *  The name of the deck to be printed
     *
     *  This method is primarily for testing. It outputs the contents of a deck from
     * The database.
     */
    protected void printDeck(String deckName){
        String[][] deck = database.getDeckContents(deckName);

        System.out.println("Deck: " + deckName + "/n");
        for(int i = 0; i < deck.length; i++){
            System.out.println("Card: " + deck[i][0]);
            System.out.println("Q: " + deck[i][1]);
            System.out.println("A: " + deck[i][2] + "\n");
        }

    }

    /**
     * queryDeckMethod()
     *
     * @param deckName
     *      The name of the deck we want to know the size of
     *
     * @return
     *      The number of cards in the deck
     *
     * This method is primarily for testing purposes, it returns the size of the
     * deck requested
     */
    protected int queryDeckSize(String deckName){
        String[][] deck = database.getDeckContents(deckName);
        return deck.length;
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
