package com.example.alexey.discounthandbook;

import android.app.ListActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {

    final String[] clientRepository = new String[] { "Александр", "Алексей", "Екатерина"};

    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, clientRepository);
        setListAdapter(mAdapter);
    }
}
