package com.example.patternapplication.model.marker.decorator;

import android.content.Context;

import com.example.patternapplication.model.data.RequestedWeather;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by viked on 27.05.16.
 */
public class BaseDecorator implements TextDecorator {

    private TextDecorator textDecorator;

    public BaseDecorator(TextDecorator textDecorator) {
        this.textDecorator = textDecorator;
    }

    @Override
    public List<String> getText(RequestedWeather weather, Context context) {
        return textDecorator.getText(weather, context);
    }
}
