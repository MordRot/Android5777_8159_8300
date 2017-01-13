package com.android.mor_arye.android5777_8159_8300.Controller;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.mor_arye.android5777_8159_8300.Model.Entities.TypeOfRecreation;
import com.android.mor_arye.android5777_8159_8300.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;

public class AddRecreationActivity extends AppCompatActivity {
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            EditText etDate;
            if (this.getTag().equals("BeginningDatePicker"))
                etDate = (EditText)getActivity().findViewById(R.id.etDateOfBeginning);
            else
                etDate = (EditText)getActivity().findViewById(R.id.etDateOfEnding);
            String date = new String(Integer.toString(day) +
                    '/' + Integer.toString(month + 1) + '/' + Integer.toString(year));
            etDate.setText(date);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recreation);
        setAllCountriesOnSpinner();
        Spinner recreations = (Spinner) findViewById(R.id.typeOfRecreation_spinner);
        recreations.setAdapter(new ArrayAdapter<TypeOfRecreation>(this, android.R.layout.simple_spinner_item, TypeOfRecreation.values()));
    }


    public void onAddRecreation(View view) {
        EditText type, country, dateOfBeginning, dateOfEnding, price, description, id;
        final ContentValues newRecreation = new ContentValues();
/*        type = (EditText) findViewById(R.id.etNameOfBusiness);
        country = (EditText) findViewById(R.id.etAddressOfBusiness);
        dateOfBeginning = (EditText) findViewById(R.id.etPhoneNumber);
        dateOfEnding = (EditText) findViewById(R.id.etEmail);
        price = (EditText) findViewById(R.id.etWebSite);
*/
        try {
            newRecreation.put("typeOfRecreation", "HOTEL");
            newRecreation.put("nameOfCountry", "israel");
            newRecreation.put("dateOfBeginning", "05/09/2016");
            newRecreation.put("dateOfEnding", "10/09/2016");
            newRecreation.put("price", 100.2);
            newRecreation.put("description", "we will have fun!");
            newRecreation.put("idBusiness", 123);

            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {

                    getContentResolver().insert(
                            Uri.parse("content://com.android.mor_arye.android5777_8159_8300/recreations"), newRecreation);

                    return null;
                }
            }.execute();
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog);
            TextView text = (TextView) dialog.findViewById(R.id.dialogText);
            text.setText("Recreation added successfuly!");
            Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
            // if button is clicked, close the dialog
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    Intent myIntent = new Intent(AddRecreationActivity.this, MainActivity.class);
                    startActivity(myIntent);
                }
            });

            dialog.show();
        }
        catch (Exception ex){
            throw ex;
        }
    }
    private void setAllBusinessesOnSpinner()
    {
        // TODO
    }
    private void setAllCountriesOnSpinner()
    {
        Locale[] locale = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        String country;
        for( Locale loc : locale ){
            country = loc.getDisplayCountry();
            if( country.length() > 0 && !countries.contains(country) ){
                countries.add( country );
            }
        }
        Collections.sort(countries, String.CASE_INSENSITIVE_ORDER);

        Spinner citizenship = (Spinner)findViewById(R.id.countries_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, countries);
        citizenship.setAdapter(adapter);
    }
    public void showBeginningDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "BeginningDatePicker");
    }
    public void showEndingDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "EndingDatePicker");
    }
}
