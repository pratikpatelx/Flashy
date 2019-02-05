package Logic;

interface DeckInterface{

    void addCard(String newQuestion, String newAnswer);

    void deleteCard(FlashcardInterface selectedCard);

    void editCard(FlashcardInterface selectedCard, String newQuestion, String newAnswer);
}