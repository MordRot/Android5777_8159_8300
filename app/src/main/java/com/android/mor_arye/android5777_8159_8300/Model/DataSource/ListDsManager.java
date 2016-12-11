package com.android.mor_arye.android5777_8159_8300.Model.DataSource;

import android.content.ContentValues;
import android.location.Address;

import com.android.mor_arye.android5777_8159_8300.Model.Backend.IDSManager;
import com.android.mor_arye.android5777_8159_8300.Model.Entities.Recreation;
import com.android.mor_arye.android5777_8159_8300.Model.Entities.Business;
import com.android.mor_arye.android5777_8159_8300.Model.Entities.TypeOfRecreation;
import com.android.mor_arye.android5777_8159_8300.Model.Entities.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by mor on 26 נובמבר 2016.
 */


public class ListDsManager implements IDSManager {

    public List<User> users;
    public List<Business> businesses;
    public List<Recreation> recreations;

    private boolean usersUpdates = false;
    private boolean businessesUpdates = false;
    private boolean recreationsUpdates = false;

    // constructor
    public ListDsManager() {
        users = new ArrayList<>();
        businesses = new ArrayList<>();
        recreations = new ArrayList<>();
    }


    // ~~~~~~ insert ~~~~~~
    @Override
    public void insertUser(ContentValues newUser) {
        usersUpdates =true;
        users.add(new User(
                newUser.getAsString("nameUser"),
                newUser.getAsString("password")
        ));
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
    }

    @Override
    public void insertRecreation(ContentValues newRecreation) {
        String dateB = newRecreation.getAsString("dateOfBeginning");
        String dateE = newRecreation.getAsString("dateOfBeginning");

        recreations.add(new Recreation(
                TypeOfRecreation.valueOf(newRecreation.getAsString("typeOfRecreation")),
                newRecreation.getAsString("nameOfCountry"),
                new GregorianCalendar(
                        new Integer(dateB.substring(6,9)),
                        new Integer(dateB.substring(3,4)),
                        new Integer(dateB.substring(0,1))),
                new GregorianCalendar(
                        new Integer(dateE.substring(6,9)),
                        new Integer(dateE.substring(3,4)),
                        new Integer(dateE.substring(0,1))),
                newRecreation.getAsDouble("price"),
                newRecreation.getAsString("description"),
                newRecreation.getAsInteger("idBusiness")
        ));
    }
    // ~~~~~~~~~~~~~~~~~

    @Override
    public boolean checkNewInBusinessOrRecreation() {
        if ( usersUpdates ||  businessesUpdates || recreationsUpdates)
        {
            usersUpdates = businessesUpdates = recreationsUpdates = false;
            return true;
        }

        return false;
    }


    // ~~~~~~~ get all collections ~~~~~~~
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
        return recreations;
    }
    // ~~~~~~~~~~~~~~

//    @Override
//    public void checkChanges() {    }
}
