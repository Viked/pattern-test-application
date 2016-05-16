package com.example.patternapplication.view;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.patternapplication.R;
import com.example.patternapplication.presenter.IPresenter;
import com.example.patternapplication.presenter.PresenterImpl;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, GoogleApiClient.ConnectionCallbacks, OnMapReadyCallback,  GoogleApiClient.OnConnectionFailedListener{

    private GoogleApiClient apiClient;
    private MapView mapView;
    //open weather api key d45545a62ad42fe8a840303b8600c6d8
    IPresenter presenter = new PresenterImpl();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        presenter.onCreate(this);
        CompoundButton.OnCheckedChangeListener modeListener =
                (buttonView, isChecked) -> {
                    if (isChecked) {
                        presenter.setMode((String) buttonView.getTag());
                    }
                };
        RadioButton simple = (RadioButton) findViewById(R.id.simple);
        RadioButton withTwoTemperatures = (RadioButton) findViewById(R.id.with_two_temperatures);
        RadioButton bonus = (RadioButton) findViewById(R.id.bonus);
        simple.setOnCheckedChangeListener(modeListener);
        withTwoTemperatures.setOnCheckedChangeListener(modeListener);
        bonus.setOnCheckedChangeListener(modeListener);

        apiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        apiClient.connect();
        getSupportLoaderManager().initLoader(0, null, this);
    }

    public void loadDB(){
        getSupportLoaderManager().restartLoader(0, null, this);
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        apiClient.disconnect();
        presenter.onDestroy();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        presenter.setMap(googleMap);
        googleMap.setOnMapClickListener(latLng -> presenter.addLocation(latLng));
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        presenter.DBLoaded(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new MyCursorLoader(this, presenter);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);
            if (mLastLocation != null) {
                presenter.addLocation(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude()));
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    static class MyCursorLoader extends CursorLoader {

        IPresenter presenter;

        public MyCursorLoader(Context context, IPresenter presenter) {
            super(context);
            this.presenter = presenter;
        }

        @Override
        public Cursor loadInBackground() {
            return presenter.loadDB();
        }

    }

}
