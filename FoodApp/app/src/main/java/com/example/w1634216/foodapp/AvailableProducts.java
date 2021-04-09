//w1634216 Benjamin Simon
package com.example.w1634216.foodapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;

public class AvailableProducts extends AppCompatActivity {

    DataBaseHelper myDb;
    ListView userList;
    Button updateButton;
    ArrayList<String > theList;
    ArrayList<String> allFood;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_products);

        userList = (ListView) findViewById(R.id.available_list);
        updateButton = (Button) findViewById(R.id.button3);
        myDb = new DataBaseHelper(this);

        theList = new ArrayList<>();
        allFood = new ArrayList<>();
        final Cursor data = myDb.getEvents();

        //Display of Food in Kitchen

        if(data.getCount()==0){
            Toast.makeText(AvailableProducts.this,"Empty Database",Toast.LENGTH_LONG).show();
        }
        else{
            userList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            //Sorts out display
            while(data.moveToNext()){
                theList.add(data.getString(1));
                Collections.sort(theList);
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, theList);

                userList.setAdapter(listAdapter);
                for(int i =0; i<theList.size();i++){
                    userList.setItemChecked(i,true);
                }
            }
            //Checks all food
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
            //Remove from kitchen
            updateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(AvailableProducts.this,"Removed from kitchen",Toast.LENGTH_LONG).show();
                    SQLiteDatabase db = myDb.getWritableDatabase();
                    for(int i = 0; i<theList.size();i++){
                        if(!userList.isItemChecked(i)){
                            db.execSQL("UPDATE " + DataBaseHelper.TABLE_NAME + " SET " + DataBaseHelper.COL_6 + "='Not Available' WHERE " + (i+1) + "=" +DataBaseHelper.COL_1);
                        }
                    }
                }
            });
        }
    }
}
