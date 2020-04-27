package com.example.inventorymanager.model;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class Pair
{
    public String serializedLocation;
    public String serializedItems;

    public Pair (String strOne, String strTwo) {
        this.serializedLocation = strOne;
        this.serializedItems = strTwo;
    }
}