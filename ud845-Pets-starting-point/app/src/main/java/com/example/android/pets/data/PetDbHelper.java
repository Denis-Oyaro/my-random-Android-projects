package com.example.android.pets.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.pets.data.PetContract.PetEntry;
/**
 * Created by OYARO on 11/01/2018.
 */

public class PetDbHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME ="shelter.db";
    private static final int DATABASE_VERSION = 1;

    public PetDbHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_PETS_TABLE = "CREATE TABLE " + PetEntry.TABLE_NAME + "("
                + PetEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PetEntry.COLUMN_PET_NAME + " TEXT NOT NULL, "
                + PetEntry.COLUMN_PET_BREED + " TEXT, "
                + PetEntry.COLUMN_PET_GENDER + " INTEGER NOT NULL, "
                + PetEntry.COLUMN_PET_WEIGHT + " INTEGER NOT NULL DEFAULT 0);";

        Log.v("PetsDbHelper",SQL_CREATE_PETS_TABLE);
        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
