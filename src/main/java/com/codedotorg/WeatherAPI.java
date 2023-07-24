package com.codedotorg;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherAPI {

    private static final String API_KEY = "06c9c20f4b33a1f5bbec77cc4e4b3171";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    
    public static String getWeather(String city) {
        try {
            URL apiURL = getAPIUrl(city, "weather");
            HttpURLConnection conn = (HttpURLConnection) apiURL.openConnection();
            conn.setRequestMethod("GET");

            StringBuffer response = getResponseFromAPI(conn);

            JSONObject obj = new JSONObject(response.toString());
            JSONObject main = obj.getJSONObject("main");

            double temp = main.getDouble("temp");
            return "Temperature in " + city + ": " + String.format("%.2f", temp) + "°F";
        } catch (Exception e) {
            return "Error retrieving weather for " + city;
        }
    }

    public static String getForecast(String city) {
        try {
            URL apiURL = getAPIUrl(city, "forecast");
            HttpURLConnection conn = (HttpURLConnection) apiURL.openConnection();
            conn.setRequestMethod("GET");

            StringBuffer response = getResponseFromAPI(conn);

            JSONObject obj = new JSONObject(response.toString());
            JSONArray list = obj.getJSONArray("list");

            
            return "";
        } catch (Exception e) {
            return "Error retrieving forecast for " + city;
        }
    }

    private static URL getAPIUrl(String city, String endpoint) {
        URL apiURL = null;

        String url = BASE_URL + endpoint + "?q=" + city;
        url += "&units=imperial&appid=" + API_KEY;

        try {
            URI uri = new URI(url);
            apiURL = uri.toURL();
        } catch (Exception e) {
            System.out.println("Error retrieving API URL.");
        }
        
        return apiURL;
    }

    private static StringBuffer getResponseFromAPI(HttpURLConnection conn) {
        String inputLine;
        StringBuffer response = new StringBuffer();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
        } catch (Exception e) {
            System.out.println("Error retrieving data from API.");
        }
        
        return response;
    }

}
