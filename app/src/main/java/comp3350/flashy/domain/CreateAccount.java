package comp3350.flashy.domain;

public class CreateAccount {
    private final String username;
    private final String password;

    public CreateAccount(String newUsername, String newPassword){
        username = newUsername;
        password = newPassword;
    }


    public String getUsername(){
        return (username);
    }

    public String getPassword(){
        return (password);
    }

    public boolean validateAccountInfo(){
        int userNameLength = username.length();
        int passWordLength = password.length();

        if (userNameLength > 0 && passWordLength > 0){
            return true;
        }
        else{
            return false;
        }
    }
}