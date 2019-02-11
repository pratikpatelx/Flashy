package comp3350.flashy.presentation;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import comp3350.flashy.R;
import comp3350.flashy.logic.uiHandler;



public class MainActivity extends AppCompatActivity {
    private Button giveAccess;
    public static uiHandler ui = new uiHandler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ui.addStub(); //added sample stub for testing
        giveAccess = (Button)findViewById(R.id.login_button);
        giveAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateDeckActivity();
                System.out.println("Log in Button CLicked");
            }
        });
    }


    public static uiHandler getHandler() {

        return ui;
    }
    public void openCreateDeckActivity(){
        Intent intent = new Intent(this, CreateDeckActivity.class);
        startActivity(intent);
    }


}

