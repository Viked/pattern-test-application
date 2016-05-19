package com.example.patternapplication.model.db;

import android.database.Cursor;
import android.os.Bundle;

import com.example.patternapplication.model.data.RequestedWeather;

/**
 * Created by Initb on 18.05.2016.
 */
public interface IDBModel {

    void open();

    void close();

    Cursor getNewDBCursor();

    Cursor getNewDBCursor(Bundle args);

    void addRec(RequestedWeather weather);

    void editRec(RequestedWeather weather);

    void deleteRec(RequestedWeather weather);

    Cursor getDBCursor();

    void closeDBCursor();

}
