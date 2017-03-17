package com.android.mor_arye.android5777_8159_8300.Model.DataSource;

import android.content.ContentValues;
import android.location.Address;
import android.os.AsyncTask;
import android.util.Log;

import com.android.mor_arye.android5777_8159_8300.Model.Backend.IDSManager;
import com.android.mor_arye.android5777_8159_8300.Model.Entities.Business;
import com.android.mor_arye.android5777_8159_8300.Model.Entities.Recreation;
import com.android.mor_arye.android5777_8159_8300.Model.Entities.TypeOfRecreation;
import com.android.mor_arye.android5777_8159_8300.Model.Entities.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import static com.android.mor_arye.android5777_8159_8300.Model.Backend.CustomContentProvider.CP_TAG;

/**
 * Created by mor on 04 פברואר 2017.
 */

public class MySQLDBManager implements IDSManager {

    String BusinessLastDateUpdated ="";
    String AttractionLastDateUpdated = "";

    private boolean usersUpdates = false;
    private boolean businessesUpdates = false;
    private boolean recreationsUpdates = false;


    private final String WEB_URL = "http://rothkoff.vlab.jct.ac.il/";

    private static String GET(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        if (con.getResponseCode() == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // print result
            return response.toString();
        } else {
            return "";
        }
    }

    private static String POST(String url, ContentValues params) throws IOException {
        StringBuilder postData = new StringBuilder();
        for (String param : params.keySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param, "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(String.valueOf(params.get(param))), "UTF-8"));
        }
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(postData.toString().getBytes("UTF-8"));
        os.flush();
        os.close();
        // For POST only - END
        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else return "";
    }


