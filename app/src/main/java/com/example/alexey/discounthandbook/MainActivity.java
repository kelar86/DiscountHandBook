package com.example.alexey.discounthandbook;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.alexey.discounthandbook.data.CardHolders;
import com.example.alexey.discounthandbook.data.CardHoldersDBHelper;

import java.util.ArrayList;

public class MainActivity extends Activity {
    final int MENU_EDIT = 0;
    final int MENU_DELETE = 1;

    ListView clientList;
    SimpleCursorAdapter mClientAdapter;
    CardHoldersDBHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clientList = (ListView)findViewById(R.id.list);

        dbHelper = new CardHoldersDBHelper(getApplicationContext());

        registerForContextMenu(clientList);
        clientList.setOnItemClickListener(listner);
    }

    @Override
    protected void onResume() {
        super.onResume();

        db = dbHelper.getReadableDatabase();

        cursor =  db.rawQuery("select * from "+ CardHolders.DB.TABLE_NAME, null);

        String[] mainActivityList = new String[] {CardHolders.DB.COLUMN_CARD_NUMBER, CardHolders.DB.COLUMN_NAME};;

        mClientAdapter = new SimpleCursorAdapter(this,
                android.R.layout.two_line_list_item,
                cursor,
                mainActivityList,
                new int[]{android.R.id.text1, android.R.id.text2}, 0);

        clientList.setAdapter(mClientAdapter);

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        db.close();
        cursor.close();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, MENU_EDIT, 0, R.string.edit_record);
        menu.add(0, MENU_DELETE, 0, R.string.delete_record);
    }



    @Override
    public boolean onContextItemSelected(MenuItem item) {
        long id = cursor.getLong(cursor.getColumnIndex("_id"));
        switch (item.getItemId()){
            case MENU_EDIT:
//                  Toast.makeText(getApplication(), " " + id, Toast.LENGTH_LONG).show();

                break;
            case MENU_DELETE:
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }



    AdapterView.OnItemClickListener listner = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

               int clientID = i;
//            Toast.makeText(getApplication(), " " + i, Toast.LENGTH_LONG).show();
                Intent intent = getIntent().setClass(getApplicationContext(), DiscountsActivity.class);
                intent.putExtra("clientID", clientID);
                startActivity(intent);
        }
    };


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, EditorActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}
