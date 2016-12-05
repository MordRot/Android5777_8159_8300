package com.android.mor_arye.android5777_8159_8300.Model.DataSource;

import android.content.ContentValues;
import android.location.Address;
import android.net.Uri;
import android.provider.ContactsContract;

import com.android.mor_arye.android5777_8159_8300.Model.Backend.IDSManager;
import com.android.mor_arye.android5777_8159_8300.Model.Entities.Recreation;
import com.android.mor_arye.android5777_8159_8300.Model.Entities.Business;
import com.android.mor_arye.android5777_8159_8300.Model.Entities.User;

import java.net.URI;
import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by mor on 26 נובמבר 2016.
 */


public class ListDsManager implements IDSManager {

    public List<User> users;
    public List<Business> businesses;
    public List<Recreation> recreeations;

    private boolean useresUpdates=false;
    private boolean businessesUpdates=false;
    private boolean recreeationsUpdates=false;

    public ListDsManager() {
        users = new ArrayList<>();
        businesses = new ArrayList<>();
        recreeations = new ArrayList<>();
    }


    @Override
    public void insertUser(ContentValues newUser) {
        useresUpdates=true;
        users.add(new User())
                //TODO

    }

    @Override
    public void insertBusiness(ContentValues newBusiness) {
        businessesUpdates=true;

        businesses.add(new Business(
                newBusiness.getAsString("nameBusiness"),
                new Address(new Locale(newBusiness.getAsString("addressBusiness"))),
                newBusiness.getAsString("phoneNumber"),
                //new ContactsContract.CommonDataKinds.Phone = newBusiness.getAsString("phoneNumber"),
                newBusiness.getAsString("emailAddress"),
                newBusiness.getAsString("websiteLink")
        ));
        //TODO
        // להמיר את השלושה האחרונים לנתונים המתאימים

    }

    @Override
    public void insertRecreation(ContentValues newRecreation) {
        //TODO
    }

    @Override
    public boolean checkNewInBusinessOrRecreation() {
        if ( useresUpdates ||  businessesUpdates || recreeationsUpdates)
        {
            useresUpdates = businessesUpdates = recreeationsUpdates =false;
            return true;
        }

        return false;
    }

    @Override
    public Collection<User> getAllUsers() {
        return users;
    }

    @Override
    public Collection<Business> getAllBusiness() {
        return businesses;
    }

    @Override
    public Collection<Recreation> getAllRecreation() {
        return recreeations;
    }

    @Override
//    public void checkChanges() {

    }
}
