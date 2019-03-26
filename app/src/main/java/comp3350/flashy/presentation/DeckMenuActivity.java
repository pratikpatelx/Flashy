package comp3350.flashy.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import comp3350.flashy.R;

public class DeckMenuActivity extends AppCompatActivity {
    private Button newDeck;
    private Button viewDeck;
    private Button logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_menu);
        this.getSupportActionBar().hide();


        newDeck = (Button) findViewById(R.id.newDeck);
        viewDeck = (Button) findViewById(R.id.deckList);
        logOut = (Button) findViewById(R.id.logOut);

        newDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateNewDeckActivity();
                Log.d("Flashy", "new deck button clicked");
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        viewDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShowAllDecksActivity();
            }
        });

    }


    public void openShowAllDecksActivity() {
        Intent intent = new Intent(this, ShowAllDecksActivity.class);
        startActivity(intent);
    }

    public void openCreateNewDeckActivity(){
        Intent intent = new Intent(this, NewDeckActivity.class);
        startActivity(intent);
    }

}
