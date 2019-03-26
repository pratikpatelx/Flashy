package comp3350.flashy.presentation;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import comp3350.flashy.R;

public class Quiz extends AppCompatActivity {

    private uiHandler uiManager = MainActivity.getHandler();

    private TextView title;
    private TextView body;
    private TextView numCorrect;
    private TextView numWrong;
    private TextView deckSize;

    private FloatingActionButton success;
    private FloatingActionButton fail;
    private Button reveal;

    private String[] content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        this.getSupportActionBar().hide();

        title = findViewById(R.id.quizTitle);
        body = findViewById(R.id.quizBody);
        numCorrect = findViewById(R.id.numCorrect);
        numWrong = findViewById(R.id.numWrong);
        deckSize = findViewById(R.id.numCards);

        success = findViewById(R.id.successButton);
        fail = findViewById(R.id.failButton);
        reveal = findViewById(R.id.revealAnswer);
        content = uiManager.getContent(-1);
        updateNumbers();

        body.setVisibility(View.INVISIBLE);

        title.setText(content[0]);
        body.setText(content[1]);


        reveal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                body.setVisibility(View.VISIBLE);
            }
        });

        success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uiManager.setAnswer(true);
                body.setVisibility(View.INVISIBLE);
                updateCardContent();
                updateNumbers();
            }
        });

        fail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uiManager.setAnswer(false);
                body.setVisibility(View.INVISIBLE);
                updateCardContent();
                updateNumbers();
            }
        });
    }

    private void updateNumbers(){
        int[] numbers = uiManager.getQuizInfo();

        deckSize.setText(Integer.toString(numbers[0]));
        numCorrect.setText(Integer.toString(numbers[1]));
        numWrong.setText(Integer.toString(numbers[2]));
    }

    private void updateCardContent(){
        if(!uiManager.isQuizDone()) {
            content = uiManager.getContent(-1);
            title.setText(content[0]);
            body.setText(content[1]);
        }else{
            finish();
        }
    }
}
