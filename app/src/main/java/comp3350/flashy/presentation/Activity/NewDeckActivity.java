package comp3350.flashy.presentation.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import comp3350.flashy.R;
import comp3350.flashy.presentation.Handler.Interface.deckHandler;

public class NewDeckActivity extends Activity {
    private Button createDeck;
    private Button cancelDeck;
    private EditText input;
    private TextView title;
    private deckHandler deck = MainActivity.getHandler().getDeckHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_creation);

        cancelDeck = findViewById(R.id.Cancel_Button);
        createDeck = findViewById(R.id.Enter_Button);
        input = findViewById(R.id.deckTitle);
        title = findViewById(R.id.title);


        cancelDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Log.d("Flashy", "cancel deck button clicked");
            }
        });

        createDeck.setOnClickListener(new View.OnClickListener() {
            CharSequence returnText = "";
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_LONG;

            @Override
            public void onClick(View v) {
                if (input.getText().toString() == null) {
                    returnText = "Please Enter Deck name";
                    Toast toast = Toast.makeText(context, returnText, duration);
                    toast.show();
                }
                openCreateDeckActivity();
                finish();
            }
        });

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                title.setText(input.getText());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //do nothing
            }
        });
    }

    public void openCreateDeckActivity() {
        Intent intent = new Intent(this, FlashCardListActivity.class);
        deck.setCurrDeck(input.getText().toString());
        startActivity(intent);
    }

}
