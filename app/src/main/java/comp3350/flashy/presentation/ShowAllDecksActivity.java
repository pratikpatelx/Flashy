package comp3350.flashy.presentation;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

import comp3350.flashy.R;

public class ShowAllDecksActivity extends AppCompatActivity {
    ArrayAdapter<String> fcArrayAdapter;
    private uiHandler uiManager = MainActivity.getHandler();
    ArrayList<String> items;
    int selectedPostion = -1;
    private TextView deck;
    private FloatingActionButton openDeck;
    private FloatingActionButton delDeck;
    private FloatingActionButton Back;
    private ListView listView;
    private Button quiz;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_decks);
        this.getSupportActionBar().hide();



        deck = (TextView) findViewById(R.id.selectedDeck);
        listView = (ListView)findViewById(R.id.showAllDeckList);
        openDeck = (FloatingActionButton) findViewById(R.id.Open);
        delDeck = (FloatingActionButton) findViewById(R.id.deleteDeck);
        Back = (FloatingActionButton) findViewById(R.id.Back);
        quiz = findViewById(R.id.startQuiz);

        items = uiManager.getNames();
        selectedPostion = -1;

        fcArrayAdapter = new ArrayAdapter<String>(this, R.layout.deck_list_item, R.id.deck_list_item, items)
        {
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);
                TextView deck = (TextView) view.findViewById(R.id.deck_list_item);
                deck.setText(items.get(position));

                return view;

            }
        };


        listView.setAdapter(fcArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    listView.setItemChecked(position,true);
                    selectedPostion = position;
                    fcArrayAdapter.notifyDataSetChanged();
                    deck.setText(items.get(position));
                    uiManager.setCurrDeck(items.get(position));
                }


        });

        openDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selectedPostion != -1)
                    openFlashCardListActivity();

            }
        });

        delDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selectedPostion != -1){
                    listView.setItemChecked(selectedPostion,false);
                    uiManager.deleteDeck(items.get(selectedPostion));
                    items.remove(selectedPostion);
                    fcArrayAdapter.notifyDataSetChanged();
                    selectedPostion = -1;
                    deck.setText("");
                }
            }
        });

        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedPostion != -1)
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

    public void openFlashCardListActivity() {
        Intent intent = new Intent(this, FlashCardListActivity.class);
        startActivity(intent);
    }

    public void openQuizActivity() {
        Intent intent = new Intent(this, Quiz.class);
        uiManager.startQuiz();
        startActivity(intent);
    }

    
}
