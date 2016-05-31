package com.example.patternapplication.model.marker.decorator;

import android.content.Context;

import com.example.patternapplication.R;
import com.example.patternapplication.model.data.RequestedWeather;

/**
 * Created by 1 on 16.05.2016.
 */
public class HumidityDecorator extends BaseDecorator {

    public HumidityDecorator(TextDecorator decorator) {
        super(decorator);
    }

    @Override
    public String getText(RequestedWeather weather, Context context) {
        return context.getString(R.string.decorator_title_humidity) + ": " +
                weather.getMain().getHumidity() +
                "%\n" + super.getText(weather, context);
    }

}
