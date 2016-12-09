package com.android.mor_arye.android5777_8159_8300.Controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.mor_arye.android5777_8159_8300.Model.Backend.CustomContentProvider;
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
        Uri uriOfAllUsers = Uri.parse("content://com.android.mor_arye.android5777_8159_8300/users");
        Cursor result = getContentResolver().query(uriOfAllUsers,null,null,null,null);
        Log.d(CustomContentProvider.CP_TAG, result.toString());
    }
}
