package comp3350.flashy.presentation;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
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
import comp3350.flashy.persistence.hsqldb.PersistenceException;

public class FlashyRegistrationActivity extends AppCompatActivity {

    private Button cancel;
/*

    private EditText Username;
    private EditText password;
    private uiHandler uiManager = MainActivity.getHandler();
*/

    //Pratik's Edit
    private AccessCreateAccounts accessCreateAccount;
    private List<CreateAccount> accountList;
    private ArrayAdapter<CreateAccount> createAccountArrayAdapter;
    private int selectedAcccountPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashy_registration);
        copyDatabaseToDevice();
        accessCreateAccount = new AccessCreateAccounts();

        try {
            accountList = new ArrayList<>();
            accountList.addAll(accessCreateAccount.getAccounts());
            createAccountArrayAdapter = new ArrayAdapter<CreateAccount>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, accountList)
            {
                @Override
                public View getView(int position,  View convertView,  ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(accountList.get(position).getUsername());
                    text2.setText(accountList.get(position).getPassword());

                    return view;
                }
            };

            final ListView listView = (ListView)findViewById(R.id.profiles);

        }catch (final Exception e){

            //Messages.fatalError(this, e.getMessage());
        }

        cancel = findViewById(R.id.cancelReg);
        final Button registration = findViewById(R.id.regButton);
 /*       Username = findViewById(R.id.Username);
        password = findViewById(R.id.userPassword);*/

        registration.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                CreateAccount newAccount = createAccountFromObjectActivity();
                CreateAccount checkAccount = null;
                CharSequence returnText;
                Context context = getApplicationContext();
                returnText = "Please fill up every section";
                int duration = Toast.LENGTH_LONG;

                try {
                    checkAccount = accessCreateAccount.insertAccountInformation(newAccount);
                } catch (PersistenceException e) {
                    returnText = "Error in saving user to database";
                } catch (Exception e) {
                    returnText = e.getMessage();
                }

                if (checkAccount == newAccount) {
                    returnText = "Account Created";

                    Toast toast = Toast.makeText(context, returnText, duration);
                    toast.show();

                    logInToAccount(v);
                } else {
                    Toast toast = Toast.makeText(context, returnText, duration);
                    toast.show();
                }

                Toast toast = Toast.makeText(context, returnText, duration);
                toast.show();
                /*if(uiManager.registerUser(Username.getText().toString(),password.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Registration successful",Toast.LENGTH_LONG).show();
                    finish();
                }else
                    Toast.makeText(getApplicationContext(),"Registration unsuccessful",Toast.LENGTH_LONG).show();*/


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private CreateAccount createAccountFromObjectActivity(){
        String userName, password;
        userName = ((EditText) findViewById(R.id.Username)).getText().toString();
        password = ((EditText) findViewById(R.id.userPassword)).getText().toString();

        return new CreateAccount(userName,password);
    }

    public void logInToAccount(View view){
        Intent logInToAccount = new Intent(this, MainActivity.class);
        startActivity(logInToAccount);
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
