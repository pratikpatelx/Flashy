package Persistence;

import java.util.ArrayList;
import java.util.Enumeration;

public class DeckStorageManager extends DatabaseManager implements DeckStorageInterface {

    @Override
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

    @Override
    public void renameDeck(String identifier, String newName) {
        ArrayList temp = storage.get(defaultDeck);
        storage.remove(identifier);
        storage.put(newName, temp);
    }

    @Override
    public void removeDeck(String identifier) {
        storage.remove(identifier);
    }
}
