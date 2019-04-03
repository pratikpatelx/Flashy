package comp3350.flashy.domain;

public interface FlashcardInterface {
    /**
     * Maintains logical grouping and needed for future iterations
     */

    /**
     * @return whether the answer is correct or wrong
     */
    boolean mark(String response);


    String getCardType();
}
