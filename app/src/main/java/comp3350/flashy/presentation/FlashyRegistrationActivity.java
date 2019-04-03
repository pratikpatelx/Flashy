package comp3350.flashy.presentation;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import comp3350.flashy.R;

public class FlashyRegistrationActivity extends AppCompatActivity {

    private Button cancel;
    private Button registration;
    private EditText Username;
    private EditText password;
    private uiHandler uiManager = MainActivity.getHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashy_registration);

        cancel = findViewById(R.id.cancelReg);
        registration = findViewById(R.id.regButton);
        Username = findViewById(R.id.Username);
        password = findViewById(R.id.userPassword);

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CharSequence returnText;
                Context context = getApplicationContext();
                returnText = "Please Enter Every Field in Username and Password";
                int duration = Toast.LENGTH_LONG;

                if (uiManager.registerUser(Username.getText().toString(), password.getText().toString())) {
                    returnText = "Account Created Sucessfully";
                    Toast toast = Toast.makeText(context, returnText, duration);
                    toast.show();
                    finish();
                } else {
                    Toast toast = Toast.makeText(context, returnText, duration);
                    toast.show();
                }

                Toast toast = Toast.makeText(context, returnText, duration);
                toast.show();


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

}
