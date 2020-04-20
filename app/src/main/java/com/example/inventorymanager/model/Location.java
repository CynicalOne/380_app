package com.example.inventorymanager.model;

import android.widget.ImageView;

import com.example.inventorymanager.model.Item;

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

    public Location(String locationName, String address) {
        this.locationName = locationName;
        this.address = address;
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

    public String getLocationName() {
        return locationName;
    }

    public ImageView getLocationPic() {
        return locationPic;
    }

    public String getAddress() {
        return address;
    }

    public int getKey() {
        return key;
    }
}