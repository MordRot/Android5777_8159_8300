package com.android.mor_arye.android5777_8159_8300.Model.Service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.android.mor_arye.android5777_8159_8300.Model.Backend.CustomContentProvider;
import com.android.mor_arye.android5777_8159_8300.Model.Backend.IDSManager;
import com.android.mor_arye.android5777_8159_8300.Model.Backend.ManagerFactory;

public class CheckForUpdateService extends IntentService {
    public static final String SERVICE_TAG = "ServiceComponent";
    private static IDSManager DSManager = ManagerFactory.getDS();

    public CheckForUpdateService() {
        super("CheckForUpdatesThread");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent,flags,startId);
    }
    @Override
    protected void onHandleIntent(Intent checkForUpdatesIntent){
        Intent businessUpdateIntent;
        Intent userUpdateIntent;
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
                    sendBroadcast(businessUpdateIntent);
                }

                if (DSManager.checkNewRecreation()) {
                    sendBroadcast(userUpdateIntent);
                }

            } catch (InterruptedException e) {
                Log.d(SERVICE_TAG, "ERROR in Service");
                Thread.currentThread().interrupt();
            }
        }
    }
/*
apparently not needed, no component will bind to the service
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
*/
}
