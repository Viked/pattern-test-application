package com.example.patternapplication.model.marker.decorator;

import android.content.Context;

import com.example.patternapplication.R;
import com.example.patternapplication.WeatherApplication;
import com.example.patternapplication.model.data.RequestedWeather;

import java.util.Locale;

/**
 * Created by 1 on 16.05.2016.
 */
public class TemperatureDecorator extends BaseDecorator {

    public TemperatureDecorator(TextDecorator decorator) {
        super(decorator);
    }

    @Override
    public String getText(RequestedWeather weather, Context context) {
        return context.getString(R.string.decorator_title_current_temperature) +
                String.format(Locale.getDefault(), ": %.2f\n", (weather.getMain().getTemp() - 273.15)) +
                super.getText(weather, context);
    }

}
