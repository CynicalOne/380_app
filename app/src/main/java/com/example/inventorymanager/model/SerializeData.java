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
    Type PairType = new TypeToken<Pair>(){}.getType();

    public static String serializeLocationAndItems(Location location) {
        String locationJSONStr, itemsJSONstr;
        // Converts the location (without the Items ArrayList) into JSON format
        locationJSONStr = gson.toJson(location);
        // Converts the Location's Items ArrayList into JSON format
        itemsJSONstr = gson.toJson(location.Items);
        // Create Pair object
        Pair PairToSerialize = new Pair(locationJSONStr, itemsJSONstr);
        // Return one string that can be stored in SQLite
        return gson.toJson(PairToSerialize);
    }

    public static Location deserializeLocationAndItems(String pairJSONStr) {
        Pair pair = gson.fromJson(pairJSONStr, PairType);
        Location location = gson.fromJson(pair.serializedLocation, LocationType);
        location.Items = gson.fromJson(pair.serializedItems, itemListType);
        return location;
    }
}