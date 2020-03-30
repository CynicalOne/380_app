import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SerializeData implements Serializable
{
    Gson gson = new Gson();
    Type profileListType = new TypeToken<ArrayList<Profile>>(){}.getType();
    Type locationListType = new TypeToken<ArrayList<Location>>(){}.getType();
    Type itemListType = new TypeToken<ArrayList<Item>>(){}.getType();

    public void serializeProfiles(ArrayList<Profile> profiles) {
        try {
            String jsonStr = gson.toJson(profiles);;
            FileWriter writer = new FileWriter("profiles.json");
            writer.write(jsonStr);
            writer.close();
        } 
        catch (IOException e) {
            System.out.println("exception " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Profile deserializeProfiles(String jsonStr) {
        ArrayList<Profile> profiles = gson.fromJson(jsonStr, profileListType);
        return
    }
}