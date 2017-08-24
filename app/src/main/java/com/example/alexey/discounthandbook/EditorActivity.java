package com.example.alexey.discounthandbook;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
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

    public void addCardHolder() {


    }

}
