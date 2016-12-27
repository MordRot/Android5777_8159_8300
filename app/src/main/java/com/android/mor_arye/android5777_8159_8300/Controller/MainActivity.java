package com.android.mor_arye.android5777_8159_8300.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.mor_arye.android5777_8159_8300.Model.Service.CheckForUpdateService;
import com.android.mor_arye.android5777_8159_8300.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent checkForUpdates = new Intent(this, CheckForUpdateService.class);
        startService(checkForUpdates);
    }
}
