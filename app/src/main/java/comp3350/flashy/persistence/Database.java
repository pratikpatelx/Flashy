package comp3350.flashy.persistence;
//package comp3350.flashy.logic;

import java.util.Hashtable;

import comp3350.flashy.logic.LogicInterface;

public class Database implements DatabaseInterface {
    private Hashtable<String, LogicInterface> storage;

    /**
     * Constructors
     */

    public Database() {
        storage = new Hashtable<>();
    }

    /**
     * Mutators
     */

    void setStorage(Hashtable<String, LogicInterface> newStorage) {
        this.storage = newStorage;
    }

    Hashtable getStorage() {
        return storage;
    }

    /**
     * Methods
     */

    @Override
    public void storeData(String identifier, LogicInterface newData) {
        storage.put(identifier, newData);
    }

    @Override
    public LogicInterface getData(String identifier) {
        return storage.get(identifier);
    }


}