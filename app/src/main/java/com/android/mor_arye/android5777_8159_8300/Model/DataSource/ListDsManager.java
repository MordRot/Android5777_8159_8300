package com.android.mor_arye.android5777_8159_8300.Model.DataSource;

import android.util.ArrayMap;

import com.android.mor_arye.android5777_8159_8300.Model.Backend.IDSManager;
import com.android.mor_arye.android5777_8159_8300.Model.Entities.Actions;
import com.android.mor_arye.android5777_8159_8300.Model.Entities.Business;
import com.android.mor_arye.android5777_8159_8300.Model.Entities.Users;

import java.lang.reflect.Array;
import java.util.HashMap;

/**
 * Created by mor on 26 נובמבר 2016.
 */


public class ListDsManager implements IDSManager {

    public HashMap<Integer, Users> usersArrayMap;
    public HashMap<Integer, Business> businessArrayMap;
    public HashMap<Integer, Actions> actionsArrayMap;


    public ListDsManager() {
        usersArrayMap = new HashMap<>();
        businessArrayMap = new HashMap<>();
        actionsArrayMap = new HashMap<>();
    }


    @Override
    public void insertUser(Users newUser) {

    }

    @Override
    public void insertBusiness() {

    }

    @Override
    public void insertActions() {

    }

    @Override
    public boolean checkNewInBusinessOrActions() {
        return false;
    }

    @Override
    public void getAllUsers() {
        return usersArrayMap;
                //return array list or list
        // לשנות את משתנה ההחזרה בפרוטוטייפ
    }

    @Override
    public void getAllBusiness() {
        return businessArrayMap;

    }

    @Override
    public void getAllActions() {
        return actionsArrayMap;
    }

    @Override
    public void checkChanges() {

    }
}
