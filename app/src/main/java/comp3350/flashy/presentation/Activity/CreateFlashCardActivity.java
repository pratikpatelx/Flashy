package comp3350.flashy.presentation.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.flashy.R;
import comp3350.flashy.presentation.Handler.Interface.deckHandler;

public class CreateFlashCardActivity extends AppCompatActivity {
    ArrayList<String> mcContent;
    private TextView textViewFlashBody;
    private TextView textViewFlashTitle;
    private ArrayAdapter menuArrayAdapter;
    private ArrayList<String> list;
    private int type;
    private AlertDialog userInput;
    private String title = "Title";
    private String body = "Example of front side of flash card";
    private boolean bodySelected;
    private EditText toShow;
    private Spinner menu;
    private boolean modifying = false;
    private FloatingActionButton save;
    private FloatingActionButton cancel;
    private EditText mcQuestion;
    private EditText mcCorrect;
    private EditText mcWrong1;
    private EditText mcWrong2;
    private EditText mcWrong3;
    private TextView instructionsMC;
    private deckHandler deck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_flash_card);
        deck = MainActivity.getHandler().getDeckHandler();

        mcCorrect = findViewById(R.id.correctMC);
        mcWrong1 = findViewById(R.id.wrongMC1);
        mcWrong2 = findViewById(R.id.wrongMC2);
        mcWrong3 = findViewById(R.id.wrongMC3);
        mcQuestion = findViewById(R.id.mcQuestion);
        instructionsMC = findViewById(R.id.instructionsMC);


        //getting body, title and save button.
        textViewFlashBody = findViewById(R.id.body);
        textViewFlashTitle = findViewById(R.id.title);
        save = findViewById(R.id.saveButton);
        cancel = findViewById(R.id.cancelButton);
        toShow = new EditText(this);
        menu = findViewById(R.id.createMenu);

        type = 0;


        final Bundle extra = getIntent().getExtras();
        String[] card;

        if (extra != null) {
            if ((mcContent = extra.getStringArrayList("MC-CONTENT")) != null) {

                mcQuestion.setText(mcContent.get(0));
                mcCorrect.setText(mcContent.get(1));
                mcWrong1.setText(mcContent.get(2));
                mcWrong2.setText(mcContent.get(3));
                mcWrong3.setText(mcContent.get(4));

                type = 2;
            } else {
                extra.getString("FLASHCARD");
                title = extra.getString("NAME");
                body = extra.getString("BODY");
                type = extra.getInt("TYPE");

                textViewFlashBody.setText(body);
                textViewFlashTitle.setText(title);


            }

            modifying = true;

        }

        list = deck.getMenuData();

        menuArrayAdapter = new ArrayAdapter<String>(this, R.layout.flashcard_list_item, R.id.flashListItem, list) {
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView flashcardType = view.findViewById(R.id.flashListItem);

                flashcardType.setTextSize(35);

                flashcardType.setText(list.get(position));


                return view;
            }
        };

        menu.setAdapter(menuArrayAdapter);
        menu.setSelection(type);


        menu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = position;
                if (type == 2) {
                    showMCInterface();
                } else
                    showStandardInterface();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        userInput = new AlertDialog.Builder(this).create();

        userInput.setTitle("");
        userInput.setView(toShow);
        this.getSupportActionBar().hide();

        //the save button for user popup interface
        userInput.setButton(DialogInterface.BUTTON_POSITIVE, "Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (bodySelected) {
                    textViewFlashBody.setText(toShow.getText());
                    body = toShow.getText().toString();
                } else {
                    textViewFlashTitle.setText(toShow.getText());
                    title = toShow.getText().toString();
                }
            }
        });


        //when clicking the textfields, we can edit them
        textViewFlashBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInput.setTitle("Enter Flashcard body");
                bodySelected = true;
                toShow.setText(textViewFlashBody.getText());
                userInput.show();
            }
        });

        textViewFlashTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInput.setTitle("Enter Flashcard Title");
                bodySelected = false;
                toShow.setText(textViewFlashTitle.getText());
                userInput.show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (type == 2) {
                    deck.saveMCCard(mcQuestion.getText().toString(), getMCAnswers());
                } else
                    deck.saveCard(title, body, type);

                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (modifying) {
                    if (type == 2) {
                        deck.saveMCCard(mcQuestion.getText().toString(), getMCAnswers());
                    } else
                        deck.saveCard(extra.getString("NAME"), extra.getString("BODY"), type);

                }

                finish();
            }
        });


    }

    private void showStandardInterface() {
        textViewFlashBody.setVisibility(View.VISIBLE);
        textViewFlashTitle.setVisibility(View.VISIBLE);

        mcQuestion.setVisibility(View.INVISIBLE);
        mcWrong3.setVisibility(View.INVISIBLE);
        mcWrong2.setVisibility(View.INVISIBLE);
        mcWrong1.setVisibility(View.INVISIBLE);
        mcCorrect.setVisibility(View.INVISIBLE);
        instructionsMC.setVisibility(View.INVISIBLE);
    }


    private void showMCInterface() {
        textViewFlashBody.setVisibility(View.INVISIBLE);
        textViewFlashTitle.setVisibility(View.INVISIBLE);

        mcQuestion.setVisibility(View.VISIBLE);
        mcWrong3.setVisibility(View.VISIBLE);
        mcWrong2.setVisibility(View.VISIBLE);
        mcWrong1.setVisibility(View.VISIBLE);
        mcCorrect.setVisibility(View.VISIBLE);
        instructionsMC.setVisibility(View.VISIBLE);
    }

    private ArrayList<String> getMCAnswers() {
        ArrayList<String> result = new ArrayList<String>();

        result.add(mcCorrect.getText().toString());
        result.add(mcWrong1.getText().toString());
        result.add(mcWrong2.getText().toString());
        result.add(mcWrong3.getText().toString());

        return result;
    }

}

