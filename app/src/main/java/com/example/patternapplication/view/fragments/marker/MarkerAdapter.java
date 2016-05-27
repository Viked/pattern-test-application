package com.example.patternapplication.view.fragments.marker;

import com.example.patternapplication.model.marker.WeatherMarker;
import com.example.patternapplication.view.adapters.AbstractRecyclerViewAdapter;

import java.util.List;

/**
 * Created by 1 on 19.05.2016.
 */
public class MarkerAdapter extends AbstractRecyclerViewAdapter<WeatherMarker, List<WeatherMarker>> {

    public MarkerAdapter(ViewHolderFactory<WeatherMarker> factory) {
        super(factory);
    }

    @Override
    public int getItemsSize(List<WeatherMarker> items) {
        return items.size();
    }

    @Override
    public WeatherMarker getItem(List<WeatherMarker> items, int i) {
        return items.get(i);
    }

}
