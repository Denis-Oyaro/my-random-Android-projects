package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by OYARO on 02/01/2018.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Context context,List<Earthquake> objects) {
        super(context,0, objects);
    }

    @NonNull
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        View itemView = convertView;

        if(convertView == null) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,
                    parent, false);
        }

        Earthquake earthquake = getItem(position);

        TextView magTextView = (TextView) itemView.findViewById(R.id.mag_text_view);
        DecimalFormat formatter = new DecimalFormat("0.0");
        magTextView.setText(formatter.format(earthquake.getMagnitude()));

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(earthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        //location
        String fullLocation = earthquake.getLocation();
        String primaryLocation = "";
        String locationOffset = "";
        if (fullLocation.contains("km"))
        {
            int endIndx = fullLocation.indexOf("of") + 1;
            locationOffset = fullLocation.substring(0,endIndx+1);
            primaryLocation = fullLocation.substring(endIndx+2,fullLocation.length());

        } else {
            primaryLocation = fullLocation;
            locationOffset = "Near the";
        }
        TextView priLocTextView = (TextView) itemView.findViewById(R.id.primary_location_text_view);
        priLocTextView.setText(primaryLocation);

        TextView locOffsetTextView = (TextView) itemView.findViewById(R.id.location_offset_text_view);
        locOffsetTextView.setText(locationOffset);



        //set data and time
        Date dateTime = new Date(earthquake.getDateAndTime());
        //date
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy", Locale.CANADA);
        String dateText = dateFormatter.format(dateTime);
        //time
        SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a",Locale.CANADA);
        String timeText = timeFormatter.format(dateTime);

        TextView dateTextView = (TextView) itemView.findViewById(R.id.date_text_view);
        dateTextView.setText(dateText);

        TextView timeTextView = (TextView) itemView.findViewById(R.id.time_text_view);
        timeTextView.setText(timeText);

        return itemView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColor;

        switch((int) Math.floor(magnitude)){
            case 0:
            case 1:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude1);
                break;
            case 2:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude2);
                break;
            case 3:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude3);
                break;
            case 4:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude4);
                break;
            case 5:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude5);
                break;
            case 6:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude6);
                break;
            case 7:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude7);
                break;
            case 8:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude8);
                break;
            case 9:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude9);
                break;
            default:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude10plus);
                break;
        }
        return magnitudeColor;
    }
}
