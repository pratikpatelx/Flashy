package comp3350.flashy.presentation.Handler;

import comp3350.flashy.application.Services;
import comp3350.flashy.logic.LogicManager;
import comp3350.flashy.presentation.Handler.Interface.deckHandler;
import comp3350.flashy.presentation.Handler.Interface.profileHandler;
import comp3350.flashy.presentation.Handler.Interface.quizHandler;

//purpose: manage the interface handlers.
public class uiHandler {

    private LogicManager logic;

    private deckHandler deck;
    private profileHandler profile;
    private quizHandler quiz;

    public uiHandler() {
        logic = new LogicManager(Services.getDeckPersistence(), Services.getUserPersistence());
        deck = new deckHandler(logic);
        profile = new profileHandler(logic);
        quiz = new quizHandler(logic);
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
