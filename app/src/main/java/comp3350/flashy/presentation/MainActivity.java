package comp3350.flashy.presentation;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import comp3350.flashy.R;
import comp3350.flashy.application.Main;
import comp3350.flashy.business.AccessCreateAccounts;
import comp3350.flashy.domain.CreateAccount;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.persistence.hsqldb.PersistenceException;

public class MainActivity extends AppCompatActivity {
    private Button giveAccess;
    private Button register;
    private Button deleteUser;

    private int selectedPos;


    //DB STUFF
    private AccessCreateAccounts accessCreateAccount;
    private List<CreateAccount> accountList;
    private ArrayAdapter<CreateAccount> createAccountArrayAdapter;
    private int selectedAccountPosition = -1;
    private TextView usernameTest;


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

        //PRATIk's Edits
        copyDatabaseToDevice();
        accessCreateAccount = new AccessCreateAccounts();

        register = (Button) findViewById(R.id.addProfile);
        //password = ((EditText) findViewById(R.id.profilePass)).getText().toString();
        giveAccess = (Button)findViewById(R.id.Enter);
        profiles = (ListView) findViewById(R.id.profiles);
        //username = ((TextView) findViewById(R.id.selectedUser)).getText().toString();
        deleteUser = (Button) findViewById(R.id.deleteProfile);
        usernameTest = (TextView) findViewById(R.id.selectedUser);

        //accessCreateAccount.getAccounts();
        pList = ui.getAllProfileNames();

        System.out.println("profiles: " + pList.toString());


        accountList = new ArrayList<>();
        accountList.addAll(accessCreateAccount.getAccounts());
        createAccountArrayAdapter = new ArrayAdapter<CreateAccount>(this, R.layout.profile_list_item, R.id.profileName, accountList)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(R.id.profileName);
                text1.setText(accountList.get(position).getUsername());

                return view;

            }

        };
        final ListView listView = (ListView) findViewById(R.id.profiles);
        createAccountArrayAdapter.notifyDataSetChanged();
        listView.setAdapter(createAccountArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Button updateButton = (Button)findViewById(R.id.deleteProfile);
                Button deleteButton = (Button)findViewById(R.id.addProfile);

                if (position == selectedAccountPosition) {
                    listView.setItemChecked(position, false);
                    updateButton.setEnabled(false);
                    deleteButton.setEnabled(false);
                    selectedAccountPosition = -1;
                } else {
                    listView.setItemChecked(position, true);
                    updateButton.setEnabled(true);
                    deleteButton.setEnabled(true);
                    selectedAccountPosition = position;
                    selectCourseAtPosition(position);
                }
            }
        });


        profiles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPos = position;
                usernameTest.setText(accountList.get(position).getUsername());


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
            public void onClick(View v) {
                 String username;
                 String password;

                updateData();
                username =  ((TextView)findViewById(R.id.selectedUser)).getText().toString();
                password = ((EditText) findViewById(R.id.profilePass)).getText().toString();
                Context context = getApplicationContext();
                CharSequence returnText;
                int duration = Toast.LENGTH_LONG;
                returnText = "Please type in correct information";

                try {
                    if (accessCreateAccount.getAccountInformation(username, password)){
                        returnText = "Welcome " + username.toUpperCase();

                        Toast toast = Toast.makeText(context, returnText, duration);
                        toast.show();
                        openDeckMenuActivity(v);
                    }
                    else{
                        returnText = "Incorrect username or password";

                        Toast toast = Toast.makeText(context, returnText, duration);
                        toast.show();
                    }
                    Toast toast = Toast.makeText(context, returnText, duration);
                    toast.show();
                } catch (PersistenceException e) {
                    e.printStackTrace();
                }
                //openDeckMenuActivity(username.getText().toString(),password.getText().toString());
            }
        });
    }

    public void buttonAccountDeleteOnClick(View v) {
        CreateAccount account = createUserFromEditText();

        try {
            accessCreateAccount.deleteAccount(account);
            accountList.clear();
            accountList.addAll(accessCreateAccount.getAccounts());
            createAccountArrayAdapter.notifyDataSetChanged();
        } catch (final Exception e) {
            //Messages.warning(this, e.getMessage());
        }
    }

    private CreateAccount createUserFromEditText() {

        TextView editName = (TextView) findViewById(R.id.profileName);
        EditText editPass = (EditText)findViewById(R.id.profilePass);

        CreateAccount account = new CreateAccount(editName.getText().toString(), editPass.getText().toString());

        return account;
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateData();

    }

    private void updateData(){
        accountList.clear();
        accountList.addAll(accessCreateAccount.getAccounts());
        createAccountArrayAdapter.notifyDataSetChanged();
        pList.clear();
        pList.addAll(ui.getAllProfileNames());
//         profileArrayAdapter.notifyDataSetChanged();
       // password.setText("");
    }


    public void selectCourseAtPosition(int position) {
        CreateAccount selected = createAccountArrayAdapter.getItem(position);

        TextView editName = findViewById(R.id.profileName);

        editName.setText(selected.getUsername());
    }

    public static uiHandler getHandler() {

        return ui;
    }
    public void openDeckMenuActivity(View view){
      Intent startDeckActivity = new Intent(this, DeckMenuActivity.class);
      startActivity(startDeckActivity);

    }
    public void openRegistrationActivity(){
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