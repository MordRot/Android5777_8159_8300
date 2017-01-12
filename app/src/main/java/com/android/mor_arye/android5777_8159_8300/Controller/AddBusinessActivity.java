package com.android.mor_arye.android5777_8159_8300.Controller;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.mor_arye.android5777_8159_8300.R;

public class AddBusinessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_business);
    }

    public void onAddBusiness(View view) {
        TextView name, address, phone, email, website;
        name = (TextView) findViewById(R.id.etNameOfBusiness);
        address = (TextView) findViewById(R.id.etAddressOfBusiness);
        phone = (TextView) findViewById(R.id.etPhoneNumber);
        email = (TextView) findViewById(R.id.etEmail);
        website = (TextView) findViewById(R.id.etWebSite);
        try {
            final ContentValues newBusiness = new ContentValues();
            newBusiness.put("nameBusiness", name.getText().toString());
            newBusiness.put("addressBusiness", address.getText().toString());
            newBusiness.put("phoneNumber", phone.getText().toString());
            newBusiness.put("emailAddress", email.getText().toString());
            newBusiness.put("websiteLink", website.getText().toString());

            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {

                    getContentResolver().insert(
                            Uri.parse("content://com.android.mor_arye.android5777_8159_8300/businesses"), newBusiness);

                    return null;
                }
            }.execute();
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog);
            TextView text = (TextView) dialog.findViewById(R.id.dialogText);
            text.setText("Business added successfuly!");
            Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
            // if button is clicked, close the dialog
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    Intent myIntent = new Intent(AddBusinessActivity.this, MainActivity.class);
                    startActivity(myIntent);
                }
            });

            dialog.show();
        }
        catch (Exception ex){
            throw ex;
        }
    }

}
