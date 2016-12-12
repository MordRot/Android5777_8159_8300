package com.android.mor_arye.android5777_8159_8300;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    TextView name;
    TextView password;
    public static final String PREFS_NAME = "MyPrefsFile";
    public static final String NAME_KEY = "nameKey";
    public static final String PASSWORD_KEY = "passwordKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name = (TextView) findViewById(R.id.etUserName);
        password = (TextView) findViewById(R.id.etPassword);
        sharedpreferences = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(NAME_KEY)) {
            name.setText(sharedpreferences.getString(NAME_KEY, ""));
        }
        if (sharedpreferences.contains(PASSWORD_KEY)) {
            password.setText(sharedpreferences.getString(PASSWORD_KEY, ""));

        }

        Button button = (Button) findViewById(R.id.LoginButton_id);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*TODO
                check if one of the fields is empty
                 */


            }
        });

    }
    public void SavePrefs(View view) {
        String n = name.getText().toString();
        String p = password.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(NAME_KEY, n);
        editor.putString(PASSWORD_KEY, p);
        editor.commit();
    }

    public void clearTextViews(View view) {
        name = (TextView) findViewById(R.id.etUserName);
        password = (TextView) findViewById(R.id.etPassword);
        name.setText("");
        password.setText("");

    }

    public void GetPrefs(View view) {
        name = (TextView) findViewById(R.id.etUserName);
        password = (TextView) findViewById(R.id.etPassword);
        sharedpreferences = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (sharedpreferences.contains(NAME_KEY)) {
            name.setText(sharedpreferences.getString(NAME_KEY, ""));
        }
        if (sharedpreferences.contains(PASSWORD_KEY)) {
            password.setText(sharedpreferences.getString(PASSWORD_KEY, ""));

        }
    }
}