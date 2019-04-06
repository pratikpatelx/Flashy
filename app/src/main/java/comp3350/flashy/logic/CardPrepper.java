package comp3350.flashy.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import comp3350.flashy.domain.FillInTheBlanksFlashcard;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.domain.MultipleChoiceFlashcard;

public class CardPrepper {

    private static final String BLANK = "_____";
    private static final int THRESHOLD = 5;


    /**
     * prepareFitBcard()
     * <p>
     * This method takes a Flashcard object as a parameter, if the Flashcard is not a FitBcard then
     * this method does nothing, otherwise the card is prepared for use in a quiz
     *
     * @param curr the card to be prepared
     */
    public static void prepareFitBCard(FillInTheBlanksFlashcard curr) {
            String[] words = curr.getAnswer().split("\\s+|,.!?;:\"");
            int nWords = words.length;
            int count = 0;
            int longest = 0;
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
                if (words[i].length() >= longest) {
                    cands[count] = words[i];
                    count++;
                }
            }
            Random rand = new Random();
            int selected = rand.nextInt(count);

            curr.setFirstPart(cands[selected]);

            curr.setAnswer(curr.getAnswer().replace(curr.getFirstPart(), BLANK));

    }


    public static void prepareMultipleChoiceFlashcard(MultipleChoiceFlashcard card) {
            ArrayList<String> choices = new ArrayList<>(card.getAnswers());

            card.setAnswer(choices.get(0));

            ArrayList<String> noobs = new ArrayList<String>();
            int num = choices.size();
            Random rand = new Random();

            String picked;

            for (int i = 0; i < num; i++) {
                picked = choices.get(rand.nextInt(choices.size()));
                choices.remove(picked);
                noobs.add(picked);
            }

            card.setAnswers(noobs);


    }

}
