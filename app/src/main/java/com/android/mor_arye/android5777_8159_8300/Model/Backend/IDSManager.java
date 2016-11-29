package com.android.mor_arye.android5777_8159_8300.Model.Backend;

import com.android.mor_arye.android5777_8159_8300.Model.Entities.Recreation;
import com.android.mor_arye.android5777_8159_8300.Model.Entities.Business;
import com.android.mor_arye.android5777_8159_8300.Model.Entities.Users;

import java.util.Collection;

/**
 * Created by mor on 26 נובמבר 2016.
 */

public interface IDSManager {

    public void insertUser(Users newUser);
    public void insertBusiness();
    public void insertActions();
    public boolean checkNewInBusinessOrActions();
    public Collection<Users> getAllUsers();
    public Collection<Business> getAllBusiness();
    public Collection<Recreation> getAllActions();
    public void checkChanges();
    //איפה לבדוק שינויים? ברשימות? כלומר האם היום שינויים ברשומות קיימות?
}
