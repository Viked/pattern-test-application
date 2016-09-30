package viked.weathermap.ui.map.view;

import android.content.Context;

import com.example.patternapplication.model.marker.WeatherMarker;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

/**
 * Created by Eugeniy Shein on 29.09.2016.
 */

public class MapClusterRenderer extends DefaultClusterRenderer<WeatherMarker> {

    public interface Callback {
        void selectMarker(Marker marker);
    }

    private Callback callback;

    public MapClusterRenderer(Callback callback, Context context, GoogleMap map,
                              ClusterManager<WeatherMarker> clusterManager) {
        super(context, map, clusterManager);
        this.callback = callback;
    }

    @Override
    protected void onBeforeClusterItemRendered(WeatherMarker item, MarkerOptions markerOptions) {
        super.onBeforeClusterItemRendered(item, markerOptions);
    }

    @Override
    protected void onClusterItemRendered(WeatherMarker clusterItem, Marker marker) {
        super.onClusterItemRendered(clusterItem, marker);
        callback.selectMarker(marker);
    }
}