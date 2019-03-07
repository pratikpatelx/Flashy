package comp3350.flashy.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import comp3350.flashy.R;

public class EditDeckActivity extends AppCompatActivity {

    private Button NewCard;
    private Button EditCard;
    private Button DeleteCard;
    private TextView DeckName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_deck);

        NewCard = (Button)findViewById(R.id.btnEditDeckNewFlashcard);
        EditCard = (Button)findViewById(R.id.btnEditDeckEditFlashcard);
        DeleteCard = (Button)findViewById(R.id.btnEditDeckDeleteFlashcard);

        NewCard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(EditDeckActivity.this, NewFlashcardActivity.class);
                startActivity(intent);
            }
        });

        EditCard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //add existing flashcard to the input of newflash activity page
                Intent intent = new Intent(EditDeckActivity.this, NewFlashcardActivity.class);
                startActivity(intent);
            }
        });

        DeleteCard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

            }
        });
    }
}
