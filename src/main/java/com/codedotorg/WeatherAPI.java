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
    
    /**
     * This method retrieves the current weather for a given city using the OpenWeatherMap API.
     * It takes a city name as a parameter and returns a string with the temperature in Fahrenheit.
     * If there is an error retrieving the weather, it returns an error message.
     *
     * @param city the name of the city to retrieve the weather for
     * @return a string with the temperature in Fahrenheit or an error message
     */
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

    /**
     * Returns the weather forecast for a given city using the OpenWeatherMap API.
     * 
     * @param city the name of the city to get the forecast for
     * @return a string containing the forecast for the next 5 days, with the date
     *         and temperature in Fahrenheit
     */
    public static String getForecast(String city) {
        try {
            URL apiURL = getAPIUrl(city, "forecast");
            HttpURLConnection conn = (HttpURLConnection) apiURL.openConnection();
            conn.setRequestMethod("GET");

            StringBuffer response = getResponseFromAPI(conn);

            JSONObject obj = new JSONObject(response.toString());
            JSONArray list = obj.getJSONArray("list");

            StringBuilder forecast = new StringBuilder();

            for (int i = 0; i < list.length(); i++) {
                JSONObject item = list.getJSONObject(i);
                JSONObject main = item.getJSONObject("main");
                double temp = main.getDouble("temp");
                String date = item.getString("dt_txt");
                forecast.append(date + ": " + String.format("%.2f", temp) + "°F\n");
            }
            
            return forecast.toString();
        } catch (Exception e) {
            return "Error retrieving forecast for " + city;
        }
    }

    /**
     * Returns the API URL for the given city and endpoint.
     * 
     * @param city the name of the city to get the weather data for
     * @param endpoint the API endpoint to use for retrieving the weather data
     * @return the URL object representing the API URL for the given city and endpoint
     */
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

    /**
     * This method takes an HttpURLConnection object and retrieves the response from the API.
     * It reads the input stream from the connection and appends each line to a StringBuffer.
     * If an exception occurs while reading the input, an error message is printed to the console.
     * 
     * @param conn the HttpURLConnection object to retrieve the response from
     * @return a StringBuffer containing the response from the API
     */
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
