package com.example.inventorymanager.model;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
 
import java.io.*;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SerializeData
{
    Gson gson = new Gson();
    Type profileListType = new TypeToken<List<Profile>>(){}.getType();
    Type locationListType = new TypeToken<List<Location>>(){}.getType();
    Type itemListType = new TypeToken<List<Item>>(){}.getType();

    public void serializeProfiles(List<Profile> profiles, Context context) {
        File path = context.getFilesDir();
        try {
            for (Profile profile : profiles) {  
                String filename = "profile_" + Integer.toString(profile.getKey()) + ".json";
                File file = new File(path, filename);
                FileOutputStream stream = new FileOutputStream(file);
                String jsonStr = gson.toJson(profile);
                ArrayList<Location> tempLocList = profile.getLocations();
                jsonStr += gson.toJson(tempLocList);
                for (Location location : tempLocList) {
                    jsonStr += gson.toJson(location.Items);
                }
                stream.write(jsonStr.getBytes());
                Log.i(filename + ":", jsonStr);
                stream.close();
            }  
        } catch (FileNotFoundException e) {
            Log.e("HomeviewV2Activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("HomeviewV2Activity", "Can not read file: " + e.toString());
        }
    }

    public ArrayList<Profile> deserializeProfiles(Context context) {
        File path = context.getFilesDir();
        File file = new File(path, "profiles.json");
        int length = (int) file.length();
        byte[] bytes = new byte[length];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(bytes);
            in.close();
        } catch (FileNotFoundException e) {
            Log.e("HomeviewV2Activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("HomeviewV2Activity", "Can not read file: " + e.toString());
        }
        String jsonStr = new String(bytes);   
        ArrayList<Profile> profiles = gson.fromJson(jsonStr, profileListType);
        return profiles;
    }
}