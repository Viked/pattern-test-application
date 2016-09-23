package com.example.patternapplication.repository.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.patternapplication.constants.WeatherConstants;

/**
 * Created by Eugeniy Shein on 23.09.2016.
 */

public class WeatherDatabaseHelper extends SQLiteOpenHelper {
    public WeatherDatabaseHelper(Context context) {
        super(context, WeatherConstants.DB_NAME, null, WeatherConstants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(WeatherConstants.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