    @Override
    public void insertUser(ContentValues newUser) {
        try {
            String results = POST(WEB_URL + "addUser.php", newUser);
            if (results.equals("")) {
                throw new Exception("An error occurred on the server's side");
            }
            if (results.substring(0, 5).equalsIgnoreCase("error")) {
                throw new Exception(results.substring(5));
            }
            usersUpdates = true;
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                    String currentTime= df.format(new Date());

                    ContentValues dateNow = new ContentValues();
                    dateNow.put("TimeUsers",currentTime);

                    String results = POST(WEB_URL + "updateTimeUsers.php", dateNow);

                    if (results.equals("")) {
                        throw new Exception("An error occurred on the server's side");
                    }
                    if (results.substring(0, 5).equalsIgnoreCase("error")) {
                        throw new Exception(results.substring(5));
                    }
                }
                catch (Exception e) {
                    throw new IllegalArgumentException(e.getMessage());
                }
                return null;
            }
        }.execute();
    }

    @Override
    public void insertBusiness(ContentValues newBusiness) {
        try {
            String results = POST(WEB_URL + "addBusiness.php", newBusiness);
            if (results.equals("")) {
                throw new Exception("An error occurred on the server's side");
            }
            if (results.substring(0, 5).equalsIgnoreCase("error")) {
                throw new Exception(results.substring(5));
            }
            businessesUpdates = true;
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                    String currentTime= df.format(new Date());

                    ContentValues dateNow = new ContentValues();
                    dateNow.put("TimeBusiness",currentTime);

                    String results = POST(WEB_URL + "updateTimeBusiness.php", dateNow);

                    Log.d(CP_TAG, "B1 update Time in insert Business " + currentTime);

                    if (results.equals("")) {
                        throw new Exception("An error occurred on the server's side");
                    }
                    if (results.substring(0, 5).equalsIgnoreCase("error")) {
                        throw new Exception(results.substring(5));
                    }
                }
                catch (Exception e) {
                    throw new IllegalArgumentException(e.getMessage());
                }
                return null;
            }
        }.execute();
    }

    @Override
    public void insertRecreation(ContentValues newRecreation) {
        try {
            String results = POST(WEB_URL + "addRecreation.php", newRecreation);
            if (results.equals("")) {
                throw new Exception("An error occurred on the server's side");
            }
            if (results.substring(0, 5).equalsIgnoreCase("error")) {
                throw new Exception(results.substring(5));
            }
            recreationsUpdates = true;
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            String currentTime= df.format(new Date());

            ContentValues dateNow = new ContentValues();
            dateNow.put("TimeRecreation",currentTime);

            String results = POST(WEB_URL + "updateTimeRecreation.php", dateNow);

            Log.d(CP_TAG, "R1 update Time in insert Recreation " + currentTime);

            if (results.equals("")) {
                throw new Exception("An error occurred on the server's side");
            }
            if (results.substring(0, 5).equalsIgnoreCase("error")) {
                throw new Exception(results.substring(5));
            }
        }
        catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
                return null;
            }
        }.execute();
    }

    @Override
    public boolean checkNewInBusiness() {
        try {
            JSONArray array = new JSONObject(GET(WEB_URL + "getChangesTime.php")).getJSONArray("ChangesTime");
            JSONObject updateTable=array.getJSONObject(0);
            if (!BusinessLastDateUpdated.equals(updateTable.getString("business")))
            {
                                                    Log.d(CP_TAG,"B2 " + BusinessLastDateUpdated + " ! " + updateTable.getString("business"));
                BusinessLastDateUpdated = updateTable.getString("business");
                return true;
            }
            return false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkNewRecreation() {
        try {
            JSONArray array = new JSONObject(GET(WEB_URL + "getChangesTime.php")).getJSONArray("ChangesTime");
            JSONObject updateTable=array.getJSONObject(0);
            if (!AttractionLastDateUpdated.equals(updateTable.getString("recreation")))
            {                                           Log.d(CP_TAG, "R2 " + AttractionLastDateUpdated + " ! " + updateTable.getString("recreation"));
                AttractionLastDateUpdated = updateTable.getString("recreation");
                return true;
            }
            return false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Collection<User> getAllUsers() throws Exception{
        List<User> usersList = new ArrayList<>();
        JSONArray array = new JSONObject(GET(WEB_URL + "getUser.php")).getJSONArray("User");
        for (int i = 0; i < array.length(); i++) {
            final JSONObject userJson = array.getJSONObject(i);

            usersList.add(new User(
                    userJson.getString("nameUser"),
                    userJson.getString("password")
            ));
        }

        return usersList;
    }

    @Override
    public Collection<Business> getAllBusinesses() throws Exception {

        List<Business> businessesList = new ArrayList<>();
        JSONArray array = new JSONObject(GET(WEB_URL + "getBusiness.php")).getJSONArray("Businesses");
        for (int i = 0; i < array.length(); i++) {
            final JSONObject businessJson = array.getJSONObject(i);

            businessesList.add(new Business(
                    businessJson.getInt("idBusiness"),
                    businessJson.getString("nameBusiness"),
                    new Address(new Locale(businessJson.getString("addressBusiness"))),
                    businessJson.getString("phoneNumber"),
                    businessJson.getString("emailAddress"),
                    businessJson.getString("websiteLink")
            ));
        }

        return businessesList;
    }

    @Override
    public Collection<Recreation> getAllRecreations() throws Exception {
        List<Recreation> RecreationsList = new ArrayList<>();
        JSONArray array = new JSONObject(GET(WEB_URL + "getRecreation.php")).getJSONArray("Recreations");
        for (int i = 0; i < array.length(); i++) {
            final JSONObject recreationsJson = array.getJSONObject(i);

            String dateB = recreationsJson.getString("dateOfBeginning");
            String dateE = recreationsJson.getString("dateOfEnding");

            RecreationsList.add(new Recreation(
                    TypeOfRecreation.valueOf(recreationsJson.getString("typeOfRecreation")),
                    recreationsJson.getString("nameOfCountry"),

                    new GregorianCalendar(
                            new Integer(dateB.substring(6, 10)),
                            new Integer(dateB.substring(3, 5)),
                            new Integer(dateB.substring(0, 2))),
                    new GregorianCalendar(
                            new Integer(dateE.substring(6, 10)),
                            new Integer(dateE.substring(3, 5)),
                            new Integer(dateE.substring(0, 2))),


                    recreationsJson.getDouble("price"),
                    recreationsJson.getString("description"),
                    recreationsJson.getInt("idBusiness")
            ));
        }
        return RecreationsList;
    }
}

