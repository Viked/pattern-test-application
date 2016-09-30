package viked.weathermap.ui.map.view;

import android.view.View;

import com.example.patternapplication.model.marker.WeatherMarker;
import com.example.patternapplication.view.adapters.PopupViewHolder;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by Eugeniy Shein on 29.09.2016.
 */

public class MapPopupAdapter implements GoogleMap.InfoWindowAdapter{

    public interface Callback{
        WeatherMarker getChosenMarker();
    }

    private Callback callback;

    private PopupViewHolder popup = null;

    public MapPopupAdapter(Callback callback) {
        this.callback = callback;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        if (callback.getChosenMarker() != null) {
            popup.bindData(callback.getChosenMarker());
        }
        return (popup.getView());
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
