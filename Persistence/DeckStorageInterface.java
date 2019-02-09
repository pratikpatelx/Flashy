package Persistence;

interface DeckStorageInterface {
    String[] getallDeckNames();

    void renameDeck(String identifier, String newName);

    void removeDeck(String identifier);
}
