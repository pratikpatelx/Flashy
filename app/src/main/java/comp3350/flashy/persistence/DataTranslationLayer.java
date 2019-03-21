package comp3350.flashy.persistence;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.StringTokenizer;

import comp3350.flashy.domain.FillInTheBlanksFlashcard;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.MultipleChoiceFlashcard;
import comp3350.flashy.domain.TrueFalseCard;

class DataTranslationLayer {

    private static final String delimiter = "|";


    public static Deck encodeDeck(Deck deck) {
        Deck result = new Deck(deck.getName());
        ArrayList<Flashcard> cardList = deck.getFlashcards();
        for (int i = 0; i < cardList.size(); i++) {
            result.addCard(encodeCard(cardList.get(i)));
        }
        return result;
    }

    private static Flashcard encodeCard (Flashcard flashcard) {
        String answer = null;

        if (flashcard.isRegularFlashcard()) {
            answer = encodeFlashcard(flashcard);
        } else if (flashcard.isMultipleChoiceFlashcard()) {
            answer = encodeFlashcard(flashcard);
        } else if (flashcard.isFillInTheBlanksFlashcard()) {
            answer = encodeFlashcard(flashcard);
        } else if (flashcard.isTrueFalseFlashcard()) {
            answer = encodeFlashcard(flashcard);
        }

        return new Flashcard(flashcard.getCardName(), flashcard.getQuestion(), answer);
    }

    /**
     * Encodes a regular Flashcard
     * "0|Answer"|
     * @param card
     * @return
     */
    private static String encodeFlashcard (Flashcard card) {
        String result = "";
        String answer = card.getAnswer();
        result.concat(card.getCardType() + delimiter);
        return result;
    }

    /**
     * Encodes a True/False Flashcard
     * "2|Answer"|
     * @param card
     * @return
     */
    private static String encodeTrueFalseFlashcard (TrueFalseCard card) {
        String result = "";
        String answer = card.getAnswer();
        result.concat(card.getCardType() + delimiter);
        return result;
    }

    /**
     * Encodes a Fill in the Blanks Flashcard
     * 1|Before_answer|Answer|After_answer|
     * @param card
     * @return
     */
    private static String encodeFillInTheBlankFlashcard (FillInTheBlanksFlashcard card) {
        String result = "";
        String answer = card.getAnswer();
        String firstPart = card.getFirstPart();
        String lastPart = card.getLastPart();


        result.concat(card.getCardType() + delimiter);
        result.concat(answer + delimiter);
        result.concat(firstPart + delimiter);
        result.concat(lastPart + delimiter);

        return result;
    }

    /**
     * Encodes a Multiple Choice Flashcard
     * 3|Correct_Answer|Answer_Three|Answer_Two|Answer_Four|etc...|
     * @param card
     * @return
     */
    private static String encodeMultipleChoiceFlashcard (MultipleChoiceFlashcard card) {
        String result = "";
        String answer = card.getAnswer();
        ArrayList<String> choices = card.getAnswers();

        result.concat(card.getCardType() + delimiter);
        for (int i = 0; i < choices.size(); i++) {
            result.concat(choices.get(i) + delimiter);
        }

        return result;
    }

    public static Deck decodeDeck(Deck deck) {
        Deck result = new Deck(deck.getName());
        ArrayList<Flashcard> cardList = deck.getFlashcards();
        for (int i = 0; i < cardList.size(); i++) {
            result.addCard(decodeCard(cardList.get(i)));
        }
        return result;
    }

    private static Flashcard decodeCard(Flashcard flashcard) {
        Flashcard result = null;
        String cardName = flashcard.getCardName();
        String question = flashcard.getQuestion();
        String answer = flashcard.getAnswer();
        switch (flashcard.getAnswer().charAt(0)) {
            case '0':
                result = decodeFlashcard(cardName, question, answer);
                break;
            case '1':
                result =decodeFillInTheBlanksFlashcard(cardName, question, answer);
                break;
            case '2':
                result = decodeTrueFalseFlashcard(cardName, question, answer);
                break;
            case '3':
                result = decodeMultipleChoiceFlashcard(cardName, question, answer);
                break;
        }
        return result;
    }

    private static Flashcard decodeMultipleChoiceFlashcard(String cardName, String question, String answer) {
    }

    private static Flashcard decodeTrueFalseFlashcard(String cardName, String question, String answer) {
    }

    private static Flashcard decodeFillInTheBlanksFlashcard(String cardName, String question, String answer) {
    }

    private static Flashcard decodeFlashcard (String cardName, String question, String answer) {
        Flashcard result = null;
        String decodedAnswer = tokenizeString(answer).get(1);
        return new Flashcard(cardName, question, answer);
    }

    private static ArrayList<String> tokenizeString(String string) {
        ArrayList<String> tokens = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(string, delimiter);
        while (tokenizer.hasMoreElements()) {
            tokens.add(tokenizer.nextToken());
        }
        return tokens;
    }
}