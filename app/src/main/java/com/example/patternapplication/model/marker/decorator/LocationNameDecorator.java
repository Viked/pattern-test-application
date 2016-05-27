package com.example.patternapplication.model.marker.decorator;

import android.content.Context;

import com.example.patternapplication.model.data.RequestedWeather;

/**
 * Created by 1 on 16.05.2016.
 */
public class LocationNameDecorator extends BaseDecorator {

    public LocationNameDecorator(TextDecorator decorator) {
        super(decorator);
    }

    @Override
    public String getText(RequestedWeather weather, Context context) {
        return weather.getName() + "\n" + super.getText(weather, context);
    }

}
