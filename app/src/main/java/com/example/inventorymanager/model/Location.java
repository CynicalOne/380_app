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
    
    public ArrayList<Item> items;

    // Default constructor
    public Location() {

    }

    public Location(String locationName, String address) {
        this.locationName = locationName;
        this.address = address;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public ImageView getLocationPic() {
        return locationPic;
    }

    public void setLocationPic(ImageView locationPic) {
        this.locationPic = locationPic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}