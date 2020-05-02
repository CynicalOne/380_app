package com.example.inventorymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.inventorymanager.model.Profile;

public class ViewBusinessItemsActivity extends AppCompatActivity {

    //Items are actually profiles lol

    TextView nameOfBusinessItem;
    TextView descriptionOfBusinessItem;

    // Getting item (actually its a profile) from recycler view
   // Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_business_items);

        //Get Profile from list
        Intent i = new Intent();
        Profile profile = (Profile) getIntent().getSerializableExtra("businessItemSent");

        // Connections
        nameOfBusinessItem = findViewById(R.id.nameOfBusinessItem);
        descriptionOfBusinessItem = findViewById(R.id.descriptionOfBusinessItem);

        // Set textview to match item
        nameOfBusinessItem.setText(profile.getProfileName());
        descriptionOfBusinessItem.setText(profile.getBusinessOrPersonal());

    }
}
