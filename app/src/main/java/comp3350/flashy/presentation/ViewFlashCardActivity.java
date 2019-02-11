package comp3350.flashy.presentation;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import comp3350.flashy.R;
import comp3350.flashy.logic.uiHandler;

public class ViewFlashCardActivity extends AppCompatActivity {

    private TextView textViewFlashBody;
    private TextView textViewFlashTitle;

    private String body;
    private String title;

    private FloatingActionButton test;
    private uiHandler uiManager = new uiHandler();
    private FloatingActionButton prev;
    private FloatingActionButton next;
    private Button exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flash_card);

        textViewFlashBody = (TextView) findViewById(R.id.body);
        textViewFlashTitle = (TextView) findViewById(R.id.title);
        prev = (FloatingActionButton) findViewById(R.id.prevButton);
        next = (FloatingActionButton) findViewById(R.id.nextButton);
        exit = (Button) findViewById(R.id.exitButton);
        //toShow = new EditText(this);

        uiManager.addStub(); //added sample stub for testing
        String[] content = uiManager.getContent();

        title = content[0];
        body = content[1];

        textViewFlashBody.setText(body);
        textViewFlashTitle.setText(title);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uiManager.setPrevCardContent();
                String[] content = uiManager.getContent();

                title = content[0];
                body = content[1];

                textViewFlashBody.setText(body);
                textViewFlashTitle.setText(title);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uiManager.setNextCardContent();
                String[] content = uiManager.getContent();

                title = content[0];
                body = content[1];

                textViewFlashBody.setText(body);
                textViewFlashTitle.setText(title);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
