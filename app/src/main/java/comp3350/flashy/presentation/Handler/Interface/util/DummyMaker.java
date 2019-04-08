package comp3350.flashy.presentation.Handler.Interface.util;

import comp3350.flashy.domain.Flashcard;

public class DummyMaker {

    private static final String DUMMYID = "DUMMY-0";
    private static final String DUMMYQUESTION = "DOES NOT EXIST";
    private static final String DUMMYANSWER = "There are either no cards in the "
            + "deck or you asked for a specific card that does not exist";

    public static Flashcard makeDummy() {
        return new Flashcard(DUMMYID, DUMMYQUESTION, DUMMYANSWER, 0);
    }

}
