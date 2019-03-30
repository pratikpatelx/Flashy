package comp3350.flashy.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import comp3350.flashy.R;
import comp3350.flashy.business.AccessCreateAccounts;
import comp3350.flashy.domain.CreateAccount;
import comp3350.flashy.persistence.hsqldb.PersistenceException;

public class FlashyRegistrationActivity extends AppCompatActivity {

    private Button cancel;

    private EditText Username;
    private EditText password;
    private uiHandler uiManager = MainActivity.getHandler();

    //Pratik's Edit
    private AccessCreateAccounts accessCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashy_registration);
        accessCreateAccount = new AccessCreateAccounts();

        cancel = findViewById(R.id.cancelReg);
        final Button registration = findViewById(R.id.regButton);
        Username = findViewById(R.id.Username);
        password = findViewById(R.id.userPassword);

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccount newAccount = createAccountFromObjectActivity();
                CreateAccount checkAccount = null;
                CharSequence returnText;
                Context context = getApplicationContext();
                returnText = "Please fill up every section";
                int duration = Toast.LENGTH_LONG;

                try {
                    checkAccount = accessCreateAccount.insertAccountInformation(newAccount);
                } catch (PersistenceException e) {
                    returnText = "Error in saving user to database";
                } catch (Exception e) {
                    returnText = e.getMessage();
                }

                if (checkAccount == newAccount) {
                    returnText = "Account Created";

                    Toast toast = Toast.makeText(context, returnText, duration);
                    toast.show();

                    logInToAccount(v);
                } else {
                    Toast toast = Toast.makeText(context, returnText, duration);
                    toast.show();
                }

                Toast toast = Toast.makeText(context, returnText, duration);
                toast.show();
                /*if(uiManager.registerUser(Username.getText().toString(),password.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Registration successful",Toast.LENGTH_LONG).show();
                    finish();
                }else
                    Toast.makeText(getApplicationContext(),"Registration unsuccessful",Toast.LENGTH_LONG).show();*/


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private CreateAccount createAccountFromObjectActivity(){
        String userName, password;
        userName = ((EditText) findViewById(R.id.Username)).getText().toString();
        password = ((EditText) findViewById(R.id.userPassword)).getText().toString();

        return new CreateAccount(userName,password);
    }

    public void logInToAccount(View view){
        Intent logInToAccount = new Intent(this, MainActivity.class);
        startActivity(logInToAccount);
    }

}
