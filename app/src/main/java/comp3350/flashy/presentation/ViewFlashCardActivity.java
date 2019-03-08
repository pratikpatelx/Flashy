package comp3350.flashy.presentation;

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

    private String body;
    private String title;
    private int index=1;
    private int deckSize;

    private uiHandler uiManager = MainActivity.getHandler();
    private FloatingActionButton prev;
    private FloatingActionButton next;
    private Button exit;

    private Button delete;
    private Button modify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flash_card);
        this.getSupportActionBar().hide();


        textViewFlashBody = (TextView) findViewById(R.id.body);
        textViewFlashTitle = (TextView) findViewById(R.id.title);
        prev = (FloatingActionButton) findViewById(R.id.prevButton);
        next = (FloatingActionButton) findViewById(R.id.nextButton);
        exit = (Button) findViewById(R.id.exitButton);
        delete = (Button) findViewById(R.id.delButton);
        modify = (Button) findViewById(R.id.modButton);
        deckSize = uiManager.getDeckSize();

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
                //uiManager.editContent(stuff to change things);

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uiManager.deleteCard(title);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
        if (index > 0) {
            return;
        } else {
            index++;
        }

    }
}
