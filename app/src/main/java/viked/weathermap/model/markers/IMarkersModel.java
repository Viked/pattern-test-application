package viked.weathermap.model.markers;

import com.example.patternapplication.model.marker.WeatherMarker;
import com.example.patternapplication.model.marker.decorator.TextDecorator;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import rx.Observable;

/**
 * Created by Eugeniy Shein on 29.09.2016.
 */

public interface IMarkersModel {

    Observable<WeatherMarker> addLocation(LatLng latLng);

    List<WeatherMarker> getCurrentMarkersList();

    List<WeatherMarker> getHistoryMarkersList();

    void deleteMarker(WeatherMarker marker, boolean deleteFromHistory);

    WeatherMarker getActiveMarker();

    TextDecorator getDecorator();
}
