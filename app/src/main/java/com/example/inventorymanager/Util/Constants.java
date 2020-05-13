package com.example.inventorymanager.Util;

import com.example.inventorymanager.model.Location;

import java.util.ArrayList;

public class Constants {

    //Profiles
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "profileListDB";
    public static final String TABLE_NAME = "profile_tbl";

    //Table Columns for profiles (business itemslul)
    public static final String KEY_ID = "id";
    public static final String KEY_PROFILE_NAME = "profile_name";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE_PATH = "image_path";
    public static final String KEY_SERIAL_NUMBER = "serialno";
    public static final String KEY_MODEL = "model";
    public static final String KEY_QUANTITY = "qty";
    public static final String KEY_PRICE = "price";
    public static final String KEY_DATE = "date_of_purchase";


    //Locations
    public static final int DB_VERSION_LOCATION = 1;
    public static final String DB_NAME_LOCATION = "locationListDB";
    public static final String TABLE__NAME_LOCATION = "location_tbl";

    //Location table columns
    public static final String KEY_ID_LOCATION = "id";
    public static final String KEY_LOCATION_NAME = "location_name";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_ITEM_LIST = "itemList";
    public static final String KEY_SERIAL_NUMBER_LOCATION = "serialno";
    public static final String KEY_MODEL_LOCATION = "model";
    public static final String KEY_QUANTITY_LOCATION = "qty";
    public static final String KEY_PRICE_LOCATION = "price";
    public static final String KEY_DATE_LOCATION = "date_of_purchase";

}
