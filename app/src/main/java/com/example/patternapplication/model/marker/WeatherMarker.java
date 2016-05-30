package com.example.patternapplication.model.marker;

import com.example.patternapplication.WeatherApplication;
import com.example.patternapplication.model.data.RequestedWeather;
import com.example.patternapplication.model.marker.decorator.BaseDecorator;
import com.example.patternapplication.model.marker.decorator.DecoratorMock;
import com.example.patternapplication.model.marker.decorator.TextDecorator;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterItem;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by 1 on 15.05.2016.
 */
public class WeatherMarker implements Observer, ClusterItem {

    private MarkerOptions markerOptions;

    private RequestedWeather weather;

    private TextDecorator textDecorator = new DecoratorMock();

    public WeatherMarker(LatLng latLng) {
        markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
    }

    @Override
    public void update(Observable observable, Object data) {
        if (data instanceof RequestedWeather) {
            RequestedWeather weather = (RequestedWeather) data;
            if (this.weather == null || this.weather.getCoord().equals(weather.getCoord())) {
                this.weather = weather;
            }
        }
    }

    public MarkerOptions getMarkerOptions() {
        if(weather!=null) {
            markerOptions.snippet(textDecorator.getText(weather, WeatherApplication.getContext()));
        }
        return markerOptions;
    }

    public void setWeather(RequestedWeather weather) {
        this.weather = weather;
    }

    @Override
    public LatLng getPosition() {
        return markerOptions.getPosition();
    }

    public TextDecorator getTextDecorator() {
        return textDecorator;
    }

    public void setTextDecorator(TextDecorator textDecorator) {
        this.textDecorator = textDecorator;
    }

    public RequestedWeather getWeather() {
        return weather;
    }

}
