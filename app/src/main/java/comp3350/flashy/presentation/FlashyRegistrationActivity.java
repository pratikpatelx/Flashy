package comp3350.flashy.presentation;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.hsqldb.rights.User;

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
                if(uiManager.registerUser(Username.getText().toString(),password.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Registration successful",Toast.LENGTH_LONG).show();
                    finish();
                }else
                    Toast.makeText(getApplicationContext(),"Registration unsuccessful",Toast.LENGTH_LONG).show();


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
