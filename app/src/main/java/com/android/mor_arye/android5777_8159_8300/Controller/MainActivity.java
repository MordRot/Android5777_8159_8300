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

            //<editor-fold desc="Buisness">
            //~~~~~~~~~~~~~~~~~~~~ BUSINESS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
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
                            Uri.parse("content://com.android.mor_arye.android5777_8159_8300/businesses"), newBusiness);

                    return null;
                }
            }.execute();
            //</editor-fold>

            //<editor-fold desc="Recreation">
            //~~~~~~~~~~~~~~~~~~~~ RECREATION ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            final ContentValues newRecreation = new ContentValues();

            newRecreation.put("typeOfRecreation", "HOTEL");
            newRecreation.put("nameOfCountry", "israel");
            newRecreation.put("dateOfBeginning", "11/11/2016");
            newRecreation.put("dateOfEnding", "12/10/2017");
            newRecreation.put("price", 100.2);
            newRecreation.put("description", "we will have fun!");
            newRecreation.put("idBusiness", 123);

            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {

                    getContentResolver().insert(
                            Uri.parse("content://com.android.mor_arye.android5777_8159_8300/recreations"), newRecreation);

                    return null;
                }
            }.execute();
            //</editor-fold>

            //<editor-fold desc="User">
            //~~~~~~~~~~~~~~~~~~~~~ USER ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
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
            //</editor-fold>

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
