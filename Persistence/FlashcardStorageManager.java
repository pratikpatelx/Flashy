package Persistence;

import java.util.ArrayList;
import java.util.Enumeration;

import DomainLogic.Flashcard;

public class FlashcardStorageManager extends DatabaseManager implements FlashcardStorageInterface {

    @Override
    public void addCard(Flashcard newCard) {
        ArrayList temp = storage.get(defaultDeck);
        temp.add(newCard);
    }

    @Override
    public void addCardToDeck(String identifier, Flashcard newCard) {
        ArrayList temp = storage.get(defaultDeck);

        if (temp != null) {
            temp.add(newCard);
            addCard(newCard);
        } else {
            temp = new ArrayList();
            temp.add(newCard);
            storage.put(identifier, temp);
            addCard(newCard);
        }
    }

    @Override
    public void removeCardFromDeck(String identifier, String cardName) {
        ArrayList temp = storage.get(identifier);
        temp.remove(cardName);
    }

    @Override
    public void removeCardFromAll(String cardName) {
        ArrayList temp;
        Enumeration enu = storage.keys();

        while (enu.hasMoreElements()) {
            removeCardFromDeck(enu.toString(), cardName);
        }
    }

    @Override
    public void editCard(String cardName, String newQuestion, String newAnswer) {
        Enumeration keyList = storage.keys();

        while (keyList.hasMoreElements()) {
            ArrayList temp = storage.get(keyList.toString());
            temp.remove(cardName);
            temp.add(new Flashcard(cardName, newQuestion, newAnswer));
        }
    }


    private Flashcard getFlashcard(ArrayList deck, String cardName) {
        Flashcard result = null;

        for (int i = 0; i < deck.size(); i++) {
            Flashcard temp = (Flashcard) deck.get(i);
            if ((temp.getCardName()).equals(cardName)) {
                result = temp;
            }
        }

        return result;
    }
}