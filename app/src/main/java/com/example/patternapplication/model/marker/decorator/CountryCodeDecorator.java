package com.example.patternapplication.model.marker.decorator;

import android.content.Context;

import com.example.patternapplication.model.data.RequestedWeather;

/**
 * Created by 1 on 16.05.2016.
 */
public class CountryCodeDecorator extends BaseDecorator {

    public CountryCodeDecorator(TextDecorator decorator) {
        super(decorator);
    }

    @Override
    public String getText(RequestedWeather weather, Context context) {
        return weather.getCod() + "\n" + super.getText(weather, context);
    }

}
