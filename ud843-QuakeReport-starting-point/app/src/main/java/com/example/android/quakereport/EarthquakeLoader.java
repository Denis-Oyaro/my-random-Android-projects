package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by OYARO on 07/01/2018.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    /** Tag for log messages */
    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    /** Query URL */
    private String mUrl;

    public EarthquakeLoader(Context context,String url) {
        super(context);
        mUrl  = url;
    }

    @Override
    public List<Earthquake> loadInBackground() {
        Log.v(LOG_TAG,"Here in loadInBackground");
        // list of earthquakes.
        List<Earthquake> earthquakes = QueryUtils.extractEarthquakes(mUrl);

        return earthquakes;
    }

    @Override
    protected void onStartLoading() {
        Log.v(LOG_TAG,"Here in onStartloading");
        forceLoad();
    }
}
