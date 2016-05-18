package com.example.patternapplication.model.observable;

import android.widget.TextView;

import com.example.patternapplication.model.data.RequestedWeather;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

/**
 * Created by 1 on 15.05.2016.
 */
public class BaseMarker implements MarkerDecorator {

    private MarkerOptions markerOptions;
    private RequestedWeather weather;

    public BaseMarker(LatLng latLng) {
        markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Погода");
    }

    @Override
    public void setText(String text) {
        markerOptions.snippet(text);
    }

    @Override
    public MarkerOptions getMarkerOptions() {
        return markerOptions;
    }

    @Override
    public RequestedWeather getWeather() {
        return weather;
    }

    @Override
    public void setWeather(RequestedWeather weather) {
        this.weather = weather;
    }

    @Override
    public LatLng getLocation() {
        return markerOptions.getPosition();
    }

    @Override
    public String getText() {
        return String.format(Locale.getDefault(), "Текущаяя температура: %.2f", (weather.getMain().getTemp() - 273.15));
    }

}
