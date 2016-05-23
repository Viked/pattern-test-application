package com.example.patternapplication.presenter;

import com.example.patternapplication.model.db.IDBModel;
import com.example.patternapplication.model.observable.MarkerDecorator;
import com.example.patternapplication.view.IMainActivity;
import com.example.patternapplication.view.fragments.BaseFragment;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by 1 on 15.05.2016.
 */
public interface IPresenter extends GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    void onCreate();

    void onDestroy();

    void setActivity(IMainActivity activity);

    void addFragment(BaseFragment fragment);

    void removeFragment(BaseFragment fragment);

    IDBModel getWeatherDB();

    void update();

    void setMode(String string);

    void addLocation(LatLng latLng);

    List<MarkerDecorator> getMarkerList();

}
