package com.example.inventorymanager;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Location
{
    public String locationName;
    public int key;
    public ImageView locationPic;
    public String address;
    
    public ArrayList<Item> Items;

    // Default constructor
    public Location() {
        this.locationName = "Name";
        this.key = -1;
        this.address = "Address";
        this.Items = new ArrayList<Item>();
    }

    public void setLocationName(String newName) {
        this.locationName = newName;
    }

    public void setLocationPic(ImageView newImage) {
        this.locationPic = newImage;
    }

    public void setAddress(String newAddress) {
        this.address = newAddress;
    }
}