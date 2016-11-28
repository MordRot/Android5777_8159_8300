package com.android.mor_arye.android5777_8159_8300.Model.Backend;

import com.android.mor_arye.android5777_8159_8300.Model.DataSource.ListDsManager;

/**
 * Created by mor on 26 נובמבר 2016.
 */
// factory singleton

public class ManagerFactory {

    private static IDSManager instance = null;

    public static IDSManager getDS(){

        if (instance == null)
            instance = new ListDsManager();

        return instance;
    }
}
