package comp3350.flashy.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import comp3350.flashy.R;


public class NewFlashcardActivity extends AppCompatActivity {

    private EditText Question;
    private EditText Answer;
    private Button Add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_flashcard);

        Question = (EditText)findViewById(R.id.etNewFlashcardQuestion);
        Answer = (EditText)findViewById(R.id.etNewFlashcardAnswer);
        Add = (Button)findViewById(R.id.btnNewFlashcardAdd);

        Add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
               // Intent intent = new Intent(NewFlashcardActivity.this, EditDeckActivity.class);
                //startActivity(intent);
            }
        });
    }
}
