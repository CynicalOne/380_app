package com.example.inventorymanager;

import android.content.Intent;
import android.os.Bundle;

import com.example.inventorymanager.adapters.HomeView_v2_RecyclerViewAdapter;
import com.example.inventorymanager.model.Location;
import com.example.inventorymanager.model.Profile;
import com.example.inventorymanager.model.SerializeData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.ArrayList;

public class HomeviewV2Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HomeView_v2_RecyclerViewAdapter adapter;

    private ArrayList<Profile> profileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeview_v2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Profiles");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "This will soon let user add a profile!!!",
                        Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        // Recycler View Connections

        recyclerView = findViewById(R.id.profilerecyclerview);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //test arraylist examples
        profileList = new ArrayList<>();
        profileList.add(new Profile("David", "Personal"));
        profileList.add(new Profile("Neal", "Business"));
        profileList.add(new Profile("Kyle", "Personal"));
        profileList.add(new Profile("Josh", "Business"));
        profileList.add(new Profile("Yasmeen", "Personal"));

        SerializeData cereal = new SerializeData();
        cereal.serializeProfiles(profileList, App.instance.getApplicationContext());

        //init adapter and connect to arraylist
        adapter = new HomeView_v2_RecyclerViewAdapter(this, profileList);
        recyclerView.setAdapter(adapter);
    }

}
