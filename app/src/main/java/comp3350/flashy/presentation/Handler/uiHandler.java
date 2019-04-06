package comp3350.flashy.presentation.Handler;

import java.util.ArrayList;

import comp3350.flashy.application.Services;
import comp3350.flashy.logic.LogicManager;
import comp3350.flashy.logic.QuizManager;
import comp3350.flashy.presentation.Handler.InterfaceHandlers.deckHandler;
import comp3350.flashy.presentation.Handler.InterfaceHandlers.profileHandler;

//purpose: manage the interface handlers.
public class uiHandler {

    private LogicManager logic;

    private deckHandler deck;
    private profileHandler profile;


    public uiHandler() {
        logic = new LogicManager(Services.getDeckPersistence(), Services.getUserPersistence());
        deck = new deckHandler(logic);
        profile = new profileHandler(logic);
    }

    public profileHandler getProfileHandler(){
        return profile;
    }

    public deckHandler getDeckHandler(){
        return deck;
    }

    public QuizManager startQuiz() {
        return logic.startQuiz(deck.getUsername(), deck.getDeckName());
    }

    public void setUsername(String name) {
        deck.setUsername(name);
    }

    public static ArrayList<String> getMenuData() {
        ArrayList<String> result = new ArrayList<String>();

        result.add("Standard");
        result.add("Fill in the blank");
        result.add("Multiple choice");

        return result;
    }
}
