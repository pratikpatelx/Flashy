package comp3350.flashy.domain;

import comp3350.flashy.logic.CardPreper;

import java.util.ArrayList;
import java.util.Random;

public class Quiz {


    private int curr;

    private ArrayList<Flashcard> cards;

    public Quiz(Deck deck){
        this.cards = new ArrayList<Flashcard>();
        this.cards.addAll(deck.getFlashcards());
        this.prepareCards();

    }


    private void prepareCards(){
        for(int i = 0; i < this.cards.size(); i++){
            if(cards.get(i).isFillInTheBlanksFlashcard()){
                CardPreper.preareFitBcard(cards.get(i));
            }
        }
    }


    /********** Accessors ***********/

    public Flashcard getCard(){
        return this.cards.get(0);
    }

    public Flashcard takeCard(){
        return this.cards.remove(0);
    }


    /**
     * placeCard()
     *      This method places the flashcard card into a random position in the deck
     *
     * @param card the card to be added
     */
    public void placeCard(Flashcard card){

        if(!this.cards.isEmpty()){
            Random rand = new Random();
            int pos = rand.nextInt() % this.cards.size();
            this.cards.add(pos, card);
        }
        else{
            this.cards.add(card);
        }


    }






}
