package comp3350.flashy.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

import comp3350.flashy.R;
import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;

public class ShowAllDecksActivity extends AppCompatActivity {
    ArrayAdapter<String> fcArrayAdapter;
    uiHandler uiManager = MainActivity.getHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_decks);

        final ArrayList<String> items = uiManager.getNames();

        fcArrayAdapter = new ArrayAdapter<String>(this, R.layout.deck_list_item, R.id.deck_list_item, items)
        {
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView deck = (TextView) view.findViewById(R.id.deck_list_item);

                deck.setTextSize(35);

                deck.setText(items.get(position));


                deck.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                       // openViewFlashCardActivity(items.get(position).getCardName());
                    }
                });


                return view;
            }
        };

        ListView listView = (ListView)findViewById(R.id.showAllDeckList);
        listView.setAdapter(fcArrayAdapter);
    }
    
    
}
