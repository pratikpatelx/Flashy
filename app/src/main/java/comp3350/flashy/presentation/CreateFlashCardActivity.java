package comp3350.flashy.presentation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.flashy.R;
import comp3350.flashy.domain.Flashcard;

public class CreateFlashCardActivity extends AppCompatActivity {
    private TextView textViewFlashBody;
    private TextView textViewFlashTitle;
    private ArrayAdapter  menuArrayAdapter;
    private ArrayList<String> list;

    private AlertDialog userInput;
    private String title = "Title";
    private String body = "Example of front side of flash card";
    private boolean bodySelected;
    private EditText toShow;
    private Spinner menu;
    private boolean modifying = false;

    private FloatingActionButton save;
    private FloatingActionButton cancel;
    private uiHandler uiManager = MainActivity.getHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_flash_card);



        //getting body, title and save button.
        textViewFlashBody = findViewById(R.id.body);
        textViewFlashTitle = findViewById(R.id.title);
        save = findViewById(R.id.saveButton);
        cancel = findViewById(R.id.cancelButton);
        toShow = new EditText(this);
        menu = findViewById(R.id.createMenu);

        final Bundle extra = getIntent().getExtras();
        String[] card;

        if(extra !=null){
            extra.getString("FLASHCARD");
            title = extra.getString("NAME");
            body = extra.getString("BODY");

            textViewFlashBody.setText(body);
            textViewFlashTitle.setText(title);

            modifying = true;

        }

        list = getMenuData();

        menuArrayAdapter = new ArrayAdapter<String>(this, R.layout.flashcard_list_item, R.id.flashListItem, list)
        {
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView flashCard = (TextView) view.findViewById(R.id.flashListItem);

                flashCard.setTextSize(35);

                flashCard.setText(list.get(position));




                return view;
            }
        };

        menu.setAdapter(menuArrayAdapter);



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

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(modifying){
                    uiManager.saveCard(extra.getString("NAME"), extra.getString("BODY"));
                }

                finish();
            }
        });


    }

    public ArrayList<String> getMenuData(){
        ArrayList<String> result = new ArrayList<String>();

        result.add("Standard");
        result.add("Fill in the blank");
        result.add("Multiple choice");
        result.add("True or false");


        return result;

    }


}

