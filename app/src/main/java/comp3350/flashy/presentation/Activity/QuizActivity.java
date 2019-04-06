package comp3350.flashy.presentation.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.flashy.R;
import comp3350.flashy.presentation.Handler.Interface.quizHandler;

import static comp3350.flashy.R.color.defaultmcanswer;
import static comp3350.flashy.R.color.flashcard_color;
import static comp3350.flashy.R.color.wrongchoice;

public class QuizActivity extends AppCompatActivity {

    int state;


    private TextView stdTitle;
    private TextView stdBody;
    private TextView fitbBody;
    private TextView numCorrect;
    private TextView numWrong;
    private TextView deckSize;
    private TextView fitbAnswer;
    private TextView MCQ;
    private TextView MCChoice1;
    private TextView MCChoice2;
    private TextView MCChoice3;
    private TextView MCChoice4;

    private FloatingActionButton success;
    private FloatingActionButton fail;
    private Button reveal;

    private String[] content;
    private ArrayList<String> contentMC;

    private quizHandler quiz = MainActivity.getHandler().getQuizHandler();

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

        MCQ = findViewById(R.id.mcBody);
        MCChoice1 = findViewById(R.id.mcQ1);
        MCChoice2 = findViewById(R.id.mcQ2);
        MCChoice3 = findViewById(R.id.mcQ3);
        MCChoice4 = findViewById(R.id.mcQ4);

        success = findViewById(R.id.successButton);
        fail = findViewById(R.id.failButton);
        reveal = findViewById(R.id.revealAnswer);

        Bundle info = getIntent().getExtras();
        String username = info.getString("NAME");
        String deck = info.getString("DECK");

        quiz.startQuiz(username,deck);


        updateContent();
        setOnClickListeners();
    }


    private void updateContent() {
        if (!quiz.isQuizDone()) {

            if (quiz.getNextCardType() != 2) {
                content = quiz.getContent();
                state = quiz.getCurrentType();
            } else {
                contentMC = quiz.getMCContent();
                state = 2;
            }


            //this is the state of the current card.
            switch (state) {
                case 0:
                    setStandardContent();
                    break;
                case 1:
                    setFITBContent();
                    break;
                case 2:
                    setMCContent();
                    break;
            }

            int[] numbers = quiz.getQuizInfo();
            deckSize.setText(Integer.toString(numbers[0]));
            numCorrect.setText(Integer.toString(numbers[1]));
            numWrong.setText(Integer.toString(numbers[2]));

        } else {
            finish();
        }
    }

    private void setFITBContent() {
        stdTitle.setText(content[0]);
        fitbBody.setText(content[1]);
        fitbAnswer.setText(content[2]);

        stdBody.setVisibility(View.INVISIBLE);
        stdTitle.setVisibility(View.VISIBLE);
        fitbAnswer.setVisibility(View.INVISIBLE);
        fitbBody.setVisibility(View.VISIBLE);
        reveal.setVisibility(View.VISIBLE);

        MCQ.setVisibility(View.INVISIBLE);
        MCChoice1.setVisibility(View.INVISIBLE);
        MCChoice2.setVisibility(View.INVISIBLE);
        MCChoice3.setVisibility(View.INVISIBLE);
        MCChoice4.setVisibility(View.INVISIBLE);
    }

    private void setStandardContent() {
        stdTitle.setText(content[0]);
        stdBody.setText(content[1]);

        stdTitle.setVisibility(View.VISIBLE);
        stdBody.setVisibility(View.VISIBLE);
        reveal.setVisibility(View.VISIBLE);

        stdBody.setVisibility(View.INVISIBLE);
        fitbAnswer.setVisibility(View.INVISIBLE);
        fitbBody.setVisibility(View.INVISIBLE);

        MCQ.setVisibility(View.INVISIBLE);
        MCChoice1.setVisibility(View.INVISIBLE);
        MCChoice2.setVisibility(View.INVISIBLE);
        MCChoice3.setVisibility(View.INVISIBLE);
        MCChoice4.setVisibility(View.INVISIBLE);
    }

    private void setMCContent() {
        resetBackgrounds();

        MCQ.setText(contentMC.get(0));
        MCChoice1.setText(contentMC.get(2));
        MCChoice2.setText(contentMC.get(3));
        MCChoice3.setText(contentMC.get(4));
        MCChoice4.setText(contentMC.get(5));


        stdBody.setVisibility(View.INVISIBLE);
        fitbAnswer.setVisibility(View.INVISIBLE);
        fitbBody.setVisibility(View.INVISIBLE);
        stdTitle.setVisibility(View.INVISIBLE);
        reveal.setVisibility(View.INVISIBLE);

        MCQ.setVisibility(View.VISIBLE);
        MCChoice1.setVisibility(View.VISIBLE);
        MCChoice2.setVisibility(View.VISIBLE);
        MCChoice3.setVisibility(View.VISIBLE);
        MCChoice4.setVisibility(View.VISIBLE);

    }

    private void resetBackgrounds() {

        MCChoice1.setBackgroundColor(getResources().getColor(defaultmcanswer));
        MCChoice2.setBackgroundColor(getResources().getColor(defaultmcanswer));
        MCChoice3.setBackgroundColor(getResources().getColor(defaultmcanswer));
        MCChoice4.setBackgroundColor(getResources().getColor(defaultmcanswer));
    }

    private void setOnClickListeners() {
        reveal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (state) {
                    case 0:
                        stdBody.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        fitbAnswer.setVisibility(View.VISIBLE);
                        break;

                }

            }
        });

        success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quiz.setAnswer(true);
                updateContent();

            }
        });

        fail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quiz.setAnswer(false);
                updateContent();

            }
        });

        MCChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackgrounds();

            }
        });
        MCChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackgrounds();

            }
        });
        MCChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackgrounds();

            }
        });
        MCChoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackgrounds();

            }
        });


    }


    private void setBackgrounds() {
        int num = 0;

        for (int i = 2; i < contentMC.size(); i++) {
            if (contentMC.get(1).equals(contentMC.get(i))) {
                num = i;


            }
        }

        MCChoice1.setBackgroundColor(getResources().getColor(wrongchoice));
        MCChoice2.setBackgroundColor(getResources().getColor(wrongchoice));
        MCChoice3.setBackgroundColor(getResources().getColor(wrongchoice));
        MCChoice4.setBackgroundColor(getResources().getColor(wrongchoice));

        switch (num) {
            case 2:
                MCChoice1.setBackgroundColor(getResources().getColor(flashcard_color));
                break;
            case 3:
                MCChoice2.setBackgroundColor(getResources().getColor(flashcard_color));
                break;
            case 4:
                MCChoice3.setBackgroundColor(getResources().getColor(flashcard_color));
                break;
            case 5:
                MCChoice4.setBackgroundColor(getResources().getColor(flashcard_color));
                break;
        }

    }
}
