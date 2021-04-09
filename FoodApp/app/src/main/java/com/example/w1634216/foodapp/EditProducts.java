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
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class EditProducts extends AppCompatActivity {

    DataBaseHelper myDb;
    ListView userList;
    EditText editName, editPrice, editWeight, editDescription;
    int place;
    Cursor data;
    String pName, pPrice, pWeight, pDescription;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_products);

        userList = (ListView) findViewById(R.id.editList);
        myDb = new DataBaseHelper(this);

        ArrayList<String> theList = new ArrayList<>();
        final Cursor data = myDb.getNameList();

        //Displays list
        if(data.getCount() == 0){
            Toast.makeText(EditProducts.this,"Empty Database",Toast.LENGTH_LONG).show();
        }
        else{
            while(data.moveToNext()){
                userList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

                theList.add(data.getString(1));
                Collections.sort(theList);
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);

                userList.setAdapter(listAdapter);
            }
            //Will display details of food clicked
            userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    setContentView(R.layout.individual_edit);

                    update = (Button) findViewById(R.id.button4);

                    editName = (EditText) findViewById(R.id.name_input2);
                    editWeight = (EditText) findViewById(R.id.weight_input2);
                    editPrice = (EditText) findViewById(R.id.price_input2);
                    editDescription = (EditText) findViewById(R.id.description_input2);
                    place = position;
                    ShowProducts(position);

                    pName = editName.getText().toString();
                    pWeight = editWeight.getText().toString();
                    pPrice = editPrice.getText().toString();
                    pDescription = editDescription.getText().toString();

                    //Will update any changes
                    update.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(EditProducts.this,"Update",Toast.LENGTH_LONG).show();
                            SQLiteDatabase db = myDb.getWritableDatabase();

                            String foodName ="UPDATE " + myDb.TABLE_NAME+" SET "+myDb.COL_2+" = '"+editName.getText().toString()+"' WHERE "+myDb.COL_1+" = '"+(place+1)+"'"+" AND "+myDb.COL_2+" = '"+pName+"'";
                            db.execSQL(foodName);
                            String foodWeight ="UPDATE " + myDb.TABLE_NAME+" SET "+myDb.COL_3+" = '"+editWeight.getText().toString()+"' WHERE "+myDb.COL_1+" = '"+(place+1)+"'"+" AND "+myDb.COL_3+" = '"+pWeight+"'";
                            db.execSQL(foodWeight);
                            String foodPrice ="UPDATE " + myDb.TABLE_NAME+" SET "+myDb.COL_4+" = '"+editPrice.getText().toString()+"' WHERE "+myDb.COL_1+" = '"+(place+1)+"'"+" AND "+myDb.COL_4+" = '"+pPrice+"'";
                            db.execSQL(foodPrice);
                            String foodDescription ="UPDATE " + myDb.TABLE_NAME+" SET "+myDb.COL_5+" = '"+editDescription.getText().toString()+"' WHERE "+myDb.COL_1+" = '"+(place+1)+"'"+" AND "+myDb.COL_5+" = '"+pDescription+"'";
                            db.execSQL(foodDescription);
                        }
                    });
                }
            });
        }
    }

    //Shows product that has been clicked
    protected void  ShowProducts(int id){
        SQLiteDatabase db = myDb.getWritableDatabase();
        String selectQuery = "SELECT * FROM food_table WHERE ID =" + (place+1);
        Cursor data = db.rawQuery(selectQuery,null);

        if(data.moveToFirst()){
            editName.setText(data.getString(1));
            editWeight.setText(data.getString(2));
            editPrice.setText(data.getString(3));
            editDescription.setText(data.getString(4));

        }
    }

}
