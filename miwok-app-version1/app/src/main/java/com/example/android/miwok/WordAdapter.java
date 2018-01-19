package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by OYARO on 29/12/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int mbackgroundColorId;

    public WordAdapter(Context context,ArrayList<Word> objects,int backgroundColorId){
        super(context,0,objects);
        mbackgroundColorId = backgroundColorId;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        Word word = (Word) getItem(position);

        View listItemView = convertView;
        if(convertView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent,
                    false);
        }

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        miwokTextView.setText(word.getMiwokTranslation());
        defaultTextView.setText(word.getDefaultTranslation());

        if(word.hasImage()){
            imageView.setImageResource(word.getImageResourceId());
            imageView.setVisibility(View.VISIBLE);

        } else {
            imageView.setVisibility(View.GONE);
        }

        /*set background color of vertical linear layout*/
        View view = listItemView.findViewById(R.id.vert_layout);
        view.setBackgroundColor(ContextCompat.getColor(getContext(),mbackgroundColorId));

        return listItemView;
    }
}
