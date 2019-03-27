/*package comp3350.flashy.presentation;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
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
    private FloatingActionButton register;
    private FloatingActionButton deleteUser;
    private EditText password;
    private int selectedPos;
    private TextView username;

    private Button test;

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

        register = (FloatingActionButton) findViewById(R.id.addProfile);
        password = (EditText) findViewById(R.id.profilePass);
        giveAccess = (Button)findViewById(R.id.Enter);
        profiles = (ListView) findViewById(R.id.profiles);
        username = (TextView) findViewById(R.id.selectedUser);
        deleteUser = (FloatingActionButton) findViewById(R.id.deleteProfile);


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

}*/


package comp3350.flashy.presentation;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.design.widget.FloatingActionButton;
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

import comp3350.flashy.application.DBSetup;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import comp3350.flashy.R;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.presentation.DeckMenuActivity;
import comp3350.flashy.presentation.FlashyRegistrationActivity;
import comp3350.flashy.presentation.uiHandler;

public class MainActivity extends AppCompatActivity {
    private Button giveAccess;
    private FloatingActionButton register;
    private FloatingActionButton deleteUser;
    private EditText password;
    private int selectedPos;
    private TextView username;


    private ListView profiles;
    ArrayList<String> pList;
    ArrayAdapter<String> profileArrayAdapter;

    public static uiHandler ui = new uiHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        copyDatabaseToDevice();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().hide();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        register = (FloatingActionButton) findViewById(R.id.addProfile);
        password = (EditText) findViewById(R.id.profilePass);
        giveAccess = (Button)findViewById(R.id.Enter);
        profiles = (ListView) findViewById(R.id.profiles);
        username = (TextView) findViewById(R.id.selectedUser);
        deleteUser = (FloatingActionButton) findViewById(R.id.deleteProfile);

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

    private void copyDatabaseToDevice() {
        final String DB_PATH = "FlashyDB";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {
            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            DBSetup.setDBPathName(dataDirectory.toString() + "/" + DBSetup.getDBPathName());

        } catch (final IOException ioe) {
            System.out.println("Unable to access application data: " + ioe.getMessage());
        }
    }

    private void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }
}

