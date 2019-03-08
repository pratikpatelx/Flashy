package comp3350.flashy.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.R;
import comp3350.flashy.persistence.DatabaseManager;

public class FlashCardListActivity extends AppCompatActivity {
    uiHandler uiManager = MainActivity.getHandler();
    ArrayAdapter<Flashcard> fcArrayAdapter;

    private Deck currDeck = uiManager.getCurrDeck();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card_list);

        TextView title = (TextView) findViewById(R.id.Title);
        Button exit = (Button) findViewById(R.id.exitFlashList);
        Button add = (Button) findViewById(R.id.addCard);
        Button delete = (Button) findViewById(R.id.deleteDeck);



        title.setText(currDeck.getName());

        final ArrayList<Flashcard> items = currDeck.getCards();

        fcArrayAdapter = new ArrayAdapter<Flashcard>(this, R.layout.flashcard_list_item, R.id.flashListItem, items)
        {
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView flashCard = (TextView) view.findViewById(R.id.flashListItem);

                flashCard.setTextSize(35);

                flashCard.setText(items.get(position).getQuestion());


                flashCard.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        openViewFlashCardActivity(items.get(position).getCardName());
                    }
                });


                return view;
            }
        };

        ListView listView = (ListView)findViewById(R.id.flashcardList);
        listView.setAdapter(fcArrayAdapter);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uiManager.deleteDeck();
                finish();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreateFlashCardActivity();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        fcArrayAdapter.notifyDataSetChanged();
    }

    public void openViewFlashCardActivity(String cardName) {
        Intent intent = new Intent(this, ViewFlashCardActivity.class);
        intent.putExtra("FLASHCARD",cardName);

        startActivity(intent);
    }



    public void openCreateFlashCardActivity() {
        Intent intent = new Intent(this, CreateFlashCardActivity.class);
        startActivity(intent);
    }


}
