//w1634216 Benjamin Simon
package com.example.w1634216.foodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button register;
    private Button display;
    private Button availability;
    private Button edit;
    private Button search;
    private Button recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (Button) findViewById(R.id.register_button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });

        display = (Button) findViewById(R.id.display_button);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplay();
            }
        });

        edit = (Button) findViewById(R.id.edit_button);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEdit();
            }
        });

        search = (Button) findViewById(R.id.search_button);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearch();
            }
        });

        availability = (Button) findViewById(R.id.availability_button);
        availability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAvailable();
            }
        });
    }

    public void openRegister(){
        Intent intent = new Intent(this, RegisterAcitvity.class);
        startActivity(intent);
    }

    public void openDisplay(){
        Intent intent = new Intent(this, Display_Products.class);
        startActivity(intent);
    }

    public void openEdit(){
        Intent intent = new Intent(this, EditProducts.class);
        startActivity(intent);
    }

    public void openSearch(){
        Intent intent = new Intent(this,SearchProducts.class);
        startActivity(intent);
    }

    public void openAvailable(){
        Intent intent = new Intent(this, AvailableProducts.class);
        startActivity(intent);
    }
}
