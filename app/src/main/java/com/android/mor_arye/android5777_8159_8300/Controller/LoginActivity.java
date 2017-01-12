package com.android.mor_arye.android5777_8159_8300.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.mor_arye.android5777_8159_8300.Model.Backend.CustomContentProvider;
import com.android.mor_arye.android5777_8159_8300.R;

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
        /*
        temporary user creation
         */
        final ContentValues newUser = new ContentValues();
        newUser.put("nameUser", "arye");
        newUser.put("password", "1234");
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                getContentResolver().insert(
                        Uri.parse("content://com.android.mor_arye.android5777_8159_8300/users"), newUser);

                return null;
            }
        }.execute();
        name = (TextView) findViewById(R.id.etUserName);
        password = (TextView) findViewById(R.id.etPassword);
        GetPrefs();
    }

    public void SavePrefs() {
        String n = name.getText().toString();
        String p = password.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(NAME_KEY, n);
        editor.putString(PASSWORD_KEY, p);
        editor.commit();
    }
    public void DeletePrefs() {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.remove(NAME_KEY);
        editor.remove(PASSWORD_KEY);
        editor.apply();
    }
    public void clearTextViews() {
        name = (TextView) findViewById(R.id.etUserName);
        password = (TextView) findViewById(R.id.etPassword);
        name.setText("");
        password.setText("");

    }

    public void GetPrefs() {
        sharedpreferences = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (sharedpreferences.contains(NAME_KEY)) {
            name.setText(sharedpreferences.getString(NAME_KEY, ""));
        }
        if (sharedpreferences.contains(PASSWORD_KEY)) {
            password.setText(sharedpreferences.getString(PASSWORD_KEY, ""));
        }
    }
    public void onRegister(View view){
        Intent myIntent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(myIntent);
    }
    public void onLogin(View view) {
        new AsyncTask<String, Void, Cursor>() {
            @Override
            protected void onPreExecute(){
                if (name.getText().toString() == "" || password.getText().toString() == "")
                    cancel(true);
            }
            @Override
            protected Cursor doInBackground(String... name) {

                Uri uriOfAllUsers = Uri.parse("content://com.android.mor_arye.android5777_8159_8300/users");
                Cursor result = getContentResolver().query(uriOfAllUsers, null, null, null, null, null);
                return result;
            }

            @Override
            protected void onPostExecute(Cursor result) {
                boolean found = false;
                int position = -1;
                while (result.moveToNext() && !found) {
                    if (result.getString(1).equals(name.getText().toString())) {
                        found = true;
                        position = result.getPosition();
                    }
                }
                if (!found)
                {
                    Log.d(CustomContentProvider.CP_TAG, "user doesn't exist");
                    clearTextViews();
                    DeletePrefs();  // if there was such user, he's already doesn't exist
                }
                else
                {
                    result.moveToPosition(position);
                    if (!password.getText().toString().equals(result.getString(2)))
                    {
                        Log.d(CustomContentProvider.CP_TAG, "password is wrong");
                        clearTextViews();
                        DeletePrefs();  // delete old prefs, password has changed
                    }
                    else
                    {
                        SavePrefs();
                        Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(myIntent);
                    }
                }
                result.close();
            }
        }.execute(name.getText().toString());
    }
}