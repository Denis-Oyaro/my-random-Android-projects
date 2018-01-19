package com.example.android.quakereport;

/**
 * Created by OYARO on 02/01/2018.
 */

public class Earthquake {

    private double magnitude;
    private String location;
    private long dateAndTime;
    private String url;

    public Earthquake (double magnitude,String location,long dateAndTime,String url){
        this.magnitude = magnitude;
        this.location = location;
        this.dateAndTime = dateAndTime;
        this.url = url;

    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public long getDateAndTime() {
        return dateAndTime;
    }

    public String getUrl() {
        return url;
    }
}
