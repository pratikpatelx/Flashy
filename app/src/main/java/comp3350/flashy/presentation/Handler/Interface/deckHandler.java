package comp3350.flashy.presentation.Handler.Interface;

import java.util.ArrayList;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.FillInTheBlanksFlashcard;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.domain.MultipleChoiceFlashcard;
import comp3350.flashy.logic.DeckManager;
import comp3350.flashy.presentation.Handler.Interface.util.DummyMaker;


//purpose: Handle all deck activity within the ui
public class deckHandler {
    private static int deckSize = 0; // this is to hold max index



    private DeckManager deckM;
    private String username;
    private Deck currDeck;
    private String deckName;

    public deckHandler(){
        deckM = new DeckManager();
    }




    //retrieve content

    //this returns the the head and body of the flashcard
    public String[] getContent(int index) {
        Flashcard curr;
        String[] result = new String[3];

        curr = currDeck.getCard(deckName + "-" + index);

        if(curr == null){
            curr = DummyMaker.makeDummy();
        }


        result[0] = curr.getQuestion();
        result[1] = curr.getAnswer();

        return result;
    }

    public ArrayList<String> getMCContent(int index) {
        ArrayList<String> result = new ArrayList<>();

        MultipleChoiceFlashcard card = (MultipleChoiceFlashcard) currDeck.getCard(deckName + "-" + index);

        result.add(card.getQuestion());
        result.addAll(card.getAnswers());

        return result;
    }

    public int getFlashcardTypeByIndex(int index) {
        Flashcard curr = currDeck.getCard(deckName + "-" + index);

        if(curr == null){
            curr = DummyMaker.makeDummy();
        }

        return Integer.parseInt(curr.getCardType());
    }


    //note to self: do a better job...
    public int getIndexByFlashCard(String name) {
        Flashcard curr = currDeck.getCard(name);

        //change this when changing (deckName + index) to (deckName +"-"+ index)
        String[] token = curr.getCardName().split("-");
        int index = Integer.parseInt(token[1]);

        return index;
    }

    public int getDeckSize() {
        deckSize = deckM.queryDeckSize(username, currDeck.getName());

        return deckSize;
    }

    public Deck getCurrDeck() {
        return currDeck;
    }

    public String getDeckName() {
        return currDeck.getName();
    }

    public ArrayList<Flashcard> getAllCards() {
        currDeck = deckM.getDeck(username, deckName);

        return new ArrayList<Flashcard>(currDeck.getFlashcards());
    }

    public ArrayList<String> getNames() {
        return new ArrayList<String>(deckM.getNames(username));
    }

    public ArrayList<String> getMenuData() {
        ArrayList<String> result = new ArrayList<String>();

        result.add("Standard");
        result.add("Fill in the blank");
        result.add("Multiple choice");

        return result;
    }




    //Manipulate Content

    public void setCurrDeck(String name) {
        Deck newDeck = deckM.getDeck(username, name);

        if (newDeck != null) {
            currDeck = newDeck;
            deckName = newDeck.getName();
            deckSize = currDeck.getNumCards();
            System.out.println("found deck");
        } else {
            currDeck = new Deck(name);
            deckM.insertDeck(username,currDeck);
            deckSize = currDeck.getNumCards();
            System.out.println("created new deck");
        }
    }

    public void setUsername(String name) {
        username = name;
    }

    public String getUsername() {
        return username;
    }





    //User functions

    //adds cards w/ name (DECKNAME-DECKSIZE++) as to be stored in database
    public void saveCard(String head, String content, int type) {
        switch (type) {
            case 0:
                deckM.putFlashcardInDeck(username, deckName, new Flashcard((deckName + "-" + deckSize), head, content));
                deckSize++;
                break;
            case 1:
                deckM.putFlashcardInDeck(username, deckName, new FillInTheBlanksFlashcard((deckName + "-" + deckSize), head, content, " "));
                break;
        }
    }

    public void saveMCCard(String question, ArrayList<String> answer) {
        deckM.putFlashcardInDeck(username, deckName, new MultipleChoiceFlashcard((deckName + "-" + deckSize), question, answer));
        deckSize++;
    }

    public void deleteCard(int index) {
        deckM.removeCard(username, currDeck, (deckName + "-" + index));
        deckSize--;
    }

    public void deleteDeck(String dName) {
        deckM.deleteDeck(username, dName);
    }

}
