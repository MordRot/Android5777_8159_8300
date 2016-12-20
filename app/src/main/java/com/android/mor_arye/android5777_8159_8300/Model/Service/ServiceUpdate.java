package com.android.mor_arye.android5777_8159_8300.Model.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.android.mor_arye.android5777_8159_8300.Model.Backend.CustomContentProvider;
import com.android.mor_arye.android5777_8159_8300.Model.Backend.IDSManager;
import com.android.mor_arye.android5777_8159_8300.Model.Backend.ManagerFactory;

public class ServiceUpdate extends Service {
    private static IDSManager DSManager = ManagerFactory.getDS();
    Intent businessUpdateIntent;
    Intent userUpdateIntent;

    public ServiceUpdate() {
        businessUpdateIntent = new Intent();
        businessUpdateIntent.setAction("UpdateDS");
        businessUpdateIntent.addCategory("business");

        userUpdateIntent = new Intent();
        userUpdateIntent.setAction("UpdateDS");
        userUpdateIntent.addCategory("business");

        while (true) {
            try {
                Thread.sleep(10000);

                if (DSManager.checkNewInBusiness()) {
                    startActivity(businessUpdateIntent);
                }

                if (DSManager.checkNewRecreation()) {
                    startActivity(userUpdateIntent);
                }

            } catch (InterruptedException e) {
                Log.d(CustomContentProvider.CP_TAG, "ERROR in Service");
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
