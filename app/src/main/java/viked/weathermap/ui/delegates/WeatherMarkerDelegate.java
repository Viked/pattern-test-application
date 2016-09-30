package viked.weathermap.ui.delegates;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.patternapplication.model.marker.WeatherMarker;
import com.hannesdorfmann.adapterdelegates2.AdapterDelegate;

import java.util.List;

import viked.weathermap.R;

/**
 * Created by Eugeniy Shein on 29.09.2016.
 */

public class WeatherMarkerDelegate implements AdapterDelegate<List<WeatherMarker>> {
    @Override
    public boolean isForViewType(@NonNull List<WeatherMarker> items, int position) {
        return true;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_lict_row, parent, false);
        return new WeatherMarkerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull List<WeatherMarker> items, int position, @NonNull RecyclerView.ViewHolder holder) {

    }

    private static class WeatherMarkerViewHolder extends RecyclerView.ViewHolder {

        WeatherMarkerViewHolder(View v) {
            super(v);
        }
    }
}
