package com.android.mor_arye.android5777_8159_8300.Model.DataSource;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.android.mor_arye.android5777_8159_8300.Model.Backend.IDSManager;
import com.android.mor_arye.android5777_8159_8300.Model.Backend.ManagerFactory;
import com.android.mor_arye.android5777_8159_8300.Model.Entities.Business;

import java.util.Collection;
import java.util.List;

/**
 * Created by mor on 30 נובמבר 2016.
 */

public class CustomContentProvider extends ContentProvider {

    private static IDSManager DSmanager = ManagerFactory.getDS();

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI("com.android.mor_arye.android5777_8159_8300.Model.DataSource", "Business", 1);
        sUriMatcher.addURI("com.android.mor_arye.android5777_8159_8300.Model.DataSource", "Recreation", 2);
        sUriMatcher.addURI("com.android.mor_arye.android5777_8159_8300.Model.DataSource", "User", 3);
    }


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

                case 1: // Businesses
                    Collection<Business> businesses = DSmanager.getAllBusiness();
                    MatrixCursor businessMatrix = new MatrixCursor(new String[]{"idBusiness", "nameBusiness", "addressBusiness", "phoneNumber", "emailAddress", "websiteLink"});
                    for (Business bus : businesses) {
                        businessMatrix.addRow(new Object[]{bus.getIdBusiness(), bus.getNameBusiness(), bus.getAddressBusiness(), bus.getPhoneNumber(), bus.getEmailAddress(), bus.getWebsiteLink()});
                    }
                    return businessMatrix;

                case 2: // Recreation
                    //TODO
                    return null;

                case 3: // User
                    //TODO
                    return null;
            }
        }
        catch (Exception ex) {
            throw ex;
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
               case 1:
                   DSmanager.insertBusiness(values);
                   return null;
               case 2:
                   DSmanager.insertRecreation(values);
                   return null;
               case 3:
                   DSmanager.insertUser(values);
                   return null;
           }
       }
       catch (Exception ex) {
           throw ex;
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
