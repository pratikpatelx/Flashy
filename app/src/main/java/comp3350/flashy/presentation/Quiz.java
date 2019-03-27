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

    int state;

    private TextView stdTitle;
    private TextView stdBody;
    private TextView fitbBody;
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

        stdTitle = findViewById(R.id.quizTitle);
        stdBody = findViewById(R.id.quizBody);
        fitbBody = findViewById(R.id.fitbBody);
        numCorrect = findViewById(R.id.numCorrect);
        numWrong = findViewById(R.id.numWrong);
        deckSize = findViewById(R.id.numCards);

        success = findViewById(R.id.successButton);
        fail = findViewById(R.id.failButton);
        reveal = findViewById(R.id.revealAnswer);

        updateContent();
        setOnClickListeners();



    }


    private void updateContent(){
        if(!uiManager.isQuizDone()) {

            content = uiManager.getContent(-1);
            int[] numbers = uiManager.getQuizInfo();

            stdTitle.setText(content[0]);
            stdBody.setText(content[1]);
            fitbBody.setText(content[1]);
            deckSize.setText(Integer.toString(numbers[0]));
            numCorrect.setText(Integer.toString(numbers[1]));
            numWrong.setText(Integer.toString(numbers[2]));

        }else{
            finish();
        }
    }

    private void setOnClickListeners(){
        reveal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fitbBody.setVisibility(View.VISIBLE);
                stdBody.setVisibility(View.INVISIBLE);
            }
        });

        success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uiManager.setAnswer(true);
                stdBody.setVisibility(View.VISIBLE);
                fitbBody.setVisibility(View.INVISIBLE);
                updateContent();

            }
        });

        fail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uiManager.setAnswer(false);
                stdBody.setVisibility(View.INVISIBLE);
                updateContent();

            }
        });
    }
}
