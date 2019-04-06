package comp3350.flashy.presentation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.flashy.R;
import comp3350.flashy.presentation.Handler.Interface.deckHandler;

public class ShowAllDecksActivity extends AppCompatActivity {
    ArrayAdapter<String> fcArrayAdapter;
    ArrayList<String> items;
    int selectedPostion = -1;
    private deckHandler handler = MainActivity.getHandler().getDeckHandler();
    private TextView deck;
    private TextView username;
    private FloatingActionButton openDeck;
    private FloatingActionButton delDeck;
    private FloatingActionButton Back;
    private ListView listView;
    private Button quiz;
    private Button newDeck;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_decks);
        this.getSupportActionBar().hide();

        deck = findViewById(R.id.selectedDeck);
        username = findViewById(R.id.userDeck);
        newDeck = findViewById(R.id.createDeck);
        listView = findViewById(R.id.showAllDeckList);
        openDeck = findViewById(R.id.Open);
        delDeck = findViewById(R.id.deleteDeck);
        Back = findViewById(R.id.Back);
        quiz = findViewById(R.id.startQuiz);

        Bundle info = getIntent().getExtras();
        handler.setUsername(info.getString("USERNAME"));

        items = handler.getNames();
        selectedPostion = -1;

        fcArrayAdapter = new ArrayAdapter<String>(this, R.layout.deck_list_item, R.id.deck_list_item, items) {
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);
                final TextView deckItem = view.findViewById(R.id.deck_list_item);

                deckItem.setText(items.get(position));


                return view;

            }
        };

        username.setText(handler.getUsername());

        listView.setAdapter(fcArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listView.setItemChecked(position, true);
                selectedPostion = position;
                fcArrayAdapter.notifyDataSetChanged();
                deck.setText(items.get(position));
                handler.setCurrDeck(items.get(position));
            }


        });

        openDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectedPostion != -1)
                    openFlashCardListActivity();

            }
        });

        delDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectedPostion != -1) {
                    listView.setItemChecked(selectedPostion, false);
                    handler.deleteDeck(items.get(selectedPostion));
                    items.remove(selectedPostion);
                    fcArrayAdapter.notifyDataSetChanged();
                    selectedPostion = -1;
                    deck.setText("");
                }
            }
        });

        newDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateNewDeckActivity();
                Log.d("Flashy", "new deck button clicked");
            }
        });

        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPostion != -1)
                    openQuizActivity();
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        items.clear();
        items.addAll(handler.getNames());
        fcArrayAdapter.notifyDataSetChanged();

    }

    public void openFlashCardListActivity() {
        Intent intent = new Intent(this, FlashCardListActivity.class);
        startActivity(intent);
    }

    public void openQuizActivity() {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("NAME",handler.getUsername());
        intent.putExtra("DECK",handler.getDeckName());
        startActivity(intent);
    }

    public void openCreateNewDeckActivity() {
        Intent intent = new Intent(this, NewDeckActivity.class);
        startActivity(intent);
    }

}
