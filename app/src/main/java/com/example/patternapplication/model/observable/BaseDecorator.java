package com.example.patternapplication.model.observable;

import com.example.patternapplication.model.data.RequestedWeather;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by 1 on 16.05.2016.
 */
public abstract class BaseDecorator implements Observer, ViewTextDecorator {

    protected ViewTextDecorator decorator;

    public BaseDecorator(ViewTextDecorator decorator) {
        this.decorator = decorator;
    }

    @Override
    public void update(Observable observable, Object data) {
        setText(getText((RequestedWeather) data));
    }

    @Override
    public void setText(String text) {
        decorator.setText(text);
    }

    @Override
    public String getText(RequestedWeather weather) {
        return decorator.getText(weather);
    }
}
