package com.android.mor_arye.android5777_8159_8300.Controller;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.mor_arye.android5777_8159_8300.Model.Backend.CustomContentProvider;
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

    Spinner recreations, citizenship, spinnerBus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recreation);
        setAllCountriesOnSpinner();
        setAllBusinessesOnSpinner();
        recreations = (Spinner) findViewById(R.id.typeOfRecreation_spinner);
        recreations.setAdapter(new ArrayAdapter<TypeOfRecreation>(this, android.R.layout.simple_spinner_item, TypeOfRecreation.values()));
    }


    public void onAddRecreation(View view) {

        final ContentValues newRecreation = new ContentValues();

            try {
                newRecreation.put("typeOfRecreation", (recreations.getSelectedItem()).toString().toUpperCase());
                newRecreation.put("nameOfCountry", (citizenship.getSelectedItem()).toString());
                newRecreation.put("dateOfBeginning", (((EditText) findViewById(R.id.etDateOfEnding)).getText()).toString());
                newRecreation.put("dateOfEnding", (((EditText) findViewById(R.id.etDateOfEnding)).getText()).toString());
                newRecreation.put("price", (((EditText) findViewById(R.id.etPrice)).getText()).toString());
                newRecreation.put("description",  (((EditText) findViewById(R.id.etDescription)).getText()).toString());
                newRecreation.put("idBusiness", ((BusinessIdName) spinnerBus.getSelectedItem()).BusId);

                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {

                        getContentResolver().insert(
                                Uri.parse("content://com.android.mor_arye.android5777_8159_8300/recreations"), newRecreation);

                        return null;
                    }
                }.execute();
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Message");
                alertDialog.setMessage("Recreation added successfully!");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
            catch (Exception ex){
                Log.d(CustomContentProvider.CP_TAG, ex.getMessage());
                Toast.makeText(this, "Maybe you don't have any business.\nFirst add one", Toast.LENGTH_SHORT).show();
            }

    }

    public class BusinessIdName {
        int BusId;
        String busName;
        public BusinessIdName(int idBusiness, String busName) {
            BusId = idBusiness;
            this.busName = busName;}
        @Override
        public String toString(){
            return busName;
        }
    }

    private void setAllBusinessesOnSpinner()
    {
        new AsyncTask<Void, Void, Cursor>() {
            @Override
            protected Cursor doInBackground(Void... params) {
                Uri uriOfAllBusinesses = Uri.parse("content://com.android.mor_arye.android5777_8159_8300/businesses");
                Cursor result = getContentResolver().query(uriOfAllBusinesses, null, null, null, null, null);
                return result;
            }
            protected void onPostExecute(Cursor result) {

                final ArrayList<BusinessIdName> busList = new ArrayList<BusinessIdName>();
                if (result.moveToFirst())
                {
                    do
                    {
                        String businessName = result.getString(result.getColumnIndex("nameBusiness"));
                        int businessId = Integer.parseInt(result.getString(result.getColumnIndex("idBusiness")));
                        busList.add(new BusinessIdName(businessId, businessName));
                    }
                    while(result.moveToNext());
                }
                else   {
                    Toast.makeText(AddRecreationActivity.this, "First add a business", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(AddRecreationActivity.this, AddBusinessActivity.class);
                    startActivity(myIntent);

                }
                result.close();
                spinnerBus = (Spinner) findViewById(R.id.businessList_spinner);
                ArrayAdapter<BusinessIdName> adapterBus = new ArrayAdapter<BusinessIdName>(getApplicationContext(), android.R.layout.simple_spinner_item, busList);
                spinnerBus.setAdapter(adapterBus);
            }
        }.execute();
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

        citizenship = (Spinner)findViewById(R.id.countries_spinner);
        ArrayAdapter<String> countriesAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, countries);
        citizenship.setAdapter(countriesAdapter);
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