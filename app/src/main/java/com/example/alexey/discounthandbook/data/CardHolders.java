package com.example.alexey.discounthandbook.data;

import android.provider.BaseColumns;

/**
 * Created by alexey on 22.08.17.
 */

public class CardHolders {

    private CardHolders() {
    }

    public static final class DB implements BaseColumns {
        public final static String TABLE_NAME = "discounts";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_CARD_NUMBER = "card_number";
        public final static String COLUMN_NAME = "name";
        public final static String DISCOUNT_REASON = "discount_reason";


        public final static String TABLE_DISCOUNT_SIZE = "discount_size";
        public final static String COLUMN_DISCOUNT = "discounts";
    }
}