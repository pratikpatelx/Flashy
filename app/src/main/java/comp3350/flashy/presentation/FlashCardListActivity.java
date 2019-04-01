package comp3350.flashy.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.R;

public class FlashCardListActivity extends AppCompatActivity {
    uiHandler uiManager = MainActivity.getHandler();
    ArrayAdapter<Flashcard> fcArrayAdapter;
    ArrayList<Flashcard> items;

    private Deck currDeck = uiManager.getCurrDeck();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card_list);

        TextView title = (TextView) findViewById(R.id.Title);
        FloatingActionButton exit = (FloatingActionButton)findViewById(R.id.exitFlashList);
        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.addCard);
        FloatingActionButton view = (FloatingActionButton) findViewById(R.id.viewCards);

        title.setText(currDeck.getName());

        items = uiManager.getAllCards();

        fcArrayAdapter = new ArrayAdapter<Flashcard>(this, R.layout.flashcard_list_item, R.id.flashListItem, items)
        {
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView flashCard = (TextView) view.findViewById(R.id.flashListItem);



                flashCard.setText(items.get(position).getQuestion());



                return view;
            }
        };

        ListView listView = (ListView)findViewById(R.id.flashcardList);
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

    private void updateData(){
        items.clear();
        items.addAll(uiManager.getAllCards());
        fcArrayAdapter.notifyDataSetChanged();
    }

    public void openViewFlashCardActivity(String cardName) {
        Intent intent = new Intent(this, ViewFlashCardActivity.class);
        intent.putExtra("FLASHCARD",cardName);

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
