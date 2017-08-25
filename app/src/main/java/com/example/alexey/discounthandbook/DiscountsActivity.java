package com.example.alexey.discounthandbook;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.alexey.discounthandbook.data.CardHolders;
import com.example.alexey.discounthandbook.data.CardHoldersDBHelper;

/**
 * Created by alexey on 23.08.17.
 */

public class DiscountsActivity extends Activity {

    SimpleCursorAdapter mDiscountAdapter;
    ListView discountList;

    CardHoldersDBHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;

    private long clientID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);


        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        clientID = intent.getLongExtra("clientID", 0);

        discountList = (ListView)findViewById(R.id.list);

        dbHelper = new CardHoldersDBHelper(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        db = dbHelper.getReadableDatabase();

        String q = "select * from "+ CardHolders.DB.TABLE_DISCOUNT_SIZE
                + " WHERE _id = " + clientID;
        cursor =  db.rawQuery(q, null);

        String[] discountActivityList = new String[] {
                CardHolders.DB.DISCOUNT_REASON,
                CardHolders.DB.COLUMN_DISCOUNT};


        mDiscountAdapter = new SimpleCursorAdapter(this,
                android.R.layout.two_line_list_item,
                cursor,
                discountActivityList,
                new int[]{android.R.id.text1, android.R.id.text2}, 0);

        discountList.setAdapter(mDiscountAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        db.close();
        cursor.close();
    }
}
