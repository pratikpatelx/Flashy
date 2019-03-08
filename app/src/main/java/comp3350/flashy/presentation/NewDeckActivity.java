package comp3350.flashy.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;
import comp3350.flashy.R;

public class NewDeckActivity extends Activity {
//    private AccessDecks mAccessDecks;
//    private List<Deck> mDeckList;
//    private ArrayAdapter<Deck> mDeckArrayAdapter;
//    private int selectedDeckPosition = -1;

    private Button createDeck;
    private Button cancelDeck;
    private EditText input;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_creation);

        cancelDeck = (Button) findViewById(R.id.Cancel_Button);
        createDeck = (Button) findViewById(R.id.Enter_Button);
        input =  (EditText) findViewById(R.id.deckTitle);


        cancelDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Log.d("Flashy", "cancel deck button clicked");
            }
        });

        createDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateDeckActivity();


            }
        });
    }

    public void openCreateDeckActivity(){
        Intent intent = new Intent(this, CreateDeckActivity.class);
        intent.putExtra("DECK",input.getText().toString());
        startActivity(intent);
    }

}
