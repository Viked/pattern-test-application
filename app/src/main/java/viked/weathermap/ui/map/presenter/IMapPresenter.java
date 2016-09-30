package viked.weathermap.ui.map.presenter;

import android.support.v4.view.ViewPager;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.List;

import viked.weathermap.model.decorators.WeatherMarker;
import viked.weathermap.ui.map.view.BaseMapFragment;

/**
 * Created by Eugeniy Shein on 27.09.2016.
 */

public interface IMapPresenter extends OnMapReadyCallback {
    void initViewPager(ViewPager viewPager);

    void initMap(MapView mapView);

    void updateView();

    void showMarker(int markerIndex);

    List<WeatherMarker> getMarkersList();

    void addFragment(BaseMapFragment fragment);

    void releseFragment(BaseMapFragment fragment);
}
