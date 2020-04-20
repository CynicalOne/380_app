package com.example.inventorymanager.model;

import android.location.Location;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Profile
{
    public String profileName;
    public int key;
    public String businessOrPersonal;
    public boolean isBusiness;
    public ImageView profilePic;
    private boolean isSelected = false;


    public ArrayList Locations = new ArrayList<Location>();

    //constructor
    public Profile() {
        this.profileName = "Name";
        this.key = -1;
        this.businessOrPersonal = "Company";
        this.isBusiness = true;
        this.profilePic = null;
        this.Locations = new ArrayList<Location>();
    }

    public Profile(String profileName, String businessOrPersonal, int key){
        this.profileName = profileName;
        this.businessOrPersonal = businessOrPersonal;
        this.key = key;
    }

    public void setProfileName(String newName) {
        this.profileName = newName;
    }

    public void setProfilePic(ImageView newImage) {
        this.profilePic = newImage;
    }

    public void setBusinessOrPersonal(boolean business) {
        this.isBusiness = business;
    }

    public ArrayList<Location> getLocations() {
        return Locations;
    }

    public String getProfileName() {
        return profileName;
    }

    public int getKey() {
        return key;
    }

    public String getBusinessOrPersonal() {
        return businessOrPersonal;
    }

    public boolean isBusiness() {
        return isBusiness;
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

}