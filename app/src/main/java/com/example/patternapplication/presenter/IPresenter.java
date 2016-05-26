package com.example.patternapplication.presenter;

import com.example.patternapplication.model.db.IDBModel;
import com.example.patternapplication.model.observable.MarkerDecorator;
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

    void setMode(String string);

    void addLocation(LatLng latLng);

    List<MarkerDecorator> getMarkerList();

    void deleteMarker(MarkerDecorator marker);

    void showMarker(Object marker);

    MarkerDecorator getActiveMarker();
}
