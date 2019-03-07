package comp3350.flashy.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import comp3350.flashy.R;

public class NewDeckActivity extends AppCompatActivity {

    private Button NewDeck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_deck);

        NewDeck = (Button)findViewById(R.id.btnNewDeckEnter);

        NewDeck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(NewDeckActivity.this, ViewDecksActivity.class);
                startActivity(intent);
            }
        });
    }
}
