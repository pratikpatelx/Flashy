package comp3350.flashy.persistence;

import java.util.ArrayList;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;

class DataTranslationLayer {

    public static Deck encodeDeck(Deck deck) {
        Deck result = new Deck(deck.getName());
        ArrayList cardList = deck.getCards();
        for (int i = 0; i < cardList.size(); i++) {
            Flashcard card = (Flashcard) cardList.get(i);
            if (card instanceof Flashcard) {
                card.setAnswer(encodeFlashcard(card));
            } // else if (card instanceof MultipleChoiceFlashcard) {
//
//            } else if (card instanceof FillInTheBlankFlashcard) {
//
//            } else if (card instanceof TrueFalseFlashcard) {
//
//            }

            result.addCard(card);
        }
        return result;
    }

    private static String encodeFlashcard (Flashcard card) {
        String result = card.getAnswer();
        return result;
    }

    public static Deck decodeDeck(Deck deck) {
        Deck result = new Deck(deck.getName());
        ArrayList cardList = deck.getCards();
        for (int i = 0; i < cardList.size(); i++) {
            Flashcard card = (Flashcard) cardList.get(i);
            if (card instanceof Flashcard) {
                card.setAnswer(decodeFlashcard(card));
            } // else if (card instanceof MultipleChoiceFlashcard) {
//
//            } else if (card instanceof FillInTheBlankFlashcard) {
//
//            } else if (card instanceof TrueFalseFlashcard) {
//
//            }

            result.addCard(card);
        }
        return result;
    }

    private static String decodeFlashcard (Flashcard card) {
        String result = card.getAnswer();
        return result;
    }
}