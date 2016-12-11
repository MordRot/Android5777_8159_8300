package com.android.mor_arye.android5777_8159_8300.Model.Backend;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.mor_arye.android5777_8159_8300.Model.Entities.Business;
import com.android.mor_arye.android5777_8159_8300.Model.Entities.Recreation;
import com.android.mor_arye.android5777_8159_8300.Model.Entities.User;

import java.util.Collection;

/**
 * Created by mor on 30 נובמבר 2016.
 */

public class CustomContentProvider extends ContentProvider {

    private static IDSManager DSManager = ManagerFactory.getDS();
    public static final String CP_TAG = "EntertainmentContent";

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI("com.android.mor_arye.android5777_8159_8300.Model.DataSource", "businesses", 1);
        sUriMatcher.addURI("com.android.mor_arye.android5777_8159_8300.Model.DataSource", "recreations", 2);
        sUriMatcher.addURI("com.android.mor_arye.android5777_8159_8300.Model.DataSource", "users", 3);
    }
//TODO
    // הכתובות URI נכונות? או שצריך לעבור דרך ה factory איכשהו?

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        try {
            int uriCode = sUriMatcher.match(uri);
            switch (uriCode) {
                case -1:
                    throw new Exception("invalid query, no such path.");
                case 1: // businesses
                    Collection<Business> businesses = DSManager.getAllBusiness();
                    MatrixCursor businessMatrix = new MatrixCursor(new String[]{"idBusiness", "nameBusiness", "addressBusiness", "phoneNumber", "emailAddress", "websiteLink"});
                    for (Business bus : businesses) {
                        businessMatrix.addRow(new Object[]{bus.getIdBusiness(), bus.getNameBusiness(), bus.getAddressBusiness(), bus.getPhoneNumber(), bus.getEmailAddress(), bus.getWebsiteLink()});
                    }
                    return businessMatrix;

                case 2: // recreations
                    Collection<Recreation> recreations = DSManager.getAllRecreation();
                    MatrixCursor RecreationMatrix = new MatrixCursor(new String[] {"typeOfRecreation", "nameOfCountry", "dateOfBeginning", "dateOfEnding", "price", "description", "idBusiness"});
                    for ( Recreation rec : recreations) {
                        RecreationMatrix.addRow(new Object[]{rec.getTypeOfRecreation(), rec.getNameOfCountry(), rec.getDateOfBeginning(), rec.getDateOfEnding(), rec.getPrice(), rec.getDescription(), rec.getIdBusiness()});
                    }
                    return RecreationMatrix;

                case 3: // users
                    Collection<User> users   = DSManager.getAllUsers();
                    MatrixCursor userMatrix = new MatrixCursor(new String[] {"idUser", "nameUser", "password"});
                    for ( User us : users) {
                        userMatrix.addRow(new Object[]{us.getIdUser(), us.getNameUser(), us.getPassword()});
                    }
                    return userMatrix;
            }
        }
        catch (Exception ex) {
            Log.d(CP_TAG, ex.getMessage());
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
       try {

           int uriCode = sUriMatcher.match(uri);
           switch (uriCode) {
               case -1:
                   throw new Exception("invalid query, no such path.");
               case 1:
                   DSManager.insertBusiness(values);
                   return null;
               case 2:
                   DSManager.insertRecreation(values);
                   return null;
               case 3:
                   DSManager.insertUser(values);
                   return null;
           }
       }
       catch (Exception ex) {
           Log.d(CP_TAG, ex.getMessage());
       }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
