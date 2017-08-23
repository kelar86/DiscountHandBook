package com.example.alexey.discounthandbook.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alexey on 22.08.17.
 */

public class CardHoldersDBHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = CardHoldersDBHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "cardholders.db";
    private static final int DATABASE_VERSION = 1;


    public CardHoldersDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String SQL_CREATE_CARDHOLDERS_TABLE = "CREATE TABLE IF NOT EXIST " + CardHolders.DB.TABLE_NAME + " ("
                + CardHolders.DB._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CardHolders.DB.COLUMN_CARD_NUMBER + " TEXT NOT NULL, "
                + CardHolders.DB.COLUMN_NAME + " TEXT NOT NULL, "
                + CardHolders.DB.DISCOUNT_REASON + " TEXT NOT NULL, "
                + CardHolders.DB.COLUMN_DISCOUNT + " INTEGER NOT NULL, ";

        sqLiteDatabase.execSQL(SQL_CREATE_CARDHOLDERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // TO DO ...
    }


}
