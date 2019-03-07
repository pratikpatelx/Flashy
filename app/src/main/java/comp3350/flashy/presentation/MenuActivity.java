package comp3350.flashy.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import comp3350.flashy.R;

public class MenuActivity extends AppCompatActivity {

    private Button NewDeck;
    private Button ViewDecks;
    private Button Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        NewDeck = (Button)findViewById(R.id.btnMenuNewDeck);
        ViewDecks = (Button)findViewById(R.id.btnMenuViewDecks);
        Logout = (Button)findViewById(R.id.btnMenuLogout);


        NewDeck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, NewDeckActivity.class);
                startActivity(intent);
            }
        });


        ViewDecks.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, ViewDecksActivity.class);
                startActivity(intent);
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
