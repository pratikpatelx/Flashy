package comp3350.flashy.persistence.Codifiers;

import java.util.ArrayList;
import java.util.StringTokenizer;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.FitBcard;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.domain.MultipleChoiceFlashcard;
import comp3350.flashy.domain.TrueFalseCard;

public class Decoder {
    String delimiter = null;

    public Decoder(String givenDelimiter) {
        delimiter = givenDelimiter;
    }

    public Deck decodeDeck(Deck deck) {
        Deck result = new Deck(deck.getName());
        ArrayList<Flashcard> cardList = new ArrayList<Flashcard>();
        cardList.addAll(deck.getCards());
        for (int i = 0; i < cardList.size(); i++) {
            result.addCard(decodeCard(cardList.get(i)));
        }
        return result;
    }

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
                result =decodeFillInTheBlanksFlashcard(cardName, question, tokenizedString);
                break;
            case '2':
                result = decodeTrueFalseFlashcard(cardName, question, tokenizedString);
                break;
            case '3':
                result = decodeMultipleChoiceFlashcard(cardName, question, tokenizedString);
                break;
        }
        return result;
    }

    private Flashcard decodeMultipleChoiceFlashcard(String cardName, String question, ArrayList<String> tokenizedString) {
        ArrayList<String> answerList = new ArrayList<>();
        for (int i = 1; i < tokenizedString.size(); i++) {
            answerList.add(tokenizedString.get(i));
        }

        return new MultipleChoiceFlashcard(cardName, question, answerList);
    }

    private Flashcard decodeTrueFalseFlashcard(String cardName, String question, ArrayList<String> tokenizedString) {
        String decodedAnswer = tokenizedString.get(1);
        String truthValue = tokenizedString.get(2);

        return new TrueFalseCard(cardName, question, decodedAnswer, truthValue);
    }

    private Flashcard decodeFillInTheBlanksFlashcard(String cardName, String question, ArrayList<String> tokenizedString) {
        String decodedAnswer = tokenizedString.get(1);
        String firstPart = tokenizedString.get(2);
        String secondPart = tokenizedString.get(3);

        return new FitBcard(cardName, question, decodedAnswer, firstPart, secondPart);
    }

    private Flashcard decodeFlashcard (String cardName, String question, ArrayList<String> tokenizedString) {
        String decodedAnswer = tokenizedString.get(1);

        return new Flashcard(cardName, question, decodedAnswer);
    }

    private ArrayList<String> tokenizeString(String string) {
        ArrayList<String> tokens = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(string, delimiter);
        while (tokenizer.hasMoreElements()) {
            tokens.add(tokenizer.nextToken());
        }
        return tokens;
    }
}
