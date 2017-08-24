package com.example.alexey.discounthandbook.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by alexey on 22.08.17.
 */

public class CardHoldersDBHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = CardHoldersDBHelper.class.getSimpleName();
    public static final String DATABASE_NAME = "cardholders.db";
    private static final int DATABASE_VERSION = 1;


    public CardHoldersDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_CARDHOLDERS_TABLE = "CREATE TABLE " + CardHolders.DB.TABLE_NAME + " ("
                + CardHolders.DB._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CardHolders.DB.COLUMN_CARD_NUMBER + " TEXT NOT NULL, "
                + CardHolders.DB.COLUMN_NAME + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_CARDHOLDERS_TABLE);

        String SQL_CREATE_DISCOUNT_SIZE = "CREATE TABLE "+ CardHolders.DB.TABLE_DISCOUNT_SIZE +" ("
                + CardHolders.DB._ID + " INTEGER REFERENCES " + CardHolders.DB.TABLE_NAME
                + " (" + CardHolders.DB._ID + "), "
                + CardHolders.DB.DISCOUNT_REASON + " TEXT, "
                + CardHolders.DB.COLUMN_DISCOUNT + " INTEGER NOT NULL DEFAULT 0);";

        db.execSQL(SQL_CREATE_DISCOUNT_SIZE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // TO DO ...
    }



}
