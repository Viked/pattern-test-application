package com.example.patternapplication.model.observable;

import com.example.patternapplication.model.data.RequestedWeather;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by 1 on 16.05.2016.
 */
public class BaseDecorator implements Observer, MarkerDecorator {

    private MarkerDecorator decorator;

    public BaseDecorator(MarkerDecorator decorator) {
        this.decorator = decorator;
    }

    @Override
    public void update(Observable observable, Object data) {
        RequestedWeather weather = (RequestedWeather) data;
        RequestedWeather old = getWeather();
        if (weather.getCoord().equals(old.getCoord())) {
            setWeather(weather);
            setText(getText());
        }
    }

    @Override
    public LatLng getLocation() {
        return decorator.getLocation();
    }

    @Override
    public void setText(String text) {
        decorator.setText(text);
    }

    @Override
    public String getText() {
        return decorator.getText();
    }

    @Override
    public RequestedWeather getWeather() {
        return decorator.getWeather();
    }

    @Override
    public void setWeather(RequestedWeather weather) {
        decorator.setWeather(weather);
    }

    @Override
    public MarkerOptions getMarkerOptions() {
        return decorator.getMarkerOptions();
    }
}
