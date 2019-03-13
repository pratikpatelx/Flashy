package comp3350.flashy.presentation;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

import comp3350.flashy.R;
import comp3350.flashy.presentation.CreateDeckActivity;
import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;

public class ShowAllDecksActivity extends AppCompatActivity {
    ArrayAdapter<String> fcArrayAdapter;
    uiHandler uiManager = MainActivity.getHandler();
    ArrayList<String> items;
    int selectedPostion;
    private TextView deck;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_decks);
        this.getSupportActionBar().hide();

        deck = (TextView) findViewById(R.id.selectedDeck);
        items = uiManager.getNames();


        fcArrayAdapter = new ArrayAdapter<String>(this, R.layout.deck_list_item, R.id.deck_list_item, items)
        {
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView deck = (TextView) view.findViewById(R.id.deck_list_item);



                deck.setText(items.get(position));


//                deck.setOnClickListener(new View.OnClickListener(){
//                    @Override
//                    public void onClick(View v) {
//                        openCreateDeckActivity(items.get(position));
//
//
//                    }
//                });


                return view;
            }
        };

        final ListView listView = (ListView)findViewById(R.id.showAllDeckList);
        listView.setAdapter(fcArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    listView.setItemChecked(position,true);
                    selectedPostion = position;

                    deck.setText(items.get(position));

                }
        });
    }

    public void openCreateDeckActivity(String deckName) {
        Intent intent = new Intent(this, CreateDeckActivity.class);
        intent.putExtra("DECK",deckName);
        startActivity(intent);
    }
    
}
