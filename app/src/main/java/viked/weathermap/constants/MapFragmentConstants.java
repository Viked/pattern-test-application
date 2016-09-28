package viked.weathermap.constants;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Eugeniy Shein on 27.09.2016.
 */

public class MapFragmentConstants {
    private MapFragmentConstants() {

    }

    public static final double ROME_LATITUDE = 41.8919300;
    public static final double ROME_LONGITUDE = 12.5113300;

    public static final LatLng ROME = new LatLng(ROME_LATITUDE, ROME_LONGITUDE);

    public static final int FRAGMENTS_COUNT = 2;
    public static final int MAP_FRAGMENT = 0;
    public static final int MARKERS_FRAGMENT = 1;

    public static final String SELECT_FRAGMENT_INDEX_KEY = "selected_item";

}
