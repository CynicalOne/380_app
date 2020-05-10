package com.example.inventorymanager.model;

import android.location.Location;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class Profile implements Serializable
{
    private String profileName;
    public int id;
    private String businessOrPersonal;

    public ImageView profilePic;
    private boolean isSelected = false;

    ImageView picture;
    String imagePath;

    public String serialNo;
    private String model;
    private int quantity = 0;
    private double price;
    private String dateOfPurchase;


    public ArrayList Locations = new ArrayList<Location>();

    //constructor


    public Profile(String profileName, int id, String businessOrPersonal, ImageView profilePic, boolean isSelected, ArrayList locations) {
        this.profileName = profileName;
        this.id = id;
        this.businessOrPersonal = businessOrPersonal;
        this.profilePic = profilePic;
        this.isSelected = isSelected;
        Locations = locations;
    }

    public ImageView getPicture() {
        return picture;
    }

    public void setPicture(ImageView picture) {
        this.picture = picture;
    }

    public Profile() {
    }

    public Profile(String profileName, String businessOrPersonal, int key){
        this.profileName = profileName;
        this.businessOrPersonal = businessOrPersonal;
        this.id = key;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setProfileName(String newName) {
        this.profileName = newName;
    }

    public void setProfilePic(ImageView newImage) {
        this.profilePic = newImage;
    }


    public ArrayList<Location> getLocations() {
        return Locations;
    }

    public String getProfileName() {
        return profileName;
    }

    public int getId() {
        return id;
    }

    public String getBusinessOrPersonal() {
        return businessOrPersonal;
    }


    public ImageView getProfilePic() {
        return profilePic;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBusinessOrPersonal(String businessOrPersonal) {
        this.businessOrPersonal = businessOrPersonal;
    }
}