package com.example.alexey.discounthandbook.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by alexey on 23.08.17.
 */

public class Repository {

    private CardHoldersDBHelper dbHelper;
    private SQLiteDatabase db;

    public Repository(Context context) {
        dbHelper = new CardHoldersDBHelper(context);
        db = dbHelper.getReadableDatabase();
    }


    private ArrayList<String> getCardNumbersAndNames() {

        ArrayList<String> result = new ArrayList<>();
        String[] queryNumberAndName = {
                CardHolders.DB.COLUMN_CARD_NUMBER,
                CardHolders.DB.COLUMN_NAME
        };

        Cursor cursor = db.query(CardHolders.DB.TABLE_NAME,
                queryNumberAndName, null, null, null, null, null);

        try {
            int cardNumberIndex = cursor.getColumnIndex(CardHolders.DB.COLUMN_CARD_NUMBER);
            int columnNameIndex = cursor.getColumnIndex(CardHolders.DB.COLUMN_NAME);

            while (cursor.moveToNext()) {
                result.add(cursor.getString(cardNumberIndex) + " " + cursor.getString(columnNameIndex));
            }

        } finally {
            cursor.close();
        }
        return result;
    }

    private void addCardHolder(Client client) throws Exception {

        ContentValues values = new ContentValues();
        values.put(CardHolders.DB.COLUMN_NAME, client.getName());
        values.put(CardHolders.DB.COLUMN_CARD_NUMBER, client.getCardNumber());
        values.put(CardHolders.DB.DISCOUNT_REASON, client.getDiscountReason());
        values.put(CardHolders.DB.COLUMN_DISCOUNT, client.getDiscount());

        long newRowId = db.insert(CardHolders.DB.TABLE_NAME, null, values);
        if (newRowId == -1) {
            throw new Exception("Database insert error");
        }
    }


}
