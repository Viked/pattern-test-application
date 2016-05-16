package com.example.patternapplication.model.observable;

import android.widget.TextView;

import com.example.patternapplication.model.data.RequestedWeather;

import java.util.Locale;

/**
 * Created by 1 on 15.05.2016.
 */
public class BaseObject implements ViewTextDecorator{

    private TextView textView;

    public BaseObject(TextView textView) {
        this.textView = textView;
    }

    @Override
    public void setText(String text) {
        textView.setText(text);
    }

    @Override
    public String getText(RequestedWeather weather){
        textView.setTag(weather);
        return String.format(Locale.getDefault(), "Текущаяя температура: %.2f",(weather.getMain().getTemp() - 273.15));
    }

}
