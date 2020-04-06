package com.example.inventorymanager.model;

import java.util.ArrayList;
import java.util.List;

import java.io.*;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SerializeData implements Serializable
{
    Gson gson = new Gson();
    Type profileListType = new TypeToken<ArrayList<Profile>>(){}.getType();
    //Type locationListType = new TypeToken<ArrayList<Location>>(){}.getType();
    //Type itemListType = new TypeToken<ArrayList<Item>>(){}.getType();

    public void serializeProfiles(ArrayList<Profile> profiles, Context context) {
        try {
            String jsonStr = gson.toJson(profiles);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("profiles.json", Context.MODE_PRIVATE));
            outputStreamWriter.write(jsonStr);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public ArrayList<Profile> deserializeProfiles() {
        String file = "profiles.json";
        String jsonStr = "";
        try {
            InputStream inputStream = context.openFileInput(file);

        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String receiveString = "";
            StringBuilder stringBuilder = new StringBuilder();

            while ((receiveString = bufferedReader.readLine()) != null) {
                stringBuilder.append("\n").append(receiveString);
            }

            inputStream.close();
            jsonStr = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } 
        catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        ArrayList<Profile> profiles = gson.fromJson(jsonStr, profileListType);
        return profiles;
    }
}