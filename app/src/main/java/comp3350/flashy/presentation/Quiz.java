package comp3350.flashy.presentation;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
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
    private TextView fitbAnswer;

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
        fitbAnswer = findViewById(R.id.fitbAnswer);

        success = findViewById(R.id.successButton);
        fail = findViewById(R.id.failButton);
        reveal = findViewById(R.id.revealAnswer);

        updateContent();
        setOnClickListeners();



    }


    private void updateContent(){
        if(!uiManager.isQuizDone()) {

            content = uiManager.getContent(-1);
            state = uiManager.getCurrentType();
            System.out.println(state);


            //this is the state of the current card.
            switch(state){
                case 0: setStandardContent();
                        break;
                case 1: setFITBContent();
                        break;
                case 2: setMCContent();
                        break;
            }

            int[] numbers = uiManager.getQuizInfo();
            deckSize.setText(Integer.toString(numbers[0]));
            numCorrect.setText(Integer.toString(numbers[1]));
            numWrong.setText(Integer.toString(numbers[2]));

        }else{
            finish();
        }
    }

    private void setFITBContent() {
        stdTitle.setText(content[0]);
        stdBody.setVisibility(View.INVISIBLE);
        fitbBody.setText(content[1]);
        fitbAnswer.setText(content[2]);
        fitbAnswer.setVisibility(View.INVISIBLE);
        fitbBody.setVisibility(View.VISIBLE);
    }

    private void setStandardContent() {
        stdTitle.setText(content[0]);
        stdBody.setText(content[1]);
        stdBody.setVisibility(View.INVISIBLE);
        fitbAnswer.setVisibility(View.INVISIBLE);
        fitbBody.setVisibility(View.INVISIBLE);
    }

    private void setMCContent() {

    }

    private void setOnClickListeners(){
        reveal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch(state){
                    case 0: stdBody.setVisibility(View.VISIBLE);
                        break;
                    case 1: fitbAnswer.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        break;

                }

            }
        });

        success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uiManager.setAnswer(true);
                updateContent();

            }
        });

        fail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uiManager.setAnswer(false);
                updateContent();

            }
        });
    }
}
