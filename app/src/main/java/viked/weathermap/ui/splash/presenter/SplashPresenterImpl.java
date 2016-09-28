package viked.weathermap.ui.splash.presenter;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;

import viked.weathermap.ui.splash.view.ISplashView;
import viked.weathermap.utils.ConnectionUtils;

/**
 * Created by Eugeniy Shein on 28.09.2016.
 */

public class SplashPresenterImpl implements ISplashPresenter, ConnectionCallbacks, OnConnectionFailedListener {

    private ConnectionUtils connectionUtils;
    private ISplashView view;
    private Context context;

    private GoogleApiClient apiClient;

    public SplashPresenterImpl(Context context,
                               ISplashView view,
                               ConnectionUtils connectionUtils) {
        this.context = context;
        this.connectionUtils = connectionUtils;
        this.view = view;
        this.apiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnect() {
        if (connectionUtils.isConnected()) {
            apiClient.connect();
        }
    }

    @Override
    public void onDisconnect() {
        apiClient.disconnect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);
            if (mLastLocation != null) {
                view.startMainActivity();
            }
        }
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        view.startMainActivity();
    }
}
