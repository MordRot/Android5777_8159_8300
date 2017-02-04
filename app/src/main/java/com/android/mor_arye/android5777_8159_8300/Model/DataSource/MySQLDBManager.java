package com.android.mor_arye.android5777_8159_8300.Model.DataSource;

import android.content.ContentValues;
import android.location.Address;

import com.android.mor_arye.android5777_8159_8300.Model.Backend.IDSManager;
import com.android.mor_arye.android5777_8159_8300.Model.Entities.Business;
import com.android.mor_arye.android5777_8159_8300.Model.Entities.Recreation;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

/**
 * Created by mor on 04 פברואר 2017.
 */

public class MySQLDBManager implements IDSManager {
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
            return response.toString();    }
        else {
            return "";
        }
    }


    private static String POST(String url, ContentValues params) throws IOException {
        StringBuilder postData = new StringBuilder();
        for (String param : params.keySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param, "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(String.valueOf(params.get(param))),"UTF-8"));
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
        }
        else return "";
    }


    @Override
    public void insertUser(ContentValues newUser) {

    }

    @Override
    public void insertBusiness(ContentValues newBusiness) {

    }

    @Override
    public void insertRecreation(ContentValues newRecreation) {

    }

    @Override
    public boolean checkNewInBusiness() {
        return false;
    }

    @Override
    public boolean checkNewRecreation() {
        return false;
    }

    @Override
    public Collection<User> getAllUsers() {
        return null;
    }

    @Override
    public Collection<Business> getAllBusiness() throws Exception {

        List<Business> businessesList = new ArrayList<>();
        JSONArray array = new JSONObject(GET(WEB_URL + "/getBusiness.php")).getJSONArray("agencies");
        for (int i = 0; i < array.length(); i++) {
            final JSONObject businesJson = array.getJSONObject(i);
                                                                            // TODO something about the ID.
            businessesList.add(new Business(
                    businesJson.getString("nameBusiness"),
                    new Address(new Locale(businesJson.getString("addressBusiness"))),
                    businesJson.getString("phoneNumber"),
                    businesJson.getString("emailAddress"),
                    businesJson.getString("websiteLink")
            ));
        }

        return businessesList;
    }

    @Override
    public Collection<Recreation> getAllRecreation() {
        return null;
    }
}
