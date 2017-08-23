package com.example.alexey.discounthandbook;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by alexey on 23.08.17.
 */

public class DiscountsActivity extends ListActivity implements AdapterView.OnItemLongClickListener {



    final String[] discountsRep = new String[] {"10%", "20%", "30%", "25%", "15%"};
    private ArrayAdapter<String> mDiscountAdapter;
    private String discout;
    private String client;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        client = intent.getStringExtra("client");
        mDiscountAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, discountsRep);


        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        setListAdapter(mDiscountAdapter);
        setContentView(R.layout.activity_discount);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        return false;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        discout = (String) l.getItemAtPosition(position);
        Toast.makeText(getBaseContext(), client + ": " + discout,
                Toast.LENGTH_LONG).show();

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
}
