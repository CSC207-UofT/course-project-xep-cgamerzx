package com.xepicgamerzx.hotelier.customer_activities.customer_search_activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class PlacesAPI {

    public ArrayList<DestinationItem> autoComplete(String input) {
        ArrayList<DestinationItem> arrayList = new ArrayList<>();
        HttpURLConnection connection = null;
        StringBuilder jsonResult = new StringBuilder();

        try {
            StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/autocomplete/json?");
            sb.append("input=" + input);
            sb.append("&key=AIzaSyDgbO256UmNGH74yVSq9NsRD4MyXltqGwQ");
            URL url = new URL(sb.toString());
            connection = (HttpURLConnection) url.openConnection();
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());

            int read;
            char[] buff = new char[1024];

            while ((read = inputStreamReader.read(buff)) != -1) {
                jsonResult.append(buff, 0, read);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonResult.toString());
            JSONArray predictions = jsonObject.getJSONArray("predictions");

            for (int i = 0; i < predictions.length(); i++) {
//                System.out.println(predictions.getJSONObject(i).getString("place_id"));
                // You can also get things like latitude, longitude using place_id on another api call...
                arrayList.add(new DestinationItem(predictions.getJSONObject(i).getString("description"),
                        predictions.getJSONObject(i).getString("place_id")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    public HashMap<String, Double> getLocation(String place_id) {
        String req = "https://maps.googleapis.com/maps/api/place/details/json?placeid=" + place_id +
                "&key=AIzaSyDgbO256UmNGH74yVSq9NsRD4MyXltqGwQ";
        HttpURLConnection connection = null;
        StringBuilder jsonResult = new StringBuilder();
        HashMap<String, Double> coords = new HashMap<>();

        try {
            URL url = new URL(req);
            connection = (HttpURLConnection) url.openConnection();
            InputStreamReader is = new InputStreamReader(connection.getInputStream());

            int read;
            char[] buff = new char[1024];

            while ((read = is.read(buff)) != -1) {
                jsonResult.append(buff, 0, read);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonResult.toString());
            JSONObject location = jsonObject.getJSONObject("result")
                    .getJSONObject("geometry")
                    .getJSONObject("location");
            Double latitude = location.getDouble("lat");
            Double longitude = location.getDouble("lng");

            coords.put("longitude", longitude);
            coords.put("latitude", latitude);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return coords;
    }

}
