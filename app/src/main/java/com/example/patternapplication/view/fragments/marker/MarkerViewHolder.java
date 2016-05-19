package com.example.patternapplication.view.fragments.marker;

import android.view.View;
import android.widget.TextView;

import com.example.patternapplication.model.data.RequestedWeather;
import com.example.patternapplication.model.observable.MarkerDecorator;
import com.example.patternapplication.view.adapters.AbstractViewHolder;

/**
 * Created by 1 on 19.05.2016.
 */
public class MarkerViewHolder extends AbstractViewHolder<MarkerDecorator> {

    private TextView textView;

    public MarkerViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView;
    }

    @Override
    public void bindData(MarkerDecorator data) {
        RequestedWeather weather = data.getWeather();
        String text = weather.getSys().getCountry() + " "
                + data.getLocation().toString();
        textView.setText(text);
    }
}
