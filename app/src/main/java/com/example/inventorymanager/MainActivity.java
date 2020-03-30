package com.example.inventorymanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.inventorymanager.adapters.HomeViewAdapter;
import com.example.inventorymanager.model.Profile;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private HomeViewAdapter adapter;
    private ArrayList<Profile> profileArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //connect recyler view
        recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //test arraylist examples
        profileArrayList = new ArrayList<>();
        Profile david = new Profile("David", "Personal");
        profileArrayList.add(david);
        profileArrayList.add(new Profile("Neal", "Business"));
        profileArrayList.add(new Profile("Kyle", "Personal"));
        profileArrayList.add(new Profile("Josh", "Business"));
        profileArrayList.add(new Profile("Yasmeen", "Personal"));

        //init adapter and connect to arraylist
        adapter = new HomeViewAdapter(this, profileArrayList);
        recyclerView.setAdapter(adapter);
    }

    //TODO: Make clickable cells go to LocationViewActivity
    

    //TODO: Add button so user can create a profile (floating button?)
}
