package comp3350.flashy.persistence;

import java.util.ArrayList;

import comp3350.flashy.domain.Deck;

//Will act as a buffer between the class containing the HSQLDB and the logic layer. For now though, it is itself the acting database.
public class DatabaseManager {
    DatabaseImplementation storage = new DatabaseStub(); //Change this based on which database implementation is needed

    public void inputDeck(String identifier, Deck inputDeck) {
        storage.inputDeck(identifier, inputDeck);
    }

    public Deck getDeck(String identifier) {
        return storage.getDeck(identifier);
    }

    public ArrayList<String> getNames(){

        return storage.getAllDeckNames();
    }


}
