package comp3350.flashy.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import comp3350.flashy.R;




public class MainActivity extends AppCompatActivity {
    private Button giveAccess;
    private Button register;
    private EditText username;
    private EditText password;

    public static uiHandler ui = new uiHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        giveAccess = (Button)findViewById(R.id.login_button);
        register = (Button) findViewById(R.id.register);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

      giveAccess.setOnClickListener(new View.OnClickListener() {
           @Override
         public void onClick(View v) {
//                if(ui.Verified(username.getText().toString(),password.getText().toString())) {
//                    ui.setUsername(username.getText().toString());
                  openDeckMenuActivity();
//                }else
//                    Toast.makeText(getApplicationContext(), "Authentication failed", Toast.LENGTH_LONG).show();
//
           }
  });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegistrationActivity();
            }
        });
    }


    public static uiHandler getHandler() {

        return ui;
    }
    public void openDeckMenuActivity(){
        Intent intent = new Intent(this, DeckMenuActivity.class);
        startActivity(intent);
    }
    public void openRegistrationActivity(){
        Intent intent = new Intent(this, FlashyRegistrationActivity.class);
        startActivity(intent);
    }


}

