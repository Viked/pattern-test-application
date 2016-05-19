package com.example.patternapplication.view.fragments;

import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Initb on 18.05.2016.
 */
public interface IMapFragment {

    void addMarker(MarkerOptions options);

    void clearMap();

    void updateView(MarkerOptions[] markerOptionsArray);

}
