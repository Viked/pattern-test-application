package com.example.patternapplication.presenter;

import android.app.Activity;
import android.database.Cursor;
import android.location.Location;
import android.widget.TextView;

import java.util.Observer;

/**
 * Created by 1 on 15.05.2016.
 */
public interface IPresenter{
    void onCreate(Activity context);
    Cursor loadDB();
    void DBLoaded(Cursor cursor);
    void setLocation(Location location);
    void onDestroy();
    void initialViews(TextView[] textViews);
}
