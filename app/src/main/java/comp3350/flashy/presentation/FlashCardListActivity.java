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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.flashy.DomainLogic.Flashcard;
import comp3350.flashy.R;
import comp3350.flashy.persistence.DatabaseManager;

public class FlashCardListActivity extends AppCompatActivity {
    uiHandler uiManager = MainActivity.getHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card_list);

        DatabaseManager db = uiManager.getDb();

        final ArrayList<Flashcard> items = db.getDeck("THE_ORACLE_DECK");

        final ArrayAdapter<Flashcard> scArrayAdapter = new ArrayAdapter<Flashcard>(this, android.R.layout.simple_list_item_2, android.R.id.text1, items)
        {
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView flashCard = (TextView) view.findViewById(android.R.id.text1);

                flashCard.setTextSize(35);
                flashCard.setText(items.get(position).getQuestion());


                flashCard.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        uiManager.setContentByFlashCard(items.get(position).getCardName());
                        openViewFlashCardActivity();
                    }
                });


                return view;
            }
        };

        ListView listView = (ListView)findViewById(R.id.flashcardList);
        listView.setAdapter(scArrayAdapter);

    }

    public void openViewFlashCardActivity() {
        Intent intent = new Intent(this, ViewFlashCardActivity.class);
        startActivity(intent);
    }

}
