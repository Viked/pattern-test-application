package com.example.patternapplication.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

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

    @Override
    public void open() {
        mDBHelper = new DBHelper(mCtx, DBConstants.DB_NAME, null, DBConstants.DB_VERSION);
        mDB = mDBHelper.getWritableDatabase();
    }

    @Override
    public void close() {
        if (mDBHelper != null) mDBHelper.close();
    }

    @Override
    public Cursor getNewDBCursor(Bundle args) {

        if (args == null) {
            this.cursor = mDB.query(DBConstants.DB_TABLE, DBConstants.columns,
                    null, null, null, null, null);
        } else {
            this.cursor = mDB.query(DBConstants.DB_TABLE, DBConstants.columns,
                    args.getString(DBConstants.BUNDLE_ARG_SELECTION),
                    args.getStringArray(DBConstants.BUNDLE_ARG_SELECTIONS_ARGS),
                    args.getString(DBConstants.BUNDLE_ARG_GROUP_BY),
                    args.getString(DBConstants.BUNDLE_ARG_HAVING),
                    args.getString(DBConstants.BUNDLE_ARG_ORDER_BY));
        }
        return this.cursor;
    }

    @Override
    public Cursor getNewDBCursor() {
        return getNewDBCursor(null);
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
