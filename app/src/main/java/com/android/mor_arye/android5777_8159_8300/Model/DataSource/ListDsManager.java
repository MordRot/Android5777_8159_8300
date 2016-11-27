package com.android.mor_arye.android5777_8159_8300.Model.DataSource;

import android.util.ArrayMap;

import com.android.mor_arye.android5777_8159_8300.Model.Entities.Actions;
import com.android.mor_arye.android5777_8159_8300.Model.Entities.Business;
import com.android.mor_arye.android5777_8159_8300.Model.Entities.Users;

import java.lang.reflect.Array;
import java.util.HashMap;

/**
 * Created by mor on 26 נובמבר 2016.
 */
//TODO איך עושים שיכיר את כל החבילה של הנתונים במקום לעשות לכל מחלקה אימפורט בנפרד

public class ListDsManager {

    public ArrayMap<Integer, Users> usersArrayMap;
    public ArrayMap<Integer, Business> businessArrayMap;
    public ArrayMap<Integer, Actions> actionsArrayMap;

    public ListDsManager() {
        usersArrayMap = new ArrayMap<Integer, Users>();
    }
}
