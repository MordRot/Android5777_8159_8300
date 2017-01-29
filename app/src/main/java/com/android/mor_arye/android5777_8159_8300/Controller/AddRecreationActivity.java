package com.android.mor_arye.android5777_8159_8300.Controller;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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

    Spinner recreations;
    Spinner citizenship;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recreation);
        setAllCountriesOnSpinner();
//        setAllBusinessesOnSpinner();
        setAllBusinessesOnSpinner2();
        recreations = (Spinner) findViewById(R.id.typeOfRecreation_spinner);
        recreations.setAdapter(new ArrayAdapter<TypeOfRecreation>(this, android.R.layout.simple_spinner_item, TypeOfRecreation.values()));
    }


    public void onAddRecreation(View view) {

        final ContentValues newRecreation = new ContentValues();

            try {
                newRecreation.put("typeOfRecreation", (recreations.getSelectedItem()).toString());
                newRecreation.put("nameOfCountry", (citizenship.getSelectedItem()).toString());
                newRecreation.put("dateOfBeginning", (((EditText) findViewById(R.id.etDateOfEnding)).getText()).toString());
                newRecreation.put("dateOfEnding", (((EditText) findViewById(R.id.etDateOfEnding)).getText()).toString());
                newRecreation.put("price", (((EditText) findViewById(R.id.etPrice)).getText()).toString());
                newRecreation.put("description",  (((EditText) findViewById(R.id.etDescription)).getText()).toString());
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
                text.setText("Recreation added sucsessfuly!");
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


    public class businessIdName {
        int BusId;
        String busName;
        public businessIdName(int idBuisnes, String busName) {
            BusId = idBuisnes;
            this.busName = busName;}
        @Override
        public String toString(){
            return busName;
        }
    }

    Spinner spinnerBus;
    ArrayAdapter<businessIdName> adapterBus;
/*
    private void setAllBusinessesOnSpinner()
    {
        final ArrayList<businessIdName> busList = new ArrayList<businessIdName>();

        new AsyncTask<Void, Void, Cursor>() {
            @Override
            protected Cursor doInBackground(Void... params) {
//                Log.d(CustomContentProvider.CP_TAG, "inside doInBackground");
                Uri uriOfAllUsers = Uri.parse("content://com.android.mor_arye.android5777_8159_8300/businesses");
                Cursor result = getContentResolver().query(uriOfAllUsers, null, null, null, null, null);
                return result;
            }
            protected void onPostExecute(Cursor result) {
//                Log.d(CustomContentProvider.CP_TAG, "inside onPostExecute");
                if (result.moveToFirst())
                {
//                    Log.d(CustomContentProvider.CP_TAG, "inside onPostExecute and if (result.moveToFirst())");
                    do
                { String businessName = result.getString(result.getColumnIndex("nameBusiness"));
                    int businessId = Integer.parseInt(result.getString(result.getColumnIndex("idBusiness")));
                    busList.add(new businessIdName(businessId, businessName));
                }while(result.moveToNext()); } result.close();
            }
        }.execute();

        spinnerBus = (Spinner) findViewById(R.id.businessList_spinner);
        adapterBus = new ArrayAdapter<businessIdName>(this, android.R.layout.simple_spinner_item, busList)
        {
            @Override public View getDropDownView(int position, View convertView, ViewGroup parent) {
//                if (convertView == null) { convertView = View.inflate(AddRecreationActivity.this, android.R.layout.simple_spinner_item, null); }
                TextView label = new TextView(AddRecreationActivity.this);
                label.setText(busList.get(position).busName);
                return label;
            }
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) { convertView = View.inflate(AddRecreationActivity.this, android.R.layout.simple_spinner_item, null); }
                TextView label = new TextView(AddRecreationActivity.this);
                label.setText(busList.get(position).busName);
                return label;
            }
        };
        spinnerBus.setAdapter(adapterBus);

//        AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView parent, View businessSpinner, int position, long id)
//        {
//                Toast.makeText(AddRecreationActivity.this,"Click - selected" + adapterBus.getItem(position),Toast.LENGTH_SHORT).show();
//            }
//        };

        spinnerBus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                businessIdName business = adapterBus.getItem(position);
                Log.d(CustomContentProvider.CP_TAG, "inside onItemSelected");
//                spinnerBus.setSelection(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(CustomContentProvider.CP_TAG, "inside onNothingSelected");
            }
        });
    }
*/

    ArrayAdapter adapterBus2;
    private void setAllBusinessesOnSpinner2()
    {
        final ArrayList<businessIdName> busList = new ArrayList<businessIdName>();

        new AsyncTask<Void, Void, Cursor>() {
            @Override
            protected Cursor doInBackground(Void... params) {
//                Log.d(CustomContentProvider.CP_TAG, "inside doInBackground");
                Uri uriOfAllUsers = Uri.parse("content://com.android.mor_arye.android5777_8159_8300/businesses");
                Cursor result = getContentResolver().query(uriOfAllUsers, null, null, null, null, null);
                return result;
            }
            protected void onPostExecute(Cursor result) {
//                Log.d(CustomContentProvider.CP_TAG, "inside onPostExecute");
                if (result.moveToFirst())
                {
//                    Log.d(CustomContentProvider.CP_TAG, "inside onPostExecute and if (result.moveToFirst())");
                    do
                    { String businessName = result.getString(result.getColumnIndex("nameBusiness"));
                        int businessId = Integer.parseInt(result.getString(result.getColumnIndex("idBusiness")));
                        busList.add(new businessIdName(businessId, businessName));
                    }while(result.moveToNext()); } result.close();
            }
        }.execute();

        spinnerBus = (Spinner) findViewById(R.id.businessList_spinner);
        adapterBus2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, busList);
        spinnerBus.setAdapter(adapterBus2);


        spinnerBus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Log.d(CustomContentProvider.CP_TAG, "inside onNothingSelected");
//                businessIdName business = adapterBus2.getItem(position);
//                spinnerBus.setSelection(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

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