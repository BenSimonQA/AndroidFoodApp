//w1634216 Benjamin Simon
package com.example.w1634216.foodapp;

import android.database.Cursor;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class SearchProducts extends AppCompatActivity {

    DataBaseHelper myDb;
    ListView userList;

    ArrayList<String> theList;
    ArrayList<String> listDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_products);

        userList = (ListView) findViewById(R.id.users_listSearch);
        myDb = new DataBaseHelper(this);

        theList = new ArrayList<>();
        listDes = new ArrayList<>();
        final Cursor data = myDb.getNameList();

        if(data.getCount() == 0){
            Toast.makeText(SearchProducts.this,"Empty Database",Toast.LENGTH_LONG).show();
        }
        else{
            while(data.moveToNext()){
                userList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

                theList.add(data.getString(1));
                listDes.add(data.getString(4));
                Collections.sort(theList);
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);

                userList.setAdapter(listAdapter);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<String> usersList = new ArrayList<>();

                //Shows all search available, depending on search
                for(String user : theList){
                    if(user.toLowerCase().contains(newText.toLowerCase())){
                        usersList.add(user);
                    }
                }
                for(String user1 : listDes){
                    if(user1.toLowerCase().contains(newText.toLowerCase())){
                        usersList.add(user1);
                    }
                }


                ArrayAdapter<String> adapter = new ArrayAdapter<String>(SearchProducts.this, android.R.layout.simple_expandable_list_item_1, usersList);
                userList.setAdapter(adapter);

                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
