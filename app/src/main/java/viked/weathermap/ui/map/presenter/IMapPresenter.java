package viked.weathermap.ui.map.presenter;

import android.support.v4.view.ViewPager;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

/**
 * Created by Eugeniy Shein on 27.09.2016.
 */

public interface IMapPresenter extends OnMapReadyCallback {
    void initViewPager(ViewPager viewPager);

    void initMap(MapView mapView);

    void updateView();

}
