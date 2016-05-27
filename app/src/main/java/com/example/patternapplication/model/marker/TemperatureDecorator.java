package com.example.patternapplication.model.marker;

import com.example.patternapplication.model.data.Main;

import java.util.Locale;

/**
 * Created by 1 on 16.05.2016.
 */
public class TemperatureDecorator extends BaseDecorator {

    public TemperatureDecorator(MarkerDecorator decorator) {
        super(decorator);
    }

    @Override
    public String getText() {
        Main main = super.getWeather().getMain();
        return String.format(Locale.getDefault(), "Минимальная температура: %.2f", (main.getTempMin() - 273.15)) +
                String.format(Locale.getDefault(), "\nМаксимальная температура:  %.2f\n", (main.getTempMax() - 273.15)) +
                super.getText();
    }
}
