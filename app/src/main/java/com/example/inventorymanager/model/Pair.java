package com.example.inventorymanager.model;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class Pair
{
    public String[] pair = new String[2];

    public Pair (String strOne, String strTwo) {
        pair[0] = strOne;
        pair[1] = strTwo;
    }
}