package com.example.patternapplication.model.marker.decorator;

import android.content.Context;

import com.example.patternapplication.R;
import com.example.patternapplication.model.data.RequestedWeather;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 16.05.2016.
 */
public class LocationNameDecorator extends BaseDecorator {

    public LocationNameDecorator(TextDecorator decorator) {
        super(decorator);
    }

    @Override
    public List<String> getText(RequestedWeather weather, Context context) {
        if(weather==null){
            return new ArrayList<>();
        }
        List<String> temp = super.getText(weather, context);
        temp.add(context.getString(R.string.decorator_title_location_name));
        temp.add(weather.getName());
        return temp;
    }

}
