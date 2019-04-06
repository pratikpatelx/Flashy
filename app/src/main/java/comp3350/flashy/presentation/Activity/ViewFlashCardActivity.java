package comp3350.flashy.presentation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.flashy.R;
import comp3350.flashy.presentation.Handler.Interface.deckHandler;

public class ViewFlashCardActivity extends AppCompatActivity {

    ArrayList<String> mcContent;
    ArrayList<String> menu;
    String[] content;
    private TextView textViewFlashBody;
    private TextView textViewFlashTitle;
    private TextView mcBody;
    private TextView mcEntry1;
    private TextView mcEntry2;
    private TextView mcEntry3;
    private TextView mcEntry4;
    private TextView currentType;
    private String name;
    private String body;
    private String title;
    private int index = 0;
    private int deckSize;
    private deckHandler deck = MainActivity.getHandler().getDeckHandler();
    private FloatingActionButton prev;
    private FloatingActionButton next;
    private FloatingActionButton exit;
    private FloatingActionButton delete;
    private FloatingActionButton modify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flash_card);
        this.getSupportActionBar().hide();


        textViewFlashBody = findViewById(R.id.body);
        textViewFlashTitle = findViewById(R.id.title);
        mcBody = findViewById(R.id.mcHead);
        mcEntry1 = findViewById(R.id.mcEntry1);
        mcEntry2 = findViewById(R.id.mcEntry2);
        mcEntry3 = findViewById(R.id.mcEntry3);
        mcEntry4 = findViewById(R.id.mcEntry4);
        currentType = findViewById(R.id.currentType);
        prev = findViewById(R.id.prevButton);
        next = findViewById(R.id.nextButton);
        exit = findViewById(R.id.exitButton);
        delete = findViewById(R.id.delButton);
        modify = findViewById(R.id.modButton);
        index = 0;
        deckSize = deck.getDeckSize();
        name = deck.getDeckName();
        menu = deck.getMenuData();

        Bundle extra = getIntent().getExtras();


        if (extra != null) {
            index = deck.getIndexByFlashCard(extra.getString("FLASHCARD"));
        }

        updateContent();


        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevUpdateIndex();
                updateContent();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextUpdateIndex();
                updateContent();
            }
        });

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isMultipleChoice()) {
                    mcContent = deck.getMCContent(index);
                    openViewFlashCardActivity((name + "-" + index), mcContent);
                } else {
                    content = deck.getContent(index);
                    openViewFlashCardActivity((name + "-" + index), content[0], content[1]);
                }

                deck.deleteCard(index);
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deck.deleteCard(index);
                finish();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void updateContent() {

        hideAllTextViews();
        currentType.setText(menu.get(deck.getFlashcardTypeByIndex(index)));

        if (isMultipleChoice()) {
            mcContent = deck.getMCContent(index);

            mcBody.setText(mcContent.get(0));
            mcEntry1.setText(mcContent.get(1));
            mcEntry2.setText(mcContent.get(2));
            mcEntry3.setText(mcContent.get(3));
            mcEntry4.setText(mcContent.get(4));

            mcBody.setVisibility(View.VISIBLE);
            mcEntry1.setVisibility(View.VISIBLE);
            mcEntry2.setVisibility(View.VISIBLE);
            mcEntry3.setVisibility(View.VISIBLE);
            mcEntry4.setVisibility(View.VISIBLE);

        } else {
            content = deck.getContent(index);

            title = content[0];
            body = content[1];

            textViewFlashBody.setText(body);
            textViewFlashTitle.setText(title);

            textViewFlashBody.setVisibility(View.VISIBLE);
            textViewFlashTitle.setVisibility(View.VISIBLE);
        }
    }

    private void hideAllTextViews() {
        mcBody.setVisibility(View.INVISIBLE);
        mcEntry1.setVisibility(View.INVISIBLE);
        mcEntry2.setVisibility(View.INVISIBLE);
        mcEntry3.setVisibility(View.INVISIBLE);
        mcEntry4.setVisibility(View.INVISIBLE);
        textViewFlashBody.setVisibility(View.INVISIBLE);
        textViewFlashTitle.setVisibility(View.INVISIBLE);
    }

    private boolean isMultipleChoice() {
        return deck.getFlashcardTypeByIndex(index) == 2;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void openViewFlashCardActivity(String cardName, String head, String body) {
        Intent intent = new Intent(this, CreateFlashCardActivity.class);
        intent.putExtra("FLASHCARD", cardName);
        intent.putExtra("NAME", head);
        intent.putExtra("BODY", body);
        intent.putExtra("TYPE", deck.getFlashcardTypeByIndex(index));
        startActivity(intent);
    }

    public void openViewFlashCardActivity(String cardName, ArrayList<String> contents) {
        Intent intent = new Intent(this, CreateFlashCardActivity.class);
        intent.putExtra("MC-CONTENT", contents);
        startActivity(intent);
    }

    //clamping methods for flashcard viewer
    private void nextUpdateIndex() {

        index++;
        if (index < deckSize) {
            return;
        } else {
            index--;
        }

    }

    private void prevUpdateIndex() {
        //Flashcard nextCard;
        index--;
        if (index >= 0) {
            return;
        } else {
            index++;
        }

    }


}
