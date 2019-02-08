package comp3350.flashy.presentation;


import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import comp3350.flashy.R;

public class CreateFlashCardActivity extends AppCompatActivity {
    private TextView textViewFlash;
    private ImageView flash;
    private FloatingActionButton test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_flash_card);

        textViewFlash = (TextView) findViewById(R.id.textView3);
        flash = (ImageView)findViewById(R.id.imageView);

        flash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Flash Card pressed");
                openDialog();
            }
        });


    }

    public void openDialog(){
        CreateFlashDialog testDialog = new CreateFlashDialog();
        testDialog.show(getSupportFragmentManager(), "dialog");
    }


}

