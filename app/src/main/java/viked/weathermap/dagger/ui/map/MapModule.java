package viked.weathermap.dagger.ui.map;

import dagger.Module;
import viked.weathermap.ui.map.view.IMapView;

/**
 * Created by Eugeniy Shein on 29.09.2016.
 */
@Module
public class MapModule {

    private IMapView mapView;

    public MapModule(IMapView mapView) {
        this.mapView = mapView;
    }
}
