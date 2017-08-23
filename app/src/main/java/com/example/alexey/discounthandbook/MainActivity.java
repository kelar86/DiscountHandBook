package com.example.alexey.discounthandbook;

import android.app.ListActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alexey.discounthandbook.data.CardHoldersDBHelper;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends ListActivity implements AdapterView.OnItemLongClickListener {

    final String[] clientRepository = new String[] { "Александр", "Алексей", "Екатерина", "Марина", "Олег"};
    private ArrayList<String> clientList = new ArrayList<>(Arrays.asList(clientRepository));

    private ArrayAdapter<String> mClientAdapter;
    private String client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mClientAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, clientList);

        setListAdapter(mClientAdapter);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (getListAdapter() == mClientAdapter) {
            client = (String) l.getItemAtPosition(position);
            Intent intent = new Intent(this, DiscountsActivity.class);
            intent.putExtra("client", client);
            startActivity(intent);

        }

    }


    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        return false;
    }



}
