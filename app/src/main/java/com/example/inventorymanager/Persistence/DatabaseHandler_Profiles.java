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
import com.example.inventorymanager.model.Profile;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;


public class DatabaseHandler_Profiles extends SQLiteOpenHelper {

    private Context context;

    public DatabaseHandler_Profiles(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION + 1 + 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PROFILE_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "("
                + Constants.KEY_ID + " Integer PRIMARY KEY,"
                + Constants.KEY_PROFILE_NAME + " TEXT,"
                + Constants.KEY_DESCRIPTION + " TEXT,"
                + Constants.KEY_IMAGE_PATH + " TEXT,"
                + Constants.KEY_SERIAL_NUMBER + " TEXT,"
                + Constants.KEY_MODEL + " TEXT, "
                + Constants.KEY_QUANTITY + " INTEGER,"
                + Constants.KEY_PRICE + " REAL,"
                + Constants.KEY_DATE + " LONG);";

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

        if(profile.getImagePath() != null) {
            contentValues.put(Constants.KEY_IMAGE_PATH, profile.getImagePath());
            contentValues.put(Constants.KEY_SERIAL_NUMBER, profile.getSerialNo());
            contentValues.put(Constants.KEY_MODEL, profile.getModel());
            contentValues.put(Constants.KEY_QUANTITY, profile.getQuantity());
            contentValues.put(Constants.KEY_PRICE, profile.getPrice());
            contentValues.put(Constants.KEY_DATE, java.lang.System.currentTimeMillis());
        }

        db.insert(Constants.TABLE_NAME, null, contentValues);
        Log.d("ProfilehandlerDB", "added profile: " +profile.getProfileName());
    }

    public Profile getProfile(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Constants.TABLE_NAME,
                new String[]{Constants.KEY_ID,
                        Constants.KEY_PROFILE_NAME,
                        Constants.KEY_DESCRIPTION,
                        Constants.KEY_IMAGE_PATH,
                        Constants.KEY_SERIAL_NUMBER,
                        Constants.KEY_MODEL,
                        Constants.KEY_QUANTITY,
                        Constants.KEY_PRICE,
                        Constants.KEY_DATE},
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
            profile.setImagePath(cursor.getString(cursor.getColumnIndex(Constants.KEY_IMAGE_PATH)));
            profile.setSerialNo(cursor.getString(cursor.getColumnIndex(Constants.KEY_SERIAL_NUMBER)));
            profile.setModel(cursor.getString(cursor.getColumnIndex(Constants.KEY_MODEL)));
            profile.setQuantity(cursor.getInt(cursor.getColumnIndex(Constants.KEY_QUANTITY)));
            profile.setPrice(cursor.getDouble(cursor.getColumnIndex(Constants.KEY_PRICE)));

            //Convert Time (long) to string
            DateFormat dateFormat = DateFormat.getDateInstance();
            String format = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE))));
            profile.setDateOfPurchase(format);
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
                Constants.KEY_DESCRIPTION,
                Constants.KEY_IMAGE_PATH,
                Constants.KEY_SERIAL_NUMBER,
                Constants.KEY_MODEL,
                Constants.KEY_QUANTITY,
                Constants.KEY_PRICE,
                Constants.KEY_DATE},
                null, null, null, null, null);

        if(cursor.moveToFirst()){
            do {
                Profile profile = new Profile();
                profile.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
                profile.setProfileName(cursor.getString(cursor.getColumnIndex(Constants.KEY_PROFILE_NAME)));
                profile.setBusinessOrPersonal(cursor.getString(cursor.getColumnIndex(Constants.KEY_DESCRIPTION)));
                profile.setImagePath(cursor.getString(cursor.getColumnIndex(Constants.KEY_IMAGE_PATH)));
                profile.setSerialNo(cursor.getString(cursor.getColumnIndex(Constants.KEY_SERIAL_NUMBER)));
                profile.setModel(cursor.getString(cursor.getColumnIndex(Constants.KEY_MODEL)));
                profile.setQuantity(cursor.getInt(cursor.getColumnIndex(Constants.KEY_QUANTITY)));
                profile.setPrice(cursor.getDouble(cursor.getColumnIndex(Constants.KEY_PRICE)));

                //Date again
                //Convert Time (long) to string
                DateFormat dateFormat = DateFormat.getDateInstance();
                String format = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE))));
                profile.setDateOfPurchase(format);

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
        contentValues.put(Constants.KEY_IMAGE_PATH, profile.getImagePath());
        contentValues.put(Constants.KEY_SERIAL_NUMBER, profile.getSerialNo());
        contentValues.put(Constants.KEY_MODEL, profile.getModel());
        contentValues.put(Constants.KEY_QUANTITY, profile.getQuantity());
        contentValues.put(Constants.KEY_PRICE, profile.getPrice());
        contentValues.put(Constants.KEY_DATE, java.lang.System.currentTimeMillis());

        Log.d("ProfilehandlerDB", "added serial: " + profile.getSerialNo());

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

    public void clearDatabase(String TABLE_NAME) {
        SQLiteDatabase db = this.getWritableDatabase();
        String clearDBQuery = "DELETE FROM "+TABLE_NAME;
        db.execSQL(clearDBQuery);
    }
}
