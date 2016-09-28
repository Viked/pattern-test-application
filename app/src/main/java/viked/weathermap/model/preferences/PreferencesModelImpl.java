package viked.weathermap.model.preferences;

import android.content.SharedPreferences;

import com.google.android.gms.maps.model.LatLng;

import viked.weathermap.constants.PreferencesConstants;

/**
 * Created by Eugeniy Shein on 28.09.2016.
 */

public class PreferencesModelImpl implements IPreferencesModel {

    private SharedPreferences sharedPreferences;

    public PreferencesModelImpl(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public LatLng getLastLocation() {
        String lat = sharedPreferences.getString(PreferencesConstants.LAST_LOCATION_LATITUDE, null);
        String lon = sharedPreferences.getString(PreferencesConstants.LAST_LOCATION_LONGITUDE, null);
        if (lat != null && lon != null) {
            try {
                return new LatLng(Double.valueOf(lat), Double.valueOf(lon));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void setLastLocation(LatLng lastLocation) {
        sharedPreferences.edit()
                .putString(PreferencesConstants.LAST_LOCATION_LATITUDE, "" + lastLocation.latitude)
                .putString(PreferencesConstants.LAST_LOCATION_LONGITUDE, "" + lastLocation.longitude)
                .apply();
    }
}
