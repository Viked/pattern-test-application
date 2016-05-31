package com.example.patternapplication.model.marker.decorator;

import android.content.Context;

import com.example.patternapplication.model.data.RequestedWeather;

/**
 * Created by 1 on 16.05.2016.
 */
public class WeatherMainDecorator extends BaseDecorator {

    public WeatherMainDecorator(TextDecorator decorator) {
        super(decorator);
    }

    @Override
    public String getText(RequestedWeather weather, Context context) {
        return weather.getWeather().get(0).getMain() +
                "\n" + super.getText(weather, context);
    }

}