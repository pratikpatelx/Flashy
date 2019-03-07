package comp3350.flashy.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import comp3350.flashy.R;


public class ViewDecksActivity extends AppCompatActivity {

    private Button NewDeck;
    private Button EditDeck;
    private Button DeleteDeck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_decks);

        NewDeck = (Button)findViewById(R.id.btnViewDecksNewDeck);
        EditDeck = (Button)findViewById(R.id.btnViewDecksEditDeck);
        DeleteDeck = (Button)findViewById(R.id.btnViewDecksDeleteDeck);

        NewDeck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ViewDecksActivity.this, NewDeckActivity.class);
                startActivity(intent);
            }
        });

        EditDeck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ViewDecksActivity.this, EditDeckActivity.class);
                startActivity(intent);
            }
        });

        DeleteDeck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

            }
        });
    }
}
