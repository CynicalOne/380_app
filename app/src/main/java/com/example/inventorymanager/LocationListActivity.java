package com.example.inventorymanager;

import android.os.Bundle;

import com.example.inventorymanager.adapters.LocationList_RecyclerViewAdapter;
import com.example.inventorymanager.model.Location;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Locations");
        setSupportActionBar(toolbar);

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
        locationList = new ArrayList<>();
        locationList.add(new Location("CSUN", "555 Nordhoff Ave"));
        locationList.add(new Location("UCLA", "12321 ventura blvd"));
        locationList.add(new Location("Strip club", "8347 Roscoe Blvd"));
        locationList.add(new Location("Home", "34322 Morrison St"));
        locationList.add(new Location("neals house", "999 woodley ave"));

        //init adapter and connect to arraylist
//        adapter = new LocationList_RecyclerViewAdapter(getApplicationContext(), locationList);
//        recyclerView.setAdapter(adapter);

        adapter = new LocationList_RecyclerViewAdapter(this, locationList);
        recyclerView.setAdapter(adapter);
    }

    private void createPopupDialog() {
        builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.popup_addlocation_inlocationlist, null);

        profileNameEditText = view.findViewById(R.id.profileName);
        descriptionEditText = view.findViewById(R.id.description);
        saveButton = view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!profileNameEditText.getText().toString().isEmpty() && descriptionEditText.getText().toString().isEmpty()) {
                    //save the item
                }
                else {
                    Snackbar.make(v, "Empty fields", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        builder.setView(view);
        dialog = builder.create();
        dialog.show();

    }

}
