package com.example.patternapplication.model.marker.decorator;

import android.content.Context;

import com.example.patternapplication.model.data.RequestedWeather;

/**
 * Created by viked on 27.05.16.
 */
public interface TextDecorator {

    String getText(RequestedWeather weather, Context context);

}
