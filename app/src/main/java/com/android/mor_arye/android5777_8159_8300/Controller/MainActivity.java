package com.android.mor_arye.android5777_8159_8300.Controller;

import android.content.ContentValues;
import android.net.Uri;
import android.os.AsyncTask;
import android.speech.tts.Voice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.android.mor_arye.android5777_8159_8300.Model.DataSource.CustomContentProvider;
import com.android.mor_arye.android5777_8159_8300.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //addToDSWithCP();
        // TODO
        // לבדוק את כל הפונקציות של ה DS בנוסף להכנסה
        getAllUsers();

        }


    private void addToDSWithCP() {
        try {
            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            final ContentValues newBusiness = new ContentValues();
            newBusiness.put("nameBusiness", "city of david");
            newBusiness.put("addressBusiness", "king david street");
            newBusiness.put("phoneNumber", "0123456789");
            newBusiness.put("emailAddress", "exmp@gmail.com");
            newBusiness.put("websiteLink", "jct.ac.il");

            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {

                    getContentResolver().insert(
                            Uri.parse("content://com.android.mor_arye.android5777_8159_8300.Model.DataSource/Business"), newBusiness);

                    return null;
                }
            }.execute();

            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

            //TODO the same thing to newRecreation and newUser
            ContentValues newRecreation = new ContentValues();


        }
        catch (Exception ex){
            throw ex;
        }
    }

    private void getAllUsers() {
        Uri uriOfUsers = Uri.parse("content://com.android.mor_arye.android5777_8159_8300.Model.DataSource");

    }
}
