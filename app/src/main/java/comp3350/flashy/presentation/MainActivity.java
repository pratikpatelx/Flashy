package comp3350.flashy.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import comp3350.flashy.R;
import comp3350.flashy.domain.Flashcard;

public class MainActivity extends AppCompatActivity {
    private Button giveAccess;
    private Button register;
    private Button deleteUser;
    private EditText password;
    private int selectedPos;
    private TextView username;


    private ListView profiles;
    ArrayList<String> pList;
    ArrayAdapter<String> profileArrayAdapter;

    public static uiHandler ui = new uiHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().hide();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        register = (Button) findViewById(R.id.addProfile);
        password = (EditText) findViewById(R.id.profilePass);
        giveAccess = (Button)findViewById(R.id.Enter);
        profiles = (ListView) findViewById(R.id.profiles);
        username = (TextView) findViewById(R.id.selectedUser);
        deleteUser = (Button) findViewById(R.id.deleteProfile);

        pList = ui.getAllProfileNames();

        System.out.println("profiles: " + pList.toString());

        profileArrayAdapter = new ArrayAdapter<String>(this, R.layout.profile_list_item, R.id.profileName, pList)
        {
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                username.setText(pList.get(position));


                return view;
            }
        };


        profiles.setAdapter(profileArrayAdapter);

        profiles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPos = position;
                username.setText(pList.get(position));
                password.setText("");
            }
        });

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegistrationActivity();
            }
        });
        giveAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDeckMenuActivity(username.getText().toString(),password.getText().toString());
            }
        });

        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //remove user
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateData();

    }

    private void updateData(){
        pList.clear();
        pList.addAll(ui.getAllProfileNames());
        profileArrayAdapter.notifyDataSetChanged();
        password.setText("");
    }

    public static uiHandler getHandler() {

        return ui;
    }
    public void openDeckMenuActivity(String username, String password){
        if(ui.Verified(username,password)) {
            ui.setUsername(username);
            Intent intent = new Intent(this, DeckMenuActivity.class);
            startActivity(intent);
        }else
            Toast.makeText(getApplicationContext(), "Authentication failed", Toast.LENGTH_LONG).show();

    }
    public void openRegistrationActivity(){
        Intent intent = new Intent(this, FlashyRegistrationActivity.class);
        startActivity(intent);
    }


}

