package com.teespire.uselessweather;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.teespire.elements.SearchElement;

/**
 * Created by Sohaib Basit on 02/07/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_CITIES = "TableCities";
    private static final String CITY_NAME = "cityName";
    private static final String CITY_LONGITUDE = "longitude";
    private static final String CITY_LATITUDE = "latitude";


    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CITIES + "("
                + CITY_NAME + " TEXT,"
                + CITY_LONGITUDE + " TEXT,"
                + CITY_LATITUDE + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void AddCity(SearchElement searchElement){
        ContentValues values = new ContentValues();
        values.put(CITY_NAME, searchElement.getCityName());
        values.put(CITY_LATITUDE, searchElement.getLatitude());
        values.put(CITY_LONGITUDE, searchElement.getLongitude());

        SQLiteDatabase database = getWritableDatabase();
        database.insert(TABLE_CITIES, null, values);
    }
}
