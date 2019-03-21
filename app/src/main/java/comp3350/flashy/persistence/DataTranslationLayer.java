package comp3350.flashy.persistence;

import java.util.ArrayList;

import comp3350.flashy.domain.FillInTheBlanksFlashcard;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.MultipleChoiceFlashcard;
import comp3350.flashy.domain.TrueFalseCard;

class DataTranslationLayer {

    private static String delimiter = "|";


    public static Deck encodeDeck(Deck deck) {
        Deck result = new Deck(deck.getName());
        ArrayList cardList = deck.getFlashcards();
        for (int i = 0; i < cardList.size(); i++) {
            Flashcard flashcard = (Flashcard) cardList.get(i);

            if (flashcard.isRegularFlashcard()) {
                flashcard.setAnswer(encodeFlashcard(flashcard));
            } else if (flashcard.isMultipleChoiceFlashcard()) {
                flashcard.setAnswer(encodeFlashcard(flashcard));
            } else if (flashcard.isFillInTheBlanksFlashcard()) {
                flashcard.setAnswer(encodeFlashcard(flashcard));
            } else if (flashcard.isTrueFalseFlashcard()) {
                flashcard.setAnswer(encodeFlashcard(flashcard));
            }

            result.addCard(flashcard);
        }
        return result;
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
        result.concat(firstPart + delimiter);
        result.concat(answer + delimiter);
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
        ArrayList cardList = deck.getFlashcards();
        for (int i = 0; i < cardList.size(); i++) {
            Flashcard flashcard = (Flashcard) cardList.get(i);
            if (flashcard instanceof Flashcard) {
                flashcard.setAnswer(decodeFlashcard(flashcard));
            } // else if (flashcard instanceof MultipleChoiceFlashcard) {
//
//            } else if (flashcard instanceof FillInTheBlankFlashcard) {
//
//            } else if (flashcard instanceof TrueFalseFlashcard) {
//
//            }

            result.addCard(flashcard);
        }
        return result;
    }

    private static String decodeFlashcard (Flashcard flashcard) {
        String result = flashcard.getAnswer();
        return result;
    }
}