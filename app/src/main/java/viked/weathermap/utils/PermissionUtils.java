package viked.weathermap.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

/**
 * Created by Eugeniy Shein on 28.09.2016.
 */

public class PermissionUtils {
    private Context context;

    public PermissionUtils(Context context) {
        this.context = context;
    }

    public boolean hasAccessLocation() {
        return checkPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                && checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    private boolean checkPermission(String permission) {
        return ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

}
