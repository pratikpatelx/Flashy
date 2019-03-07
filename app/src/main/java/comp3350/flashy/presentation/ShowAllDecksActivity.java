package comp3350.flashy.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

import comp3350.flashy.R;
import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;

public class ShowAllDecksActivity extends AppCompatActivity {
    ArrayAdapter<Flashcard> fcArrayAdapter;
    private uiHandler uiManager;
    private Deck currDeck = uiManager.getCurrDeck();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_decks);

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
                       // openViewFlashCardActivity(items.get(position).getCardName());
                    }
                });


                return view;
            }
        };
    }
    
    
}
