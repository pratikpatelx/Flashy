package comp3350.flashy.domain;

public class CreateAccount {
    private String username;
    private String password;

    public CreateAccount(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
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