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
    private Button editDeck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_menu);

        newDeck = (Button) findViewById(R.id.newDeck);
        newDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateNewDeckActivity();
                Log.d("Flashy", "new deck button clicked");
            }
        });

        logOut = (Button) findViewById(R.id.logOut);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeActivity();
                Log.d("Flashy", "log out button clicked");
            }
        });

        viewDeck = (Button) findViewById(R.id.deckList);
        viewDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShowAllDecksActivity();
                //Log.d("Flashy", "log out button clicked");
            }
        });

//        editDeck = (Button) findViewById(R.id.viewDecks);
//        editDeck.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openViewFlashcardActivity();
//                //Log.d("Flashy", "log out button clicked");
//            }
//        });
    }

    public void openViewFlashcardActivity() {
        Intent intent = new Intent(this, ViewFlashCardActivity.class);
        startActivity(intent);
    }

    public void openShowAllDecksActivity() {
        Intent intent = new Intent(this, ShowAllDecksActivity.class);
        startActivity(intent);
    }

    public void openCreateNewDeckActivity(){
        Intent intent = new Intent(this, NewDeckActivity.class);
        startActivity(intent);
    }

    public void openHomeActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
