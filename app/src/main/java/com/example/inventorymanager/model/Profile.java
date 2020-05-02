package com.example.inventorymanager.model;

import android.location.Location;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class Profile implements Serializable
{
    public String profileName;
    public int id;
    public String businessOrPersonal;
    //public boolean isBusiness;
    public ImageView profilePic;
    private boolean isSelected = false;


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

    public Profile() {
    }

    public Profile(String profileName, String businessOrPersonal, int key){
        this.profileName = profileName;
        this.businessOrPersonal = businessOrPersonal;
        this.id = key;
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