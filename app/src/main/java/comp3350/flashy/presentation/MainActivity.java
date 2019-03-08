package comp3350.flashy.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import comp3350.flashy.R;




public class MainActivity extends AppCompatActivity {
    private Button giveAccess;
    public static uiHandler ui = new uiHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        giveAccess = (Button)findViewById(R.id.login_button);
        giveAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDeckMenuActivity();
                Log.d("Flashy", "Log in Button CLicked");
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


}

