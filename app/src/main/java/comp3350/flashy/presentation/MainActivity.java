package comp3350.flashy.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import comp3350.flashy.R;

public class MainActivity extends AppCompatActivity {

    private TextView Info;
    private EditText Username;
    private EditText Password;
    private Button Enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Info = (TextView)findViewById(R.id.tvMainLogin);
        Username = (EditText)findViewById(R.id.etMainUsername);
        Password = (EditText)findViewById(R.id.etMainPassword);
        Enter = (Button)findViewById(R.id.btnMainEnter);

        Enter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
