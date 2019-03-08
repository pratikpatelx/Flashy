package comp3350.flashy.presentation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import comp3350.flashy.R;

public class CreateDeckActivity extends AppCompatActivity {
    private Button createFlashCard;
    private Button viewFlashCard;
    private Button listFlashCards;
    private uiHandler uiManager = MainActivity.getHandler();
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_deck);

        createFlashCard = (Button) findViewById(R.id.createFlashCard);
        viewFlashCard = (Button) findViewById(R.id.viewFlashCard);
        listFlashCards = (Button) findViewById(R.id.listFlashCards);
        backButton = (Button)findViewById(R.id.backButton);

        Bundle extra = super.getIntent().getExtras();

        if(extra != null){
            uiManager.setCurrDeck(extra.getString("DECK"));
            System.out.println(extra.getString("DECK"));
        }


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

        listFlashCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFlashCardListActivity();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDeckMenuActivity();
            }
        });



        ArrayList<String> names = uiManager.getNames();

        for(String name: names){
            System.out.println(name);
        }

    }



    public void openDeckMenuActivity() {
        Intent intent = new Intent(this, DeckMenuActivity.class);
        startActivity(intent);
    }

    public void openCreateFlashCardActivity() {
        Intent intent = new Intent(this, CreateFlashCardActivity.class);
        startActivity(intent);
    }

    public void openViewFlashCardActivity() {
        Intent intent = new Intent(CreateDeckActivity.this, ViewFlashCardActivity.class);
        startActivity(intent);
    }

    public void openFlashCardListActivity() {
        Intent intent = new Intent(this, FlashCardListActivity.class);
        startActivity(intent);
    }
}

