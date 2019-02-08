package comp3350.flashy.presentation;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import comp3350.flashy.R;

public class CreateDeckActivity extends AppCompatActivity {
    private ImageView deck1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_deck);

        deck1 = (ImageView) findViewById(R.id.random_image);
        deck1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFlashCardActivity();
                System.out.println("Deck pressed");
            }
        });

    }

    public void openFlashCardActivity(){
        Intent intent = new Intent(this, CreateFlashCardActivity.class);
        startActivity(intent);
    }
}

