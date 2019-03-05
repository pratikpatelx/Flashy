package comp3350.flashy.DomainLogic;

import java.util.ArrayList;

public class Deck{

    private static final String DUMMYNAME = "DUMMY";
    private static final String DUMMYQUESTION = "There are no cards in this deck.";
    private static final String DUMMYANSWER = "You need to create add a card.";

    private ArrayList<Flashcard> cards;
    private String name;
    private int curr; //This is the index of the currently selected flashcard, the one that is displayed in the GUI

    public Deck(String name){
        this.name = name;
        this.cards = new ArrayList();
        this.curr = -1;
    }

    public Deck(String name, Deck other) {
        this.name = name;
        this.cards = other.getCards();
    }


    public ArrayList getCards() {
        return cards;
    }

    public void setDeck(Deck other) {
        this.cards = other.getCards();
    }


    /*
     * addCard()
     *
     * Parameters:
     *  String question: The Question on the flashcard
     *  String answer: The answer to the question, no it's not 42, well not neccesarily
     *
     * This function will execute the process for putting a new "Flashcard" to
     * the deck
     * This function will be called when the "Add Card/Done" button is pressed in the
     * card creation GUI.
     * This function handles the creation of the simple flashcard
     */
    public void addCard(Flashcard noob){

        this.cards.add(noob);
        this.curr++;
    }



    /*
     * deleteCard()
     *
     * Parameters: None
     *
     * This card removes the card in the ArrayList variable "Deck" at the index "curr"
     * Since ArrayLists automatically shift their elements to fill in gaps we should
     * only need special handeling for the case that the card being deleted is the last
     * card in the deck.
     *
     * This function should be called when pressing the delete card button on the
     * deck viewer GUI.
     */
    public void deleteCard(){
        if(this.curr == this.cards.size()-1){
            this.curr = this.cards.size() -1;
        }
        this.cards.remove(curr);
    }




    /**
     * editCard()
     * This method will change the card with the same name as the parameter changed
     * to be the same as the parameter changed.
     * Parameters: Flashcard changed
     *
     *
     * @return returns weather or not the card was successfully edited;
     */
    public boolean editCard(Flashcard changed){
        boolean success = false;
        if(cards.size() >= 0){
            int idx = cards.indexOf(changed);
            cards.get(idx).editCard(changed);
        }
        return success;
    }


    /*
     * nextCard()
     *
     * Parameters: None
     *
     * This function changes the card currently being displayed to the next card
     * in the deck
     *
     * This function should be called when the user swipes right on the deck viewer
     * GUI
     *
     */
    public void nextCard(){
        this.curr = (this.curr + 1)%this.cards.size();
    }


    /*
     * prevCard()
     *
     * Parameters: None
     *
     * This function changes the card currently being displayed to the previous card
     * in the deck
     *
     * This function should be called when the user swipes left on the deck viewer
     * GUI
     */
    public void prevCard(){
        if(this.curr == 0){
            this.curr = this.cards.size()-1;
        }
        else{
            this.curr--;
        }
    }


    /*
     * getCard()
     *
     * this function returns the Flash object in "deck" with the index equal to "curr"
     * if there are no cards in the deck then then a dummy card will be returned
     */
    public Flashcard getCard(){
        Flashcard currCard;
        if(this.cards.size() <= 0){
            currCard = new Flashcard(DUMMYNAME, DUMMYQUESTION, DUMMYANSWER);
        }
        else{
            currCard = this.cards.get(curr);
        }
        return currCard;
    }



    /**
     * getNumCards()
     * @return the number of flashcards in this Deck
     */
    public int getNumCards(){
        return this.cards.size();
    }


    /**
     * toString()
     *
     * will return a string containing information on the deck
     * along with its contents
     */
    public String toString(){
        String info = "Deck: " + this.name + "\n";
        for(int i = 0; i < this.cards.size(); i++){
            info += cards.get(i) + "\n";
        }
        return info;
    }

}
