package com.example.patternapplication.model.observable;


import com.example.patternapplication.model.data.RequestedWeather;

/**
 * Created by 1 on 15.05.2016.
 */
public interface ViewTextDecorator {
    String getText(RequestedWeather weather);
    void setText(String text);
}
