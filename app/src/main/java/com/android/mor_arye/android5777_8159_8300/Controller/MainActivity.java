package com.android.mor_arye.android5777_8159_8300.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.mor_arye.android5777_8159_8300.Model.Backend.IDSManager;
import com.android.mor_arye.android5777_8159_8300.Model.Backend.ManagerFactory;
import com.android.mor_arye.android5777_8159_8300.Model.Entities.Business;
import com.android.mor_arye.android5777_8159_8300.Model.Entities.Recreation;
import com.android.mor_arye.android5777_8159_8300.Model.Service.CheckForUpdateService;
import com.android.mor_arye.android5777_8159_8300.R;

public class MainActivity extends AppCompatActivity {

    public static IDSManager DSManager = ManagerFactory.getDS();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent checkForUpdates = new Intent(this, CheckForUpdateService.class);
        startService(checkForUpdates);

        final Button addBusiness = (Button) findViewById(R.id.addNewBusiness);
        addBusiness.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, AddBusinessActivity.class);
                startActivity(myIntent);
            }
        });

        final Button addRecreation = (Button) findViewById(R.id.addNewRecreation);
        addRecreation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, AddRecreationActivity.class);
                startActivity(myIntent);
            }
        });
    }

    public void checkDS(View view)
    {
        try
        {
            Log.d("checkDS","Businesses: ");
            Log.d("checkDS",DSManager.getAllBusinesses().toString() + ", ");
            Log.d("checkDS","\nRecreations: ");
            for (Recreation r: DSManager.getAllRecreations())
            {
                Log.d("checkDS",r.toString() + ", ");
            }
        }
        catch (Exception ex)
        {
            Log.d("checkDS", ex.getMessage());
        }
    }
}
