package viked.weathermap.dagger.ui.map;

import dagger.Subcomponent;
import viked.weathermap.dagger.FragmentScope;
import viked.weathermap.ui.map.view.MapContainerFragment;
import viked.weathermap.ui.map.view.MapFragment;
import viked.weathermap.ui.map.view.MarkersFragment;

/**
 * Created by Eugeniy Shein on 28.09.2016.
 */
@FragmentScope
@Subcomponent(modules = MapModule.class)
public interface MapComponent {

    void inject(MapContainerFragment mapContainerFragment);

    void inject(MapFragment mapFragment);

    void inject(MarkersFragment markersFragment);

}
