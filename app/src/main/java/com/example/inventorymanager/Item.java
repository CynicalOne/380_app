package com.example.inventorymanager;

import android.widget.ImageView;

import java.util.Date;

public class Item {

    // fields for item
    String itemName;
    int key;
    ImageView image;
    ImageView receipt;
    int quantity;
    double price;
    Date purchaseDate;
    String description;

    //TODO: all these methods


    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public void setReceipt(ImageView receipt) {
        this.receipt = receipt;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
