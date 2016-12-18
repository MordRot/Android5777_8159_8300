package com.android.mor_arye.android5777_8159_8300.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.mor_arye.android5777_8159_8300.R;

import static com.android.mor_arye.android5777_8159_8300.Controller.LoginActivity.NAME_KEY;
import static com.android.mor_arye.android5777_8159_8300.Controller.LoginActivity.PASSWORD_KEY;
import static com.android.mor_arye.android5777_8159_8300.Controller.LoginActivity.PREFS_NAME;

public class RegistrationActivity extends AppCompatActivity {

//  SharedPreferences shPrefRegister;
    SharedPreferences.Editor editRegiser;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void onRegister(View view) {
        saveToShPref();
        saveToDB();
    }


    private void saveToShPref() {

        editRegiser = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();

        String name = ((TextView) findViewById(R.id.NameEdit)).getText().toString();
        String pass = ((TextView) findViewById(R.id.passwordEdit)).getText().toString();

        editRegiser.putString(NAME_KEY, name);
        editRegiser.putString(PASSWORD_KEY, pass);
        editRegiser.commit();
    }

    private void saveToDB() {
        final ContentValues newUser = new ContentValues();

        newUser.put("nameUser", ((TextView) findViewById(R.id.NameEdit)).getText().toString());
        newUser.put("password", ((TextView) findViewById(R.id.passwordEdit)).getText().toString());

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                getContentResolver().insert(
                        Uri.parse("content://com.android.mor_arye.android5777_8159_8300/users"), newUser);

                return null;
            }
        }.execute();
    }
}
