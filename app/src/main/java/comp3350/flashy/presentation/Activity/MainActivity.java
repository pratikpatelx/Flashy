package comp3350.flashy.presentation.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import comp3350.flashy.R;
import comp3350.flashy.application.Main;
import comp3350.flashy.presentation.Handler.Interface.profileHandler;
import comp3350.flashy.presentation.Handler.uiHandler;

public class MainActivity extends AppCompatActivity {
    public static uiHandler ui;
    private profileHandler profile;

    ArrayList<String> pList;
    ArrayAdapter<String> profileArrayAdapter;


    private Button giveAccess;
    private Button register;
    private Button deleteUser;
    private EditText password;
    private int selectedPos;
    private TextView username;
    private ListView profiles;

    public static uiHandler getHandler() {

        return ui;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().hide();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //initialize app
        copyDatabaseToDevice();
        ui = new uiHandler();

        profile = ui.getProfileHandler();

        register = findViewById(R.id.addProfile);
        password = findViewById(R.id.profilePass);
        giveAccess = findViewById(R.id.Enter);
        profiles = findViewById(R.id.profiles);
        username = findViewById(R.id.selectedUser);
        deleteUser = findViewById(R.id.deleteProfile);


        pList = profile.getAllProfileNames();

        profileArrayAdapter = new ArrayAdapter<String>(this, R.layout.profile_list_item, R.id.profileName, pList) {
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
                openDeckMenuActivity(username.getText().toString(), password.getText().toString());
            }
        });

        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence returnText;
                Context context = getApplicationContext();
                CharSequence showText = "User " + username.getText() + " has been deleted";
                int duration = Toast.LENGTH_LONG;
                //remove user
                profile.deleteUser(pList.get(selectedPos));
                Toast toast = Toast.makeText(context, showText, duration);
                toast.show();
                updateData();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateData();

    }

    private void updateData() {
        pList.clear();
        pList.addAll(profile.getAllProfileNames());
        profileArrayAdapter.notifyDataSetChanged();
        password.setText("");
    }

    public void openDeckMenuActivity(String username, String password) {
        int duration = Toast.LENGTH_LONG;
        CharSequence showText = "Authentication Failed";
        Context context = getApplicationContext();

        if (profile.Verified(username, password)) {
            Intent intent = new Intent(this, ShowAllDecksActivity.class);
            intent.putExtra("USERNAME",username);
            startActivity(intent);
            showText = "Welcome " + username.toUpperCase();
            Toast toast = Toast.makeText(context, showText, duration);
            toast.show();
        } else
            showText = "Incorrect Username or Password";
        Toast toast = Toast.makeText(context, showText, duration);
        toast.show();

    }

    public void openRegistrationActivity() {
        Intent intent = new Intent(this, FlashyRegistrationActivity.class);
        startActivity(intent);
    }

    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

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

            Main.setDBPathName(dataDirectory.toString() + "/" + Main.getDBPathName());

        } catch (final IOException ioe) {
            // Messages.warning(this, "Unable to access application data: " + ioe.getMessage());
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
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




