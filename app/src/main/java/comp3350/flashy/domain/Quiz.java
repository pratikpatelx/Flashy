package comp3350.flashy.domain;

import java.util.ArrayList;
import java.util.Random;

public class Quiz {


    private int curr;

    private ArrayList<Flashcard> cards;

    public Quiz(Deck deck){
        this.cards = new ArrayList<Flashcard>();
        this.cards.addAll(deck.getCards());

    }

    /********** Accessors ***********/

    public Flashcard getCard(){
        return this.cards.get(0);
    }

    public Flashcard takeCard(){
        return this.cards.remove(0);
    }

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
