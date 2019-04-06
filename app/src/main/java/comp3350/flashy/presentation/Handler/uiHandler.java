package comp3350.flashy.presentation.Handler;

import java.util.ArrayList;

import comp3350.flashy.logic.QuizManager;
import comp3350.flashy.presentation.Handler.InterfaceHandlers.deckHandler;
import comp3350.flashy.presentation.Handler.InterfaceHandlers.profileHandler;

//purpose: manage the interface handlers.
public class uiHandler {

    private deckHandler deck;
    private profileHandler profile;

    public uiHandler() {
        deck = new deckHandler();
        profile = new profileHandler();
    }

    public profileHandler getProfileHandler(){
        return profile;
    }

    public deckHandler getDeckHandler(){
        return deck;
    }

    public QuizManager startQuiz() {
        return deck.startQuiz();
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
