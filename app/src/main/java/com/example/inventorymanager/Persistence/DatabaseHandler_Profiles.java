package com.example.inventorymanager.Persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.inventorymanager.Util.Constants;
import com.example.inventorymanager.model.Profile;

import java.util.ArrayList;

public class DatabaseHandler_Profiles extends SQLiteOpenHelper {

    private Context context;

    public DatabaseHandler_Profiles(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PROFILE_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "("
                + Constants.KEY_ID + " Integer PRIMARY KEY,"
                + Constants.KEY_PROFILE_NAME + " TEXT,"
                + Constants.KEY_DESCRIPTION + " TEXT);";

        db.execSQL(CREATE_PROFILE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(db);
    }

    //CRUD Operations

    public void addProfile(Profile profile) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.KEY_PROFILE_NAME, profile.getProfileName());
        contentValues.put(Constants.KEY_DESCRIPTION, profile.getBusinessOrPersonal());

        db.insert(Constants.TABLE_NAME, null, contentValues);
        Log.d("ProfilehandlerDB", "added profile: " +profile.getProfileName());
    }

    public Profile getProfile(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Constants.TABLE_NAME,
                new String[]{Constants.KEY_ID,
                        Constants.KEY_PROFILE_NAME,
                        Constants.KEY_DESCRIPTION},
                Constants.KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        Profile profile = new Profile();
        if(cursor != null) {
            profile.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
            profile.setProfileName(cursor.getString(cursor.getColumnIndex(Constants.KEY_PROFILE_NAME)));
            profile.setBusinessOrPersonal(cursor.getString(cursor.getColumnIndex(Constants.KEY_DESCRIPTION)));
        }


        return profile;
    }

    //get all items
    public ArrayList<Profile> getAllItems() {
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Profile> profilesList = new ArrayList<>();

        Cursor cursor = db.query(Constants.TABLE_NAME,
                new String[]{Constants.KEY_ID,
                Constants.KEY_PROFILE_NAME,
                Constants.KEY_DESCRIPTION},
                null, null, null, null, null);

        if(cursor.moveToFirst()){
            do {
                Profile profile = new Profile();
                profile.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
                profile.setProfileName(cursor.getString(cursor.getColumnIndex(Constants.KEY_PROFILE_NAME)));
                profile.setBusinessOrPersonal(cursor.getString(cursor.getColumnIndex(Constants.KEY_DESCRIPTION)));

                profilesList.add(profile);
            } while (cursor.moveToNext());
        }

        return profilesList;
    }

    public int updateProfile(Profile profile) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.KEY_PROFILE_NAME, profile.getProfileName());
        contentValues.put(Constants.KEY_DESCRIPTION, profile.getBusinessOrPersonal());

        //update
        return db.update(Constants.TABLE_NAME, contentValues, Constants.KEY_ID + "=?", new String[]{String.valueOf(profile.getId())});
    }

    public void deleteProfile(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TABLE_NAME, Constants.KEY_ID + "=?" , new String[]{String.valueOf(id)});

        db.close();
    }

    public int getProfilesCount() {
        String countQuery = "SELECT * FROM " + Constants.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }
    
    public ArrayList<Location> deserializeLocations(ArrayList<String> pairStrs) {
        ArrayList<Location> locations = new ArrayList<Location>();
        for (String pairJSONStr : pairStrs) {
            locations.add(deserializeLocationAndItems(pairJSONStr));
        }
        return locations;
    }

    public ArrayList<String> serializeLocations(ArrayList<Locations> locations) {
        ArrayList<String> pairJSONStrs = new ArrayList<String>();
        for (Location location : locations) {
            pairJSONStrs.add(serializeLocationAndItems(location));
        }
        return pairJSONStrs;
    }
}
