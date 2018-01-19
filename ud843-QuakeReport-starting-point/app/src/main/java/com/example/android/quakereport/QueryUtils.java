package com.example.android.quakereport;

import android.util.Log;
import android.util.MalformedJsonException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private static final String LOG_TAG = QueryUtils.class.getName();

    private QueryUtils() {
    }

    /**
     * Return a list of {@link Earthquake} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<Earthquake> extractEarthquakes(String urlString) {

        Log.v(LOG_TAG,"Here in extractEarthquakes");
        /*try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        ArrayList<Earthquake> earthquakes = null;
        try {
            URL url = createUrl(urlString);
            String jsonResponse = makeHttpRequest(url);
            earthquakes = getEarthquakesFromResponse(jsonResponse);
        }catch(IOException E){
            //
        }

        return earthquakes;
    }

    private static String makeHttpRequest(URL url)throws IOException {

        String jsonResponse = "";
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000 /* milliseconds */);
        urlConnection.setConnectTimeout(15000 /* milliseconds */);
        urlConnection.connect();
        InputStream inputStream = urlConnection.getInputStream();
        jsonResponse = readFromStream(inputStream);

        return jsonResponse;

    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder builder = new StringBuilder();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String line = reader.readLine();
        while(line!=null){
            builder.append(line);
            line = reader.readLine();
        }

        return builder.toString();
    }

    private static URL createUrl(String urlString) {
        URL url = null;
        try {
            url = new URL(urlString);
        }
        catch(MalformedURLException e){
            //
        }

        return url;
    }


    private static ArrayList<Earthquake> getEarthquakesFromResponse(String jsonResponse) {

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Earthquake> earthquakes = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
            // build up a list of Earthquake objects with the corresponding data.
            JSONObject rootObject = new JSONObject(jsonResponse);
            JSONArray features = rootObject.getJSONArray("features");
            for(int i=0;i<features.length();i++){
                JSONObject currEarthquakeObject = features.getJSONObject(i);
                JSONObject properties = currEarthquakeObject.getJSONObject("properties");
                earthquakes.add(new Earthquake(properties.getDouble("mag"),properties.getString("place"),
                        properties.getLong("time"),properties.getString("url")));
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

}