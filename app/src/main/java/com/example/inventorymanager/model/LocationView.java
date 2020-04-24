package com.example.inventorymanager.model;

import android.widget.Button;

public class LocationView implements ListEdit<Item>
{
    public Location location;
    public Button editButton;
    public Button addButton;
    public boolean inEditMode;

    public void goToItemView(Item item) {

    }
    
    // methods from ListEdit interface
    public void deleteListElement(int index) {
        location.items.remove(index);
    }

    public void deleteEntireList() {

    }

    public void addListElement(Item itemToAdd) {
        location.items.add(itemToAdd);
    }

    public void clickAddButton() {

    }

    @Override
    public void clickEditButton() {

    }

    public void clickDeleteButton() {

    }
}