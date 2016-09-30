package viked.weathermap.ui.map.view;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


import com.hannesdorfmann.adapterdelegates2.AdapterDelegatesManager;

import java.util.ArrayList;
import java.util.List;

import viked.weathermap.common.OnClickViewHolder;
import viked.weathermap.model.decorators.WeatherMarker;
import viked.weathermap.ui.delegates.WeatherMarkerDelegate;

/**
 * Created by Eugeniy Shein on 29.09.2016.
 */

public class MarkersAdapter extends RecyclerView.Adapter {

    private AdapterDelegatesManager<List<WeatherMarker>> delegates;

    private List<WeatherMarker> data;

    public void setData(List<WeatherMarker> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public MarkersAdapter(OnClickViewHolder callback) {
        data = new ArrayList<>();
        delegates = new AdapterDelegatesManager<>();

        delegates.addDelegate(new WeatherMarkerDelegate(callback));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegates.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        return delegates.getItemViewType(data, position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
