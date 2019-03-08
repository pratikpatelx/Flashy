package comp3350.flashy.domain;
import java.util.ArrayList;

public class Deck{



    private static final String DUMMYNAME = "DUMMY";
    private static final String DUMMYQUESTION = "Why did you give me this dummy?";
    private static final String DUMMYANSWER = "There are either no cards in the "
            + "deck or you asked for a specific card that does not exist";

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
        this.validateNames();
    }


    public ArrayList getCards() {
            return cards;
    }

    public void setDeck(Deck other) {
        this.cards = other.getCards();
        this.validateNames();
    }


    public String getName(){
        return this.name;
    }

    public void setDeckName(String newName){
        this.name = newName;
        this.validateNames();
    }


    /**
     * validateNames()
     * This function will iterate through the cards and ensures each one has the
     * correct name
     */
    public final void validateNames(){
        String correct;
        Flashcard card;
        for(int i = 0; i < this.cards.size(); i++){
            correct = this.name+ "-" + (i);
            card = this.cards.get(i);
            if(!card.getCardName().equals(correct)){
                card.setCardName(correct);
            }
        }
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



    /**
     * deleteCard()
     *
     * This method removes the card in the ArrayList specified by the card to be
     * removed.
     *
     * @param cardName
     *      The name of the card to be removed
     *
     * @return
     *      a boolean of weather a card was actually removed from the deck
     */
    public boolean deleteCard(String cardName){
        boolean success = false;
        int pos = this.findCard(cardName);
        if(pos >= 0){
            this.cards.remove(pos);
            this.validateNames();
            success = true;
            if(this.curr == this.cards.size()-1){
                this.curr--;
            }
        }
        return success;
    }




    /**
     * editCard()
     * This method will change the card with the same name as the parameter changed
     * to be the same as the parameter changed.
     *
     * @param changed
     *
     *
     * @return returns weather or not the card was successfully edited;
     */
    public boolean editCard(Flashcard changed){
        boolean success = false;
        if(cards.size() >= 0){
            int idx = cards.indexOf(changed);
            if(idx >= 0){
                cards.get(idx).editCard(changed);
                success = true;
            }

        }
        return success;
    }


    /**
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


    /**
     * getCard()
     *
     * this function returns the Flash object in "deck" with the index equal to "curr"
     * if there are no cards in the deck then then a dummy card will be returned.
     *
     * @param cardName
     *      The name of the card to search for
     *
     * @return
     *      the Flashcard object with the specified name the method will return
     *      a dummy card if it can't find the card requested
     */
    public Flashcard getCard(String cardName){
        Flashcard card;
        if(this.cards.size() == 0){
            card = makeDummy();
        }
        else{
            this.validateNames();
            int pos = extractNumber(cardName);
            if(pos >= 0 && pos < this.cards.size()){
                card = this.cards.get(pos);
            }
            else{
                card = makeDummy();
            }
        }
        return card;
    }


    private int findCard(String cardName){
        int pos = -1;

        this.validateNames();
        int number = this.extractNumber(cardName);
        if(number >= 0 && number < this.cards.size()){
            pos = number;
        }

        return pos;
    }


    private Flashcard makeDummy(){
        return new Flashcard(DUMMYNAME, DUMMYQUESTION, DUMMYANSWER);
    }



    private int extractNumber(String cardName){
        int number = -1;
        int pos = cardName.lastIndexOf('-');
        if(pos >= 0){
            try{
                number = Integer.valueOf(cardName.substring(pos+1));
            }
            catch(NumberFormatException nfe){
                System.err.println("Could not extract card number from " + cardName);
                System.err.println(nfe.getMessage());
            }
        }
        return number;
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
     *
     * @return
     *
     *
     */
    @Override
    public String toString(){
        String info = "Deck: " + this.name + "\n";
        for(int i = 0; i < this.cards.size(); i++){
            info += cards.get(i) + "\n";
        }
        return info;
    }

}