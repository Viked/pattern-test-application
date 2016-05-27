package com.example.patternapplication.model.marker;


import com.example.patternapplication.model.data.RequestedWeather;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by 1 on 15.05.2016.
 */
public interface MarkerDecorator {
    LatLng getLocation();

    String getText();

    void setText(String text);

    RequestedWeather getWeather();

    void setWeather(RequestedWeather weather);

    MarkerOptions getMarkerOptions();
}
