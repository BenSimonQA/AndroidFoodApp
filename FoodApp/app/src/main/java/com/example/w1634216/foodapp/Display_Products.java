//w1634216 Benjamin Simon
package com.example.w1634216.foodapp;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Display_Products extends AppCompatActivity {

    DataBaseHelper myDb;
    ListView userList;
    Button addButton;
    ArrayList<String> theList;
    ArrayList<String> allFood;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display__products);

        userList = (ListView) findViewById(R.id.users_list);
        addButton = (Button) findViewById(R.id.button2);
        myDb = new DataBaseHelper(this);

        theList = new ArrayList<>();
        allFood = new ArrayList<>();
        final Cursor data = myDb.getNameList();

        //Displays list
        if(data.getCount() == 0){
            Toast.makeText(Display_Products.this,"Empty Database",Toast.LENGTH_LONG).show();
        }
        else{
            userList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            //Individually gets food
            while(data.moveToNext()){
                theList.add(data.getString(1));
                Collections.sort(theList);
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, theList);

                userList.setAdapter(listAdapter);
            }
            //Checks the list
            userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Cursor data = myDb.getNameList();

                    String kitFood = ((TextView) view).getText().toString();
                    if(allFood.contains(kitFood)){
                        allFood.remove(kitFood);
                    }
                    else{
                        allFood.add(kitFood);
                    }
                }
            });
            //Adds food to kitchen
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(Display_Products.this,"Added to kitchen",Toast.LENGTH_LONG).show();
                    SQLiteDatabase db = myDb.getWritableDatabase();
                    for(int i = 0; i<theList.size();i++){
                        if(userList.isItemChecked(i)){
                            db.execSQL("UPDATE " + DataBaseHelper.TABLE_NAME + " SET " + DataBaseHelper.COL_6 + "='Available' WHERE " + (i+1) + "=" +DataBaseHelper.COL_1);
                        }
                    }
                }
            });
        }
    }
}