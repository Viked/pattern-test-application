package com.example.patternapplication.model.marker.decorator;

import android.content.Context;

import com.example.patternapplication.R;
import com.example.patternapplication.model.data.RequestedWeather;

/**
 * Created by 1 on 16.05.2016.
 */
public class PressureDecorator extends BaseDecorator {

    public PressureDecorator(TextDecorator decorator) {
        super(decorator);
    }

    @Override
    public String getText(RequestedWeather weather, Context context) {
        return context.getString(R.string.decorator_title_pressure) + ": " +
                weather.getMain().getPressure() +
                "\n" + super.getText(weather, context);
    }

}
