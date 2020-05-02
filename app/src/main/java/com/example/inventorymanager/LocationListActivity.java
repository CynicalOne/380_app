package com.example.inventorymanager;

import android.os.Bundle;

import com.example.inventorymanager.Persistence.DatabaseHandler_Location;
import com.example.inventorymanager.adapters.LocationList_RecyclerViewAdapter;
import com.example.inventorymanager.model.Location;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class LocationListActivity extends AppCompatActivity {

    //For add popup
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private Button saveButton;
    private EditText profileNameEditText;
    private EditText descriptionEditText;

    //Recycler View
    private ArrayList<Location> locationList;
    private RecyclerView recyclerView;
    private LocationList_RecyclerViewAdapter adapter;

    //Database
    DatabaseHandler_Location databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Locations");
        setSupportActionBar(toolbar);

        databaseHandler = new DatabaseHandler_Location(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPopupDialog();
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });


        // Recycler View Connections
        recyclerView = findViewById(R.id.locationRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Test values for arraylist
        locationList = databaseHandler.getAllItems();

        //init adapter and connect to arraylist
//        adapter = new LocationList_RecyclerViewAdapter(getApplicationContext(), locationList);
//        recyclerView.setAdapter(adapter);

        adapter = new LocationList_RecyclerViewAdapter(this, locationList);
        recyclerView.setAdapter(adapter);
    }

    private void createPopupDialog() {
        builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.popup_addlocation_inlocationlist, null);

        profileNameEditText = view.findViewById(R.id.locationName);
        descriptionEditText = view.findViewById(R.id.descriptionLocationAddress);
        saveButton = view.findViewById(R.id.saveButtonLocation);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLocation(v);
                updateLocationList(databaseHandler.getAllItems());

            }
        });

        builder.setView(view);
        dialog = builder.create();
        dialog.show();

    }

    private void saveLocation(View v) {
        Location location = new Location();

        String newLocation = profileNameEditText.getText().toString().trim();
        String newAddress = descriptionEditText.getText().toString().trim();

        location.setLocationName(newLocation);
        location.setAddress(newAddress);

        databaseHandler.addLocation(location);

        Snackbar.make(v, "Location Saved", Snackbar.LENGTH_SHORT).show();

        //Close popup and delay screen to update
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 500);

        adapter.notifyDataSetChanged();
    }

    public void updateLocationList(List<Location> newlist) {
        locationList.clear();
        locationList.addAll(newlist);
    }

}
