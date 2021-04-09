//w1634216 Benjamin Simon
package com.example.w1634216.foodapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterAcitvity extends AppCompatActivity {
    DataBaseHelper myDb;
    EditText editName, editPrice, editWeight, editDescription;
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_acitvity);
        myDb = new DataBaseHelper(this);

        editName = (EditText) findViewById(R.id.name_input);
        editWeight = (EditText) findViewById(R.id.weight_input);
        editPrice = (EditText) findViewById(R.id.price_input);
        editDescription = (EditText) findViewById(R.id.description_input);
        btnAddData = (Button) findViewById(R.id.button);

        AddData();
    }

    //Adds all data of new food
    public void AddData(){
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String availability = "Not Available";
                        boolean isInserted = myDb.insertData(editName.getText().toString(),editWeight.getText().toString(),editPrice.getText().toString(), editDescription.getText().toString(), availability);
                        if(isInserted = true){
                            Toast.makeText(RegisterAcitvity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(RegisterAcitvity.this,"Data Not Inserted",Toast.LENGTH_LONG).show();

                        }
                    }
                }
        );
    }
}
