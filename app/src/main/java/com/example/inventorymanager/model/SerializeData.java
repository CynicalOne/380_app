package com.example.inventorymanager.model;

import java.util.ArrayList;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SerializeData
{
    Gson gson = new Gson();
    // Type profileListType = new TypeToken<ArrayList<Profile>>(){}.getType();
    // Type locationListType = new TypeToken<ArrayList<Location>>(){}.getType();
    Type itemListType = new TypeToken<ArrayList<Item>>(){}.getType();
    Type LocationType = new TypeToken<Location>(){}.getType();

    public Pair serializeLocationAndItems(Location location) {
        String locationJSONStr, itemsJSONstr;
        // Converts the location (without the Items ArrayList) into JSON format
        locationJSONStr = gson.toJson(location);
        // Converts the Location's Items ArrayList into JSON format
        itemsJSONstr = gson.toJson(location.items);
        return new Pair(locationJSONStr, itemsJSONstr);
    }

    public Location deserializeLocationAndItems(Pair pairStr) {
        Location location = gson.fromJson(pairStr.serializedLocation, LocationType);
        location.items = gson.fromJson(pairStr.serializedItems, itemListType);
        return location;
    }
}