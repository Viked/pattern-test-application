package com.example.patternapplication.model.marker.decorator;

import android.content.Context;

import com.example.patternapplication.R;
import com.example.patternapplication.model.data.RequestedWeather;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 16.05.2016.
 */
public class WeatherDescriptionDecorator extends BaseDecorator {

    public WeatherDescriptionDecorator(TextDecorator decorator) {
        super(decorator);
    }

    @Override
    public List<String> getText(RequestedWeather weather, Context context) {
        if(weather==null){
            return new ArrayList<>();
        }
        List<String> temp = super.getText(weather, context);
        temp.add(context.getString(R.string.decorator_title_weather_description));
        temp.add(weather.getWeather().get(0).getDescription());
        return temp;
    }

}
