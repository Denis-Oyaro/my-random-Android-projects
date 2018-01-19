package com.example.android.miwok;

/**
 * Created by OYARO on 29/12/2017.
 */

public class Word {

    private String miwokTranslation;
    private String defaultTranslation;
    private int imageResourceId = NO_IMAGE_PROVIDED;
    private int audioResourceId;

    private static final int NO_IMAGE_PROVIDED = 0;

    public Word(String defaultTranslation,String miwokTranslation,int audioResourceId) {
        this.miwokTranslation = miwokTranslation;
        this.defaultTranslation = defaultTranslation;
        this.audioResourceId = audioResourceId;
    }

    public Word(String defaultTranslation,String miwokTranslation,int imageResourceId,
                int audioResourceId) {
        this.miwokTranslation = miwokTranslation;
        this.defaultTranslation = defaultTranslation;
        this.imageResourceId = imageResourceId;
        this.audioResourceId = audioResourceId;
    }

    public String getMiwokTranslation() {
        return miwokTranslation;
    }

    public String getDefaultTranslation() {
        return defaultTranslation;
    }

    public int getImageResourceId(){
        return imageResourceId;
    }

    public boolean hasImage(){
        return  imageResourceId != NO_IMAGE_PROVIDED;
    }

    public int getAudioResourceId(){
        return audioResourceId;
    }

    @Override
    public String toString() {
        return "Word{" +
                "miwokTranslation='" + miwokTranslation + '\'' +
                ", defaultTranslation='" + defaultTranslation + '\'' +
                ", imageResourceId=" + imageResourceId +
                ", audioResourceId=" + audioResourceId +
                '}';
    }
}
