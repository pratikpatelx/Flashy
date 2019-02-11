package comp3350.flashy.presentation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import comp3350.flashy.R;
import comp3350.flashy.logic.uiHandler;

public class CreateFlashCardActivity extends AppCompatActivity {
    private TextView textViewFlashBody;
    private TextView textViewFlashTitle;

    private AlertDialog userInput;
    private String title = "";
    private String body = "";
    private boolean bodySelected;
    private EditText toShow;

    private FloatingActionButton save;
    private uiHandler uiManager = new uiHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_flash_card);

        //getting body, title and save button.
        textViewFlashBody = (TextView) findViewById(R.id.body);
        textViewFlashTitle = (TextView) findViewById(R.id.title);
        save = (FloatingActionButton) findViewById(R.id.saveButton);
        toShow = new EditText(this);

        userInput = new AlertDialog.Builder(this).create();

        userInput.setTitle("");
        userInput.setView(toShow);

        //the save button for user popup interface
        userInput.setButton(DialogInterface.BUTTON_POSITIVE, "Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (bodySelected) {
                    textViewFlashBody.setText(toShow.getText());
                    body = toShow.getText().toString();
                    System.out.println(body + " body recieved");
                } else {
                    textViewFlashTitle.setText(toShow.getText());
                    title = toShow.getText().toString();
                    System.out.println(title + " title recieved");
                }
            }
        });


        //when clicking the textfields, we can edit them
        textViewFlashBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInput.setTitle("Enter Flashcard body");
                bodySelected = true;
                System.out.println("body pressed");
                toShow.setText(textViewFlashBody.getText());
                userInput.show();
            }
        });

        textViewFlashTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInput.setTitle("Enter Flashcard Title");
                bodySelected = false;
                System.out.println("title pressed");
                toShow.setText(textViewFlashTitle.getText());
                userInput.show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(title + " :: title saved");
                System.out.println(body + " :: body saved");
                uiManager.saveCard(title, body);
                finish();
            }
        });


    }


}

