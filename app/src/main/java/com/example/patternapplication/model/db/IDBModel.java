package com.example.patternapplication.model.db;

import android.database.Cursor;

import com.example.patternapplication.model.data.RequestedWeather;

/**
 * Created by Initb on 18.05.2016.
 */
public interface IDBModel {

    void open();

    void close();

    Cursor getNewDBCursor();

    Cursor getNewDBCursor(String sort);

    void addRec(RequestedWeather weather);

    void editRec(RequestedWeather weather);

    void deleteRec(RequestedWeather weather);

    Cursor getDBCursor();

}
