package com.example.patternapplication.repository.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.patternapplication.constants.WeatherConstants;

/**
 * Created by Eugeniy Shein on 23.09.2016.
 */

public class WeatherContentProvider extends ContentProvider {

    public static final String CONTENT_PATH = "com.example.patternapplication.provider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_PATH);

    public static Uri getContentUri(){
        return BASE_CONTENT_URI.buildUpon().appendPath(WeatherConstants.DB_TABLE_NAME).build();
    }

    private SQLiteOpenHelper mHelper;

    @Override
    public boolean onCreate() {
        mHelper = new WeatherDatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        return db.query(WeatherConstants.DB_TABLE_NAME, WeatherConstants.columns, null, null, null, null, sortOrder);
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return WeatherConstants.DB_TABLE_NAME;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        long id = db.insertWithOnConflict(WeatherConstants.DB_TABLE_NAME, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);

        Uri returnUri = ContentUris.withAppendedId(uri, id);

        if (getContext() != null && getContext().getContentResolver() != null) {
            getContext().getContentResolver().notifyChange(returnUri, null);
        }

        return returnUri;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int delCount = db.delete(WeatherConstants.DB_TABLE_NAME, s, strings);
        if (getContext() != null && getContext().getContentResolver() != null) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return delCount;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int updateCount = db.update(
                WeatherConstants.DB_TABLE_NAME,
                contentValues,
                s,
                strings);

        if (getContext() != null && getContext().getContentResolver() != null) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return updateCount;
    }
}
