package comp3350.flashy.presentation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import comp3350.flashy.R;

public class CreateDeckActivity extends AppCompatActivity {
    private Button createFlashCard;
    private Button viewFlashCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_deck);

        createFlashCard = (Button) findViewById(R.id.createFlashCard);
        viewFlashCard = (Button) findViewById(R.id.viewFlashCard);


        createFlashCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateFlashCardActivity();
            }
        });

        viewFlashCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openViewFlashCardActivity();
            }
        });


    }

    public void openCreateFlashCardActivity() {
        Intent intent = new Intent(this, CreateFlashCardActivity.class);
        //Intent intent = new Intent(this, viewDeck.class);
        startActivity(intent);
    }

    public void openViewFlashCardActivity() {
        Intent intent = new Intent(this, ViewFlashCardActivity.class);
        //Intent intent = new Intent(this, viewDeck.class);
        startActivity(intent);
    }
}

