package com.example.inventorymanager.model;

import android.widget.Button;

import java.util.ArrayList;

public class HomeView implements ListEdit<Profile>
{
    public Button editButton;
    public Button addButton;
    public boolean inEditMode;
    final String APPNAME = "Trackoholic";

    ArrayList<Profile> Profiles = new ArrayList<Profile>();
    

    public void goToProfileView(Profile profile) {
        
    }

    // methods from ListEdit interface
    public void deleteListElement(int index) {
        Profiles.remove(index);
    }

    public void deleteEntireList() {
        Profiles = new ArrayList<Profile>();
    }

    public void addListElement(Profile profileToAdd) {
        Profiles.add(profileToAdd);
    }

    public void clickAddButton() {

    }

    public void clickDeleteButton() {

    }

    public void clickEditButton() {

    }
}
