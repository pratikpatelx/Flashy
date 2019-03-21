package comp3350.flashy.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import comp3350.flashy.R;

public class ViewFlashCardActivity extends AppCompatActivity {

    private TextView textViewFlashBody;
    private TextView textViewFlashTitle;

    private String name;
    private String body;
    private String title;
    private int index=0;
    private int deckSize;

    private uiHandler uiManager = MainActivity.getHandler();
    private FloatingActionButton prev;
    private FloatingActionButton next;
    private FloatingActionButton exit;

    private FloatingActionButton  delete;
    private FloatingActionButton modify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flash_card);
        this.getSupportActionBar().hide();


        textViewFlashBody = (TextView) findViewById(R.id.body);
        textViewFlashTitle = (TextView) findViewById(R.id.title);
        prev = (FloatingActionButton) findViewById(R.id.prevButton);
        next = (FloatingActionButton) findViewById(R.id.nextButton);
        exit = (FloatingActionButton) findViewById(R.id.exitButton);
        delete = (FloatingActionButton) findViewById(R.id.delButton);
        modify = (FloatingActionButton) findViewById(R.id.modButton);
        index = 0;
        deckSize = uiManager.getDeckSize();
        name = uiManager.getDeckName();

        Bundle extra = getIntent().getExtras();
        String[] content;

        if(extra!=null){
            index = uiManager.getIndexByFlashCard(extra.getString("FLASHCARD"));
        }

        //throws array out of bounds exception
       content = uiManager.getContent(index);

        title = content[0];
        body = content[1];

        textViewFlashBody.setText(body);
        textViewFlashTitle.setText(title);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevUpdateIndex();
                String[] content = uiManager.getContent(index);

                title = content[0];
                body = content[1];

                textViewFlashBody.setText(body);
                textViewFlashTitle.setText(title);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextUpdateIndex();
                String[] content = uiManager.getContent(index);

                title = content[0];
                body = content[1];

                textViewFlashBody.setText(body);
                textViewFlashTitle.setText(title);
            }
        });

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] contents = uiManager.getContent(index);


                openViewFlashCardActivity((name + "-" + index),contents[0],contents[1]);
                uiManager.deleteCard(index);
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uiManager.deleteCard(index);
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

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void openViewFlashCardActivity(String cardName, String head, String body) {
        Intent intent = new Intent(this, CreateFlashCardActivity.class);
        intent.putExtra("FLASHCARD",cardName);
        intent.putExtra("NAME", head);
        intent.putExtra("BODY", body);
        startActivity(intent);
    }
    //clamping methods for flashcard viewer
    private void nextUpdateIndex() {
        //Flashcard nextCard;
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
