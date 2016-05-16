package com.example.patternapplication.model.observable;

import com.example.patternapplication.model.data.RequestedWeather;

import java.util.Locale;

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
        return String.format(Locale.getDefault(), "Минимальная температура: %.2f", (weather.getMain().getTemp_min() - 273.15)) +
                String.format(Locale.getDefault(), "\nМаксимальная температура:  %.2f\n", (weather.getMain().getTempMax() - 273.15)) +
                super.getText(weather);
    }
}
