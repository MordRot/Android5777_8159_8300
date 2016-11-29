package com.android.mor_arye.android5777_8159_8300.Model.DataSource;

import com.android.mor_arye.android5777_8159_8300.Model.Backend.IDSManager;
import com.android.mor_arye.android5777_8159_8300.Model.Entities.Recreation;
import com.android.mor_arye.android5777_8159_8300.Model.Entities.Business;
import com.android.mor_arye.android5777_8159_8300.Model.Entities.Users;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by mor on 26 נובמבר 2016.
 */


public class ListDsManager implements IDSManager {

    public HashMap<Integer, Users> usersArrayMap;
    public HashMap<Integer, Business> businessArrayMap;
    public HashMap<Integer, Recreation> actionsArrayMap;


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
    public Collection<Users> getAllUsers() {
        return usersArrayMap.values();
    }

    @Override
    public Collection<Business> getAllBusiness() {
        return businessArrayMap.values();

    }

    @Override
    public Collection<Recreation> getAllActions() {
        return actionsArrayMap.values();
    }

    @Override
    public void checkChanges() {

    }
}
