package com.example.patternapplication.view.fragments.marker;

import android.view.View;

import com.example.patternapplication.R;
import com.example.patternapplication.model.data.RequestedWeather;
import com.example.patternapplication.model.marker.WeatherMarker;
import com.example.patternapplication.view.adapters.AbstractViewHolder;
import com.example.patternapplication.view.fragments.BaseListFragment;

import java.util.List;
import java.util.Observable;

/**
 * Created by 1 on 19.05.2016.
 */
public class MarkerListFragment extends BaseListFragment<MarkerAdapter> {

    @Override
    public MarkerAdapter initialAdapter() {
        return new MarkerAdapter(MarkerViewHolder::new);
    }

    @Override
    public void update(Observable observable, Object data) {
        getAdapter().setItems(getPresenter().getMarkerList());
    }

    public class MarkerViewHolder extends AbstractViewHolder<WeatherMarker> {

        public MarkerViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindData(WeatherMarker data) {
            super.bindData(data);
            RequestedWeather weather = data.getWeather();
            if (weather != null) {
                getImageView().setImageResource(R.drawable.weather_cloudy);
            }
        }

        @Override
        public void deleteAction() {
            getPresenter().deleteMarker((WeatherMarker) itemView.getTag());
        }

        @Override
        public List<String> getContentList(WeatherMarker data) {
            return data.getTextDecorator().getText(data.getWeather(), itemView.getContext());
        }

        @Override
        public void bindView() {
            super.bindView();
            itemView.setOnClickListener(v -> getPresenter().showMarker(v.getTag()));
        }

    }
}
