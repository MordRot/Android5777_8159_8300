package com.android.mor_arye.android5777_8159_8300.Model.Backend;

import android.content.ContentValues;

import com.android.mor_arye.android5777_8159_8300.Model.Entities.Recreation;
import com.android.mor_arye.android5777_8159_8300.Model.Entities.Business;
import com.android.mor_arye.android5777_8159_8300.Model.Entities.User;

import java.util.Collection;

/**
 * Created by mor on 26 נובמבר 2016.
 */

public interface IDSManager {

    public void insertUser(ContentValues newUser);
    public void insertBusiness(ContentValues newBusiness);
    public void insertRecreation(ContentValues newRecreation);
    public boolean checkNewInBusiness();
    public boolean checkNewRecreation();
    public Collection<User> getAllUsers();
    public Collection<Business> getAllBusiness();
    public Collection<Recreation> getAllRecreation();
//    public void checkChanges();
}
