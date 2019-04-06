package comp3350.flashy.presentation.Handler;

import comp3350.flashy.presentation.Handler.Interface.deckHandler;
import comp3350.flashy.presentation.Handler.Interface.profileHandler;
import comp3350.flashy.presentation.Handler.Interface.quizHandler;

//purpose: manage the interface handlers.
public class uiHandler {

    private deckHandler deck;
    private profileHandler profile;
    private quizHandler quiz;

    public uiHandler() {

        quiz = new quizHandler();
        deck = new deckHandler();
        profile = new profileHandler();
    }

    public profileHandler getProfileHandler(){
        return profile;
    }

    public deckHandler getDeckHandler(){
        return deck;
    }

    public quizHandler getQuizHandler(){
        return quiz;
    }
}
