package com.example.inventorymanager;

import android.content.Context;
import android.os.Bundle;

import com.example.inventorymanager.adapters.ItemList_RecyclerViewAdapter;
import com.example.inventorymanager.model.Item;
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

public class ItemListActivity extends AppCompatActivity {

    private Context context;

    //For Recycler View
    private ArrayList<Item> itemList;
    private RecyclerView recyclerView;
    private ItemList_RecyclerViewAdapter adapter;

    //For add popup
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private Button saveButton;
    private EditText profileNameEditText;
    private EditText descriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Recycler View Connections
        recyclerView = findViewById(R.id.itemRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemList = new ArrayList<>();
        itemList.add(new Item("Soap", 1));
        itemList.add(new Item("Socks", 20));
        itemList.add(new Item("Neals", 5));
        itemList.add(new Item("Laptops", 15));
        itemList.add(new Item("Ants", 111));

        adapter = new ItemList_RecyclerViewAdapter(this, itemList);
        recyclerView.setAdapter(adapter);

    }

}
