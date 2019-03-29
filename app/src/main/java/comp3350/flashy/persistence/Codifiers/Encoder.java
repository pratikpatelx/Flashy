package comp3350.flashy.persistence.Codifiers;

import java.util.ArrayList;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.FillInTheBlanksFlashcard;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.domain.MultipleChoiceFlashcard;
import comp3350.flashy.domain.TrueFalseCard;

public class Encoder {
    String delimiter;

    /**
     * Constructor
     * @param givenDelimiter
     */
    public Encoder(String givenDelimiter) {
        delimiter = givenDelimiter;
    }

    /**
     * Encode an entire deck of Flashcard type objects
     * @param deck
     * @return
     */
    public Deck encodeDeck(Deck deck) {
        Deck result = new Deck(deck.getName());
        ArrayList<Flashcard> cardList = new ArrayList<Flashcard>(deck.getFlashcards());
        System.out.println(cardList);
        for (int i = 0; i < cardList.size(); i++) {
            result.addCard(encodeCard(cardList.get(i)));
        }
        return result;
    }

    /**
     * Encode a single flashcard type object
     * @param flashcard
     * @return
     */
    private Flashcard encodeCard (Flashcard flashcard) {
        String answer = "";

        if (flashcard.isRegularFlashcard()) {
            answer = encodeFlashcard(flashcard);
        } else if (flashcard.isMultipleChoiceFlashcard()) {
            answer = encodeFlashcard(flashcard);
        } else if (flashcard.isFillInTheBlanksFlashcard()) {
            answer = encodeFillInTheBlankFlashcard((FillInTheBlanksFlashcard) flashcard);
        } else if (flashcard.isTrueFalseFlashcard()) {
            answer = encodeFlashcard(flashcard);
        }

        System.out.println("Encoding card:" + flashcard.getCardName() + ", encoded answer: " + answer);

        return new Flashcard(flashcard.getCardName(), flashcard.getQuestion(), answer);
    }

    /**
     * Encodes a regular Flashcard
     * 0|Answer|
     * @param card
     * @return
     */
    private String encodeFlashcard (Flashcard card) {
        String result = "";
        String answer = card.getAnswer();
        result+= card.getCardType() + delimiter;
        result+= answer + delimiter;
        return result;
    }

    /**
     * Encodes a True/False Flashcard
     * 2|Answer|T/F value|
     * @param card
     * @return
     */
    private String encodeTrueFalseFlashcard (TrueFalseCard card) {
        String result = "";
        String answer = card.getAnswer();
        result.concat(card.getCardType() + delimiter);
        result.concat(answer + delimiter);
        result.concat(card.getTruthValue() + delimiter);
        return result;
    }

    /**
     * Encodes a Fill in the Blanks Flashcard
     * 1|Answer|Before_answer|After_answer|
     * @param card
     * @return
     */
    private String encodeFillInTheBlankFlashcard (FillInTheBlanksFlashcard card) {
        String result = "";
        String answer = card.getAnswer();
        String firstPart = card.getFirstPart();
        String lastPart = card.getLastPart();


        result+=card.getCardType() + delimiter;
        result+=answer + delimiter;


        return result;
    }

    /**
     * Encodes a Multiple Choice Flashcard
     * 3|Correct_Answer|Answer_Three|Answer_Two|Answer_Four|etc...|
     * @param card
     * @return
     */
    private String encodeMultipleChoiceFlashcard (MultipleChoiceFlashcard card) {
        String result = "";
        String answer = card.getAnswer();
        ArrayList<String> choices = new ArrayList<String>(card.getAnswers());

        card.getAnswers();

        result.concat(card.getCardType() + delimiter);
        for (int i = 0; i < choices.size(); i++) {
            result.concat(choices.get(i) + delimiter);
        }

        return result;
    }
}
