package com.example.inventorymanager.Persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.inventorymanager.Util.Constants;
import com.example.inventorymanager.model.Location;

import java.util.ArrayList;

public class DatabaseHandler_Location extends SQLiteOpenHelper {

    private Context context;

    public DatabaseHandler_Location(@Nullable Context context) {
        super(context, Constants.DB_NAME_LOCATION, null, Constants.DB_VERSION_LOCATION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PROFILE_TABLE = "CREATE TABLE " + Constants.TABLE__NAME_LOCATION + "("
                + Constants.KEY_ID_LOCATION + " Integer PRIMARY KEY,"
                + Constants.KEY_LOCATION_NAME + " TEXT,"
                + Constants.KEY_ADDRESS + " TEXT);";

        db.execSQL(CREATE_PROFILE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(db);
    }

    //CRUD Operations

    public void addLocation(Location location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.KEY_LOCATION_NAME, location.getLocationName());
        contentValues.put(Constants.KEY_ADDRESS, location.getAddress());

        db.insert(Constants.TABLE__NAME_LOCATION, null, contentValues);
        Log.d("@HERE", "addLocation: " +location.getLocationName());
    }

    public Location getLocation(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Constants.TABLE__NAME_LOCATION,
                new String[]{Constants.KEY_ID_LOCATION,
                        Constants.KEY_LOCATION_NAME,
                        Constants.KEY_ADDRESS},
                Constants.KEY_ID_LOCATION + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if(cursor != null) {
            cursor.moveToFirst();
        }

        Location location = new Location();
        if (cursor!=null) {
            location.setKey(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID_LOCATION))));
            location.setLocationName(cursor.getString(cursor.getColumnIndex(Constants.KEY_LOCATION_NAME)));
            location.setAddress(cursor.getString(cursor.getColumnIndex(Constants.KEY_ADDRESS)));
        }

        return location;
    }

    public ArrayList<Location> getAllItems() {
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Location> locationsList = new ArrayList<>();

        Cursor cursor = db.query(Constants.TABLE__NAME_LOCATION,
                new String[]{Constants.KEY_ID_LOCATION,
                        Constants.KEY_LOCATION_NAME,
                        Constants.KEY_ADDRESS},
                null, null, null, null, null);

        if(cursor.moveToFirst()) {
            do {
                Location location = new Location();
                location.setKey(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID_LOCATION))));
                location.setLocationName(cursor.getString(cursor.getColumnIndex(Constants.KEY_LOCATION_NAME)));
                location.setAddress(cursor.getString(cursor.getColumnIndex(Constants.KEY_ADDRESS)));
                locationsList.add(location);
            }while (cursor.moveToNext());
        }

        return locationsList;
    }

    public int updateLocation(Location location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.KEY_LOCATION_NAME, location.getLocationName());
        contentValues.put(Constants.KEY_ADDRESS, location.getAddress());

        return db.update(Constants.TABLE__NAME_LOCATION, contentValues, Constants.KEY_ID_LOCATION +"=?", new String[]{String.valueOf(location.getKey())});
    }

    public int getLocationCount() {
        String countQuery = "SELECT * FROM " + Constants.TABLE__NAME_LOCATION;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }

    public void deleteLocation(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TABLE__NAME_LOCATION, Constants.KEY_ID_LOCATION + "=?", new String[] {String.valueOf(id)});
    }
}
