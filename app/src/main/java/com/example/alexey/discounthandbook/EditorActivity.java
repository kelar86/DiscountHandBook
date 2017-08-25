package com.example.alexey.discounthandbook;


import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alexey.discounthandbook.data.CardHolders;
import com.example.alexey.discounthandbook.data.CardHoldersDBHelper;


/**
 * Created by alexey on 24.08.17.
 */

public class EditorActivity extends Activity {

    private Button saveBtn;

    CardHoldersDBHelper dbHelper;

    EditText cardNum;
    EditText name;
    EditText discountReason;
    EditText discount;
    SQLiteDatabase db;
    long clientID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_activity);
        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Context context = getApplication();
        dbHelper = new CardHoldersDBHelper(context);

        cardNum = (EditText)findViewById(R.id.editCardNum);
        name = (EditText)findViewById(R.id.editName);
        discountReason = (EditText)findViewById(R.id.editDiscountReason);
        discount = (EditText)findViewById(R.id.editDiscount);
        saveBtn = (Button) findViewById(R.id.saveBtn);

        Intent intent = getIntent();
        clientID = intent.getLongExtra("clientID", 0);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCardHolder();
                Toast.makeText(getApplication(), "ЗАПИСЬ УСПЕШНО ДОБАВЛЕНА!", Toast.LENGTH_LONG).show();
                startActivity(getIntent().setClass(getApplicationContext(), MainActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        db = dbHelper.getWritableDatabase();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                goHome();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public void addCardHolder() {
        ContentValues cvNames = new ContentValues();
        ContentValues cvDiscounts = new ContentValues();

        cvNames.put(CardHolders.DB.COLUMN_CARD_NUMBER, cardNum.getText().toString());
        cvNames.put(CardHolders.DB.COLUMN_NAME, name.getText().toString());

        cvDiscounts.put(CardHolders.DB.DISCOUNT_REASON, discountReason.getText().toString());
        cvDiscounts.put(CardHolders.DB.COLUMN_DISCOUNT, discount.getText().toString());


        if(clientID > 0){
            db.update(CardHolders.DB.TABLE_NAME, cvNames, CardHolders.DB._ID + "=" + String.valueOf(clientID), null);
            db.update(CardHolders.DB.TABLE_DISCOUNT_SIZE, cvDiscounts, CardHolders.DB._ID + "=" + String.valueOf(clientID), null);

        } else {
            db.insert(CardHolders.DB.TABLE_NAME, null, cvNames);
            db.insert(CardHolders.DB.TABLE_NAME, null, cvDiscounts);
        }

        goHome();


    }
    private void goHome(){

        db.close();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

}
