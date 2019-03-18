package comp3350.flashy.persistence;

import java.lang.reflect.Array;
import java.util.ArrayList;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;

class DataTranslationLayer {

    public static Deck encodeDeck(Deck deck) {
        Deck result = deck;
        ArrayList cardList = deck.getCards();
        for (int i = 0; i < cardList.size(); i++) {
            Flashcard card = (Flashcard) cardList.get(i);
            if (card instanceof Flashcard) {
                card.setAnswer(encodeFlashcard(card).get(0));
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

    private static ArrayList<String> encodeFlashcard (Flashcard card) {
        ArrayList<String> result = new ArrayList<>();
        result.add(card.getAnswer());
        return result;
    }

    public static Deck decodeDeck(Deck deck) {
        Deck result = deck;
        ArrayList cardList = deck.getCards();
        for (int i = 0; i < cardList.size(); i++) {
            Flashcard card = (Flashcard) cardList.get(i);
            if (card instanceof Flashcard) {
                card.setAnswer(decodeFlashcard(card).get(0));
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

    private static ArrayList<String> decodeFlashcard (Flashcard card) {
        ArrayList<String> result = new ArrayList<>();
        result.add(card.getAnswer());
        return result;
    }
}