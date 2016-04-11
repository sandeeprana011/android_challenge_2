package com.example.bhiwalakhil.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editorSettings;
    EditText epassword, eusername;
    boolean loggedIn = false;
    private String savUsername, savPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(Fields.SETTINGS, MODE_PRIVATE);
        savUsername = sharedPreferences.getString(Fields.USERNAME, "admin");
        savPassword = sharedPreferences.getString(Fields.PASSWORD, "admin");
        loggedIn = sharedPreferences.getBoolean(Fields.LOGGED_IN, false);
        editorSettings = sharedPreferences.edit();

        if (loggedIn) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
		   	finish();
		}

    }

    public void loginPressed(View view) {

        epassword = (EditText) findViewById(R.id.t_password);
        eusername = (EditText) findViewById(R.id.t_username);

        String username = eusername.getText().toString();
        String password = epassword.getText().toString();

        if (username.equals(savUsername)&&password.equals(savPassword)) {
            editorSettings.putBoolean(Fields.LOGGED_IN,true);
            editorSettings.apply();

            Intent intent = new Intent(this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

    }
}
