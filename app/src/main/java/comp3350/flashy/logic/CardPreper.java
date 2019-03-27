package comp3350.flashy.logic;

import java.util.Random;

import comp3350.flashy.domain.*;

public class CardPreper {

    private static final String BLANK = "_____";
    private static final int THRESHOLD = 5;


    /**
     * prepareFitBcard()
     *
     * This method takes a Flashcard object as a parameter, if the Flashcard is not a FitBcard then
     * this method does nothing, otherwise the card is prepared for use in a quiz
     *
     * @param card the card to be prepared
     */
    public static void preareFitBcard(Flashcard card){
        if(card.isFillInTheBlanksFlashcard()) {
            FillInTheBlanksFlashcard curr = (FillInTheBlanksFlashcard) card;
            String[] words = card.getAnswer().split("\\s+|,.!?;:\"");
            int nWords = words.length;
            int count = 0;
            int longest = 0;
            System.out.println("split gets this: "+words[0]);

            boolean met = false;//has the threshold been met

            for (int i = 0; i < nWords; i++) {
                if (met) {
                    if (words[i].length() >= THRESHOLD) {
                        count++;
                    }
                } else {
                    if (words[i].length() >= longest) {
                        if (words[i].length() > longest) {
                            count = 1;
                            longest = words[i].length();
                            //has the threshold been met?
                            met = words[i].length() >= THRESHOLD;
                        } else {
                            count++;
                        }

                    }
                }

            }

            String[] cands = new String[count];
            count = 0;
            if (met) {
                longest = THRESHOLD;
            }
            for (int i = 0; i < nWords; i++) {
                if (words[i].length() >= THRESHOLD) {
                    cands[count] = words[i];
                    count++;
                }
            }

            Random rand = new Random();
            int selected = rand.nextInt(count);

            curr.setFirstPart(cands[selected]);

            String revision = curr.getAnswer().replace(curr.getFirstPart(), BLANK);


            System.out.println("revision:"+revision+" firstpart:"+ curr.getFirstPart());
            curr.setAnswer(revision);
        }
    }



//    public static void prepareMCcard(Flashcard card){
//        MCcard mc;
//        if(card.isMultipleChoiceFlashcard()){
//
//        }
//    }

}
