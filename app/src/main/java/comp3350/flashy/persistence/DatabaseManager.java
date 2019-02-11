package comp3350.flashy.persistence;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import comp3350.flashy.logic.Flashcard;

//Will act as a buffer between the class containing the HSQLDB and the logic layer. For now though, it is itself the acting database.
public class DatabaseManager {
    private Hashtable<String, ArrayList> storage;

    //The Oracle Deck contains all flashcards currently in existence.
    private String defaultDeck = "THE_ORACLE_DECK";

    /**
     * Constructors
     */

    public DatabaseManager() {
        storage = new Hashtable<>();
        storage.put(defaultDeck, new ArrayList());
    }


    /**
     * Mutators
     */

    void setStorage(Hashtable<String, ArrayList> newStorage) {
        this.storage = newStorage;

    }

    Hashtable getStorage() {
        return storage;
    }

    /**
     * Methods
     */

    //Deck Methods
    //@Override
    public void inputDeck(String identifier, String[][] inputDeck) {
        storage.remove(identifier);

        ArrayList result = new ArrayList();

        for (String[] anInputDeck : inputDeck) {
            String tempCardName = anInputDeck[0];
            String tempQuestion = anInputDeck[1];
            String tempAnswer = anInputDeck[2];
            result.add(new Flashcard(tempCardName, tempQuestion, tempAnswer));
        }

        storage.put(identifier, result);
    }

    //@Override
    public String[] getallDeckNames() {
        String[] result = new String[storage.size()];
        Enumeration enu = storage.keys();

        int i = 0;
        if (enu.hasMoreElements()) {
            do {
                result[i] = enu.toString();
                i++;
            } while (enu.hasMoreElements());
        }

        return result;
    }

    // @Override
    public void renameDeck(String identifier, String newName) {
        ArrayList temp = getDeck(identifier);
        storage.remove(identifier);
        storage.put(newName, temp);
    }

    // @Override
    public void removeDeck(String identifier) {
        storage.remove(identifier);
    }

    //Card Methods

    //@Override
    public void addCard(Flashcard newCard) {
        ArrayList temp = getDeck(defaultDeck);
        temp.add(newCard);
    }

    //@Override
    public void addCardToDeck(String identifier, Flashcard newCard) {
        ArrayList temp = getDeck(identifier);//used to incorrectly say default deck

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

    //@Override
    public void removeCardFromDeck(String identifier, String cardName) {
        ArrayList temp = storage.get(identifier);
        temp.remove(cardName);
    }

    //@Override
    public void removeCardFromAll(String cardName) {
        ArrayList temp;
        Enumeration enu = storage.keys();

        while (enu.hasMoreElements()) {
            removeCardFromDeck(enu.toString(), cardName);
        }
    }

    //@Override
    public void editCard(String cardName, String newQuestion, String newAnswer) {
        Enumeration keyList = storage.keys();
        boolean more = true;
        Flashcard doppel = new Flashcard(cardName, newQuestion, newAnswer);
        Object currO = keyList.nextElement();
        String currS = null;
        if (currO instanceof String) {
            currS = (String) currO;
        }

        //System.out.println(keyList);
        if (currS != null) {
            while (more) {
                ArrayList temp = getDeck(currS);


                temp.remove(doppel);
                temp.add(doppel);
                more = keyList.hasMoreElements();

                if (more) {
                    currO = keyList.nextElement();
                    if (currO instanceof String) {
                        currS = (String) currO;
                    }
                }
            }
        }
    }


    /**
     * @param cardName
     * @return Will return null if card does not exist
     */
    public Flashcard getCard(String cardName) {
        Enumeration keyList = storage.keys();
        boolean more = true;
        Flashcard doppel = new Flashcard(cardName, "IDC", "IDC");
        Flashcard stick = null;
        Object stickO = null;
        int idx = -1;

        Object currO = keyList.nextElement();
        String currS = null;
        boolean found = false;
        if (currO instanceof String) {
            currS = (String) currO;
        }

        //System.out.println(keyList);
        if (currS != null) {
            while (more && !found) {
                ArrayList temp = getDeck(currS);

                idx = temp.indexOf(doppel);

                if (idx >= 0) {

                    stickO = temp.get(idx);
                    if (stickO instanceof Flashcard) {
                        stick = (Flashcard) stickO;
                        found = true;
                    }

                }


                more = keyList.hasMoreElements();

                if (more && !found) {
                    currO = keyList.nextElement();
                    if (currO instanceof String) {
                        currS = (String) currO;
                    }
                }

            }
        }

        return stick;
    }

    //Get Data Methods

    //@Override
    public String[][] getAllCards() {
        int deckSize = getDeckSize(defaultDeck);
        String[][] result = new String[deckSize][3];
        ArrayList temp = getDeck(defaultDeck);

        if (temp != null) {
            result = cardDataGenerator(deckSize, temp);
        }

        return result;
    }

    //@Override
    public String[][] getDeckContents(String identifier) {
        int deckSize = getDeck(identifier).size();
        String[][] result = new String[deckSize][3];
        ArrayList temp = getDeck(identifier);

        if (temp != null) {
            result = cardDataGenerator(deckSize, temp);
        }

        return result;
    }

    //Private methods

    private String[][] cardDataGenerator(int numCards, ArrayList deck) {
        String[][] result = new String[numCards][3];

        for (int i = 0; i < numCards; i++) {
            result[i][0] = ((Flashcard) deck.get(i)).getCardName();
            result[i][1] = ((Flashcard) deck.get(i)).getQuestion();
            result[i][2] = ((Flashcard) deck.get(i)).getAnswer();
        }
        return result;
    }

    private ArrayList getDeck(String identifier) {
        ArrayList deck = storage.get(identifier);

        if (deck == null) {
            storage.put(identifier, new ArrayList());
        }

        return storage.get(identifier);
    }

    private int getDeckSize(String identifier) {
        return storage.get(identifier).size();
    }
}
