package com.example.inventorymanager.model;

import android.widget.ImageView;

import com.example.inventorymanager.model.Item;

import java.io.Serializable;
import java.util.ArrayList;

public class Location implements Serializable
{
    private String locationName;
    private int key;
    public ImageView locationPic;
    public String address;

    private String serialNo;
    private String model;
    private int quantity = 0;
    private double price;
    private String dateOfPurchase;
    
    public ArrayList<Item> items;

    // Default constructor
    public Location() {

    }

    public Location(String locationName, String address) {
        this.locationName = locationName;
        this.address = address;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(String dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
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