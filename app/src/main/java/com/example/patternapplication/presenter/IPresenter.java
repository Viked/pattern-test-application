package com.example.patternapplication.presenter;

import android.database.Cursor;

import com.example.patternapplication.model.db.IDBModel;
import com.example.patternapplication.view.IMainActivity;
import com.example.patternapplication.view.fragments.IListFragment;
import com.example.patternapplication.view.fragments.IMapFragment;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by 1 on 15.05.2016.
 */
public interface IPresenter extends GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    void onCreate();

    void onDestroy();

    void attachActivity(IMainActivity activity);

    void attachMapFragment(IMapFragment mapFragment);

    void attachListFragment(IListFragment listFragment);

    IDBModel getWeatherDB();

    void DBLoaded(Cursor cursor);

    void setMode(String string);

    void setMap(GoogleMap googleMap);

    void addLocation(LatLng latLng);

}