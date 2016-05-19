package com.example.patternapplication.view.fragments.db;

import android.view.View;
import android.widget.TextView;

import com.example.patternapplication.model.data.RequestedWeather;
import com.example.patternapplication.model.data.Utils;
import com.example.patternapplication.model.observable.MarkerDecorator;
import com.example.patternapplication.view.adapters.AbstractViewHolder;

/**
 * Created by 1 on 19.05.2016.
 */
public class DBViewHolder extends AbstractViewHolder<RequestedWeather> {

    private TextView textView;

    public DBViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView;
    }

    @Override
    public void bindData(RequestedWeather data) {
        String text = data.getSys().getCountry() + " "
                + Utils.convertKelvin(data.getMain().getTemp());
        textView.setText(text);
    }
}
