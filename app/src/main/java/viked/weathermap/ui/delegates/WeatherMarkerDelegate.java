package viked.weathermap.ui.delegates;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.hannesdorfmann.adapterdelegates2.AdapterDelegate;

import java.util.List;

import viked.weathermap.R;
import viked.weathermap.common.OnClickViewHolder;
import viked.weathermap.model.decorators.WeatherMarker;
import viked.weathermap.ui.map.view.MarkerContentAdapter;

import static viked.weathermap.constants.MapFragmentConstants.WEATHER_CONTENT_SPAN_COUNT;

/**
 * Created by Eugeniy Shein on 29.09.2016.
 */

public class WeatherMarkerDelegate implements AdapterDelegate<List<WeatherMarker>> {

    private OnClickViewHolder callback;

    public WeatherMarkerDelegate(OnClickViewHolder callback) {
        this.callback = callback;
    }

    @Override
    public boolean isForViewType(@NonNull List<WeatherMarker> items, int position) {
        return true;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_lict_row, parent, false);
        view.setOnClickListener(v -> callback.onClick(v, (int) v.getTag()));
        return new WeatherMarkerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull List<WeatherMarker> items, int position, @NonNull RecyclerView.ViewHolder holder) {
        WeatherMarkerViewHolder viewHolder = (WeatherMarkerViewHolder) holder;
        viewHolder.itemView.setTag(position);
        viewHolder.imageView.setImageResource(R.drawable.weather_cloudy);
        WeatherMarker marker = items.get(position);
        viewHolder.contentAdapter.addItems(
                marker.getTextDecorator()
                        .getText(marker.getWeather(), viewHolder.imageView.getContext()));
    }

    private static class WeatherMarkerViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        MarkerContentAdapter contentAdapter;

        WeatherMarkerViewHolder(View v) {
            super(v);
            imageView = (ImageView) v.findViewById(R.id.image);
            RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.content);
            recyclerView.setLayoutManager(new GridLayoutManager(v.getContext(), WEATHER_CONTENT_SPAN_COUNT));
            contentAdapter = new MarkerContentAdapter();
            recyclerView.setAdapter(contentAdapter);
        }
    }
}
