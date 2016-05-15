package com.example.patternapplication.model.observable;

import com.example.patternapplication.model.data.RequestedWeather;

/**
 * Created by 1 on 16.05.2016.
 */
public class TemperatureDecorator extends BaseDecorator {

    public TemperatureDecorator(ViewTextDecorator decorator) {
        super(decorator);
    }

    @Override
    public void setText(String text) {
        super.setText(text);
    }

    @Override
    public String getText(RequestedWeather weather) {
        return weather.getMain().getTempMin() + " - " + weather.getMain().getTempMax() + " ~ " + super.getText(weather);
    }
}
