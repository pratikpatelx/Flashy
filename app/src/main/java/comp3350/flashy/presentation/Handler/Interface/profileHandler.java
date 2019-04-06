package comp3350.flashy.presentation.Handler.Interface;

import java.util.ArrayList;

import comp3350.flashy.logic.LogicManager;

//purpose: handle all profile functions in the ui
public class profileHandler {

    private LogicManager logic;

    public profileHandler(LogicManager lgc){
        logic = lgc;
    }

    public boolean registerUser(String username, String password) {
        return logic.addUserToDatabase(username, password);
    }

    public boolean Verified(String username, String password) {
        return logic.verifyUserPassword(username, password);
    }

    public void deleteUser(String user) {
        logic.removeUserFromDatabase(user);
    }

    public ArrayList<String> getAllProfileNames() {
        return logic.getAllProfiles();
    }
}
