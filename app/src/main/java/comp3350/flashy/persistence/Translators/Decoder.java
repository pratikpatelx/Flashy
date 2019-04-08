package comp3350.flashy.persistence.Translators;

import java.util.ArrayList;
import java.util.StringTokenizer;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.FillInTheBlanksFlashcard;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.domain.MultipleChoiceFlashcard;

public class Decoder {
    String delimiter = null;

    /**
     * Constructor
     *
     * @param givenDelimiter
     */
    public Decoder(String givenDelimiter) {
        delimiter = givenDelimiter;
    }

    /**
     * Decode a deck of Flashcard type objects
     *
     * @param deck
     * @return
     */
    public Deck decodeDeck(Deck deck) {
        if (deck != null) {
            Deck result = new Deck(deck.getName());
            ArrayList<Flashcard> cardList = new ArrayList<Flashcard>(deck.getFlashcards());

            for (int i = 0; i < cardList.size(); i++) {
                result.addCard(decodeCard(cardList.get(i)));
            }
            return result;
        } else
            return null;
    }

    /**
     * Decode a single card of Flashcard type
     *
     * @param flashcard
     * @return
     */
    private Flashcard decodeCard(Flashcard flashcard) {
        Flashcard result = null;
        String cardName = flashcard.getCardName();
        String question = flashcard.getQuestion();
        ArrayList<String> tokenizedString = tokenizeString(flashcard.getAnswer());
        switch (flashcard.getAnswer().charAt(0)) {
            case '0':
                result = decodeFlashcard(cardName, question, tokenizedString);
                break;
            case '1':
                result = decodeFillInTheBlanksFlashcard(cardName, question, tokenizedString);
                break;
            case '2':
                result = decodeMultipleChoiceFlashcard(cardName, question, tokenizedString);
                break;
        }


        return result;
    }

    /**
     * Decode a Multiple Choice Flashcard
     *
     * @param cardName
     * @param question
     * @param tokenizedString
     * @return
     */
    private Flashcard decodeMultipleChoiceFlashcard(String cardName, String question, ArrayList<String> tokenizedString) {
        ArrayList<String> answerList = new ArrayList<>();
        for (int i = 1; i < tokenizedString.size(); i++) {
            answerList.add(tokenizedString.get(i).substring(0, tokenizedString.get(i).length() - 1));
        }

        return new MultipleChoiceFlashcard(cardName, question, answerList);
    }

    /**
     * Decode a Fill in the Blanks Flashcard
     *
     * @param cardName
     * @param question
     * @param tokenizedString
     * @return
     */
    private Flashcard decodeFillInTheBlanksFlashcard(String cardName, String question, ArrayList<String> tokenizedString) {
        String decodedAnswer = tokenizedString.get(1);


        return new FillInTheBlanksFlashcard(cardName, question, decodedAnswer, "");
    }

    /**
     * Decode a regular Flashcard
     *
     * @param cardName
     * @param question
     * @param tokenizedString
     * @return
     */
    private Flashcard decodeFlashcard(String cardName, String question, ArrayList<String> tokenizedString) {
        String decodedAnswer = tokenizedString.get(1);

        return new Flashcard(cardName, question, decodedAnswer);
    }

    /**
     * Tokenize a given string based on the global delimiter
     *
     * @param string
     * @return
     */
    private ArrayList<String> tokenizeString(String string) {
        ArrayList<String> tokens = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(string, delimiter);
        while (tokenizer.hasMoreElements()) {
            tokens.add(tokenizer.nextToken());
        }

        return tokens;
    }
}
