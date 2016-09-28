package viked.weathermap.model.preferences;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Eugeniy Shein on 28.09.2016.
 */

public interface IPreferencesModel {

    LatLng getLastLocation();

    void setLastLocation(LatLng lastLocation);

}
