package comp3350.flashy.presentation.Handler.Interface;

import java.util.ArrayList;

import comp3350.flashy.logic.UserManager;

//purpose: handle all profile functions in the ui
public class profileHandler {

    private UserManager userM;

    public profileHandler(){ userM = new UserManager();
    }

    public boolean registerUser(String username, String password) {
        return userM.addUserToDatabase(username, password);
    }

    public boolean Verified(String username, String password) {
        return userM.verifyUserPassword(username, password);
    }

    public void deleteUser(String user) {
        userM.removeUserFromDatabase(user);
    }

    public ArrayList<String> getAllProfileNames() {
        return new ArrayList<String>(userM.getAllProfiles());
    }
}
