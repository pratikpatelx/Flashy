package comp3350.flashy.presentation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.flashy.R;
import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.presentation.Handler.Interface.deckHandler;

public class FlashCardListActivity extends AppCompatActivity {
    private deckHandler deck = MainActivity.getHandler().getDeckHandler();
    private ArrayAdapter<Flashcard> fcArrayAdapter;
    private ArrayList<Flashcard> items;

    private Deck currDeck = deck.getCurrDeck();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card_list);

        TextView title = findViewById(R.id.Title);
        FloatingActionButton exit = findViewById(R.id.exitFlashList);
        FloatingActionButton add = findViewById(R.id.addCard);
        FloatingActionButton view = findViewById(R.id.viewCards);

        title.setText(currDeck.getName());

        items = deck.getAllCards();

        fcArrayAdapter = new ArrayAdapter<Flashcard>(this, R.layout.flashcard_list_item, R.id.flashListItem, items) {
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView flashCard = view.findViewById(R.id.flashListItem);


                flashCard.setText(items.get(position).getQuestion());


                return view;
            }
        };

        ListView listView = findViewById(R.id.flashcardList);
        listView.setAdapter(fcArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openViewFlashCardActivity(items.get(position).getCardName());
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreateFlashCardActivity();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openViewFlashCardActivity();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateData();
    }

    private void updateData() {
        items.clear();
        items.addAll(deck.getAllCards());
        fcArrayAdapter.notifyDataSetChanged();
    }

    public void openViewFlashCardActivity(String cardName) {
        Intent intent = new Intent(this, ViewFlashCardActivity.class);
        intent.putExtra("FLASHCARD", cardName);

        startActivity(intent);
    }

    public void openViewFlashCardActivity() {
        Intent intent = new Intent(this, ViewFlashCardActivity.class);
        startActivity(intent);
    }


    public void openCreateFlashCardActivity() {
        Intent intent = new Intent(this, CreateFlashCardActivity.class);
        startActivity(intent);
    }


}
