package comp3350.flashy.persistence.DatabaseManagement;

import java.util.ArrayList;
import java.util.Collection;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.FillInTheBlanksFlashcard;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.domain.MultipleChoiceFlashcard;
import comp3350.flashy.persistence.Codifiers.DataTranslationLayer;
import comp3350.flashy.persistence.DatabaseImplementation;

public class DatabaseManager {

    private DatabaseImplementation storage;
    private DataTranslationLayer translationLayer;

    public DatabaseManager(DatabaseImplementation givenStorage) {
        storage = givenStorage;
        translationLayer = new DataTranslationLayer();
        createDefaultData();
    }
    /*
    Deck Methods
     */
    public void inputDeck(String username, String identifier, Deck inputDeck) {
        storage.inputDeck(username,identifier, translationLayer.encodeDeck(inputDeck));
    }


    public Deck getDeck(String username, String identifier) {
        return translationLayer.decodeDeck(storage.getDeck(username, identifier));
    }

    public void removeDeck(String username, String identifier) {
        storage.deleteDeck(username, identifier);
    }

    public Collection getDeckCollection(String username) {
        return storage.getDeckCollection(username);
    }

    private void createDefaultData() {
        storage.inputUser("Stub","");
        ArrayList<String> answers = new ArrayList<>();

        answers.add("test1");
        answers.add("test2");
        answers.add("test3");
        answers.add("test4");


        for (int i = 0; i < 3; i++) {
            String deckName = "DefaultDeck" + i;
            Deck tempDeck = new Deck(deckName);
            for (int j = 0; j < 5; j++) {
                tempDeck.addCard(new Flashcard(
                        deckName + "-" + j,
                        "DefaultCardQuestion" + j,
                        "DefaultCardAnswer" + j));


            }

            tempDeck.addCard(new MultipleChoiceFlashcard("DefaultDeck"+i+"-5","the question",answers));

            tempDeck.addCard(new FillInTheBlanksFlashcard("DefaultDeck"+i+"-6","Default Card Question","Default Card Answer lol stuff here for testingdfsf","Default","Answer"));
            inputDeck("Stub" ,deckName, tempDeck);
        }
    }

    /*
    User Methods
     */

    public Collection<String> getUserCollection(){
        return storage.getUserCollection();
    }

    public void inputUser (String username, String password) {
        storage.inputUser(username, password);
    }
    
    public String getUserPassword (String username){
        return storage.getUserPassword(username);
    }
    
    public void removeUser (String username){
        storage.removeUser(username);
    }
}
