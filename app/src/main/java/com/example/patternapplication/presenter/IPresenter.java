package com.example.patternapplication.presenter;

import android.app.Activity;
import android.database.Cursor;
import android.location.Location;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.Observer;

/**
 * Created by 1 on 15.05.2016.
 */
public interface IPresenter{
    void onCreate(Activity context);
    Cursor loadDB();
    void DBLoaded(Cursor cursor);
    void onDestroy();
    void setMode(String string);
    void setMap(GoogleMap googleMap);

    void addLocation(LatLng latLng);
}
