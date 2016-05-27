package com.example.patternapplication.presenter;

import com.example.patternapplication.model.db.IDBModel;
import com.example.patternapplication.model.marker.WeatherMarker;
import com.example.patternapplication.view.IMainActivity;
import com.example.patternapplication.view.fragments.BaseFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by 1 on 15.05.2016.
 */
public interface IPresenter {

    void onCreate();

    void onDestroy();

    void setActivity(IMainActivity activity);

    void addFragment(BaseFragment fragment);

    void removeFragment(BaseFragment fragment);

    IDBModel getWeatherDB();

    void requestUpdate();

    void update();

    void addLocation(LatLng latLng);

    List<WeatherMarker> getMarkerList();

    void deleteMarker(WeatherMarker marker);

    void showMarker(Object marker);

    WeatherMarker getActiveMarker();
}
