package com.android.mor_arye.android5777_8159_8300.Controller;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.android.mor_arye.android5777_8159_8300.Model.DataSource.CustomContentProvider;
import com.android.mor_arye.android5777_8159_8300.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        addToDSWithCP();

        }


    private void addToDSWithCP() {
        //CustomContentProvider myCP = new CustomContentProvider();

        //    new AsyncTask<Void>(this.getActivity(), " add a new agency",
        //            (ProgressBar) getDialog().findViewById(R.id.addAgencyProgressBar)) {

        getContentResolver().insert(); //TODO insert new items
    }
}
