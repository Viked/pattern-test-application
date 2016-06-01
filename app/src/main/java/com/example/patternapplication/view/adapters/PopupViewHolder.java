package com.example.patternapplication.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.patternapplication.R;
import com.example.patternapplication.model.marker.WeatherMarker;

import java.util.List;

/**
 * Created by viked on 01.06.16.
 */
public class PopupViewHolder extends AbstractViewHolder<WeatherMarker> {

    public PopupViewHolder(Context context) {
        super(LayoutInflater.from(context)
                .inflate(R.layout.fragment_lict_row, null, false));
    }

    @Override
    public void bindView() {

    }

    @Override
    public void deleteAction() {

    }

    @Override
    public void bindData(WeatherMarker data) {
        super.bindData(data);
        getImageView().setImageResource(R.drawable.weather_cloudy);
    }

    @Override
    public List<String> getContentList(WeatherMarker data) {
        return data.getTextDecorator().getText(data.getWeather(), itemView.getContext());
    }

    public View getView(){
        return itemView;
    }
}
