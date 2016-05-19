package com.example.patternapplication.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.patternapplication.model.data.Clouds;
import com.example.patternapplication.model.data.Coord;
import com.example.patternapplication.model.data.Main;
import com.example.patternapplication.model.data.RequestedWeather;
import com.example.patternapplication.model.data.Sys;
import com.example.patternapplication.model.data.Weather;
import com.example.patternapplication.model.data.Wind;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Initb on 26.04.2016.
 */
public class DBModel implements IDBModel {

    private final Context mCtx;

    private Cursor cursor;

    private DBHelper mDBHelper;
    private SQLiteDatabase mDB;

    public DBModel(Context ctx) {
        mCtx = ctx;
    }

    public void open() {
        mDBHelper = new DBHelper(mCtx, DBConstants.DB_NAME, null, DBConstants.DB_VERSION);
        mDB = mDBHelper.getWritableDatabase();
    }

    public void close() {
        if (mDBHelper != null) mDBHelper.close();
    }

    @Override
    public Cursor getNewDBCursor(String sort) {
        String[] columns = {
                DBConstants.COLUMN_ID,
                DBConstants.COLUMN_TIME,
                DBConstants.COLUMN_COORD_LON,
                DBConstants.COLUMN_COORD_LAT,
                DBConstants.COLUMN_WEATHER_ID,
                DBConstants.COLUMN_WEATHER_MAIN,
                DBConstants.COLUMN_WEATHER_DESCRIPTION,
                DBConstants.COLUMN_WEATHER_ICON,
                DBConstants.COLUMN_MAIN_TEMP,
                DBConstants.COLUMN_MAIN_PRESSURE,
                DBConstants.COLUMN_MAIN_HUMIDITY,
                DBConstants.COLUMN_MAIN_TEMP_MIN,
                DBConstants.COLUMN_MAIN_TEMP_MAX,
                DBConstants.COLUMN_WIND_SPEED,
                DBConstants.COLUMN_WIND_DEG,
                DBConstants.COLUMN_CLOUDS,
                DBConstants.COLUMN_DT,
                DBConstants.COLUMN_SYS_COUNTRY,
                DBConstants.COLUMN_SYS_SUNRISE,
                DBConstants.COLUMN_SYS_SUNSET,
                DBConstants.COLUMN_REQUEST_ID,
                DBConstants.COLUMN_NAME,
                DBConstants.COLUMN_COD
        };

        this.cursor = mDB.query(DBConstants.DB_TABLE, columns,
                null, null, null, null, sort);

        return this.cursor;
    }


    @Override
    public Cursor getNewDBCursor() {
        return getNewDBCursor(DBConstants.COLUMN_SYS_COUNTRY + " ASC");
    }

    @Override
    public Cursor getDBCursor() {
        return cursor;
    }

    @Override
    public void addRec(RequestedWeather weather) {
        if (weather != null) {
            mDB.insert(DBConstants.DB_TABLE, null, Utils.getWeatherContentValue(weather));
        }
    }

    @Override
    public void editRec(RequestedWeather weather) {
        if (weather != null) {
            mDB.replace(DBConstants.DB_TABLE, null, Utils.getWeatherContentValue(weather));
        }
    }

    @Override
    public void deleteRec(RequestedWeather weather) {
        if (weather != null) {
            mDB.delete(DBConstants.DB_TABLE, DBConstants.COLUMN_TIME + " = " + weather.getTime(), null);
        }
    }

}
