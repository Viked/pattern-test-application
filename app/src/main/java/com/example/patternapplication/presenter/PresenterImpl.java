package com.example.patternapplication.presenter;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.example.patternapplication.model.WeatherApiRequestInterface;
import com.example.patternapplication.model.WeatherModel;
import com.example.patternapplication.model.data.RequestedWeather;
import com.example.patternapplication.model.db.DBModel;
import com.example.patternapplication.model.db.IDBModel;
import com.example.patternapplication.model.observable.BaseDecorator;
import com.example.patternapplication.model.observable.BaseMarker;
import com.example.patternapplication.model.observable.MarkerDecorator;
import com.example.patternapplication.model.observable.TemperatureDecorator;
import com.example.patternapplication.view.IMainActivity;
import com.example.patternapplication.view.adapters.PopupAdapter;
import com.example.patternapplication.view.fragments.db.IDBFragment;
import com.example.patternapplication.view.fragments.map.IMapFragment;
import com.example.patternapplication.view.fragments.marker.IMarkerListFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Initb on 13.05.2016.
 */
public class PresenterImpl implements IPresenter {

    private static final long UPDATE_TIME_THRESHOLD = 600000;
    private static final long TIME_IN_DAY = 86400000;

    private Application context;

    private IMainActivity activity;

    private IDBFragment dbFragment;

    private IMarkerListFragment markerListFragment;

    private IMapFragment mapFragment;

    private GoogleApiClient apiClient;

    private DBModel dbModel;

    private WeatherApiRequestInterface apiRequestInterface;

    private java.util.Observable dataObservable;

    private GoogleMap googleMap;

    private List<MarkerDecorator> markers = new ArrayList<>();

    private int mode = 0;

    public PresenterImpl(Application context) {
        this.context = context;
        apiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        dbModel = new DBModel(context);
    }

    @Override
    public void onCreate() {
        apiClient.connect();
        dbModel.open();
        apiRequestInterface = WeatherModel.create();
        dataObservable = new java.util.Observable() {
            @Override
            public boolean hasChanged() {
                return true;
            }
        };

    }

    @Override
    public void setActivity(IMainActivity activity) {
        this.activity = activity;
        if(dbModel.getDBCursor() == null) {
            activity.loadDB();
        }
    }

    @Override
    public void setMapFragment(IMapFragment mapFragment) {
        this.mapFragment = mapFragment;
    }

    @Override
    public void setDBFragment(IDBFragment listFragment) {
        this.dbFragment = listFragment;
        /*
        if(dbModel.getDBCursor() != null){
            db.setList(dbModel.getDBCursor());
        }
        */
    }

    @Override
    public void setMarkerListFragment(IMarkerListFragment markerListFragment) {
        this.markerListFragment = markerListFragment;
    }

    @Override
    public IDBModel getWeatherDB() {
        return dbModel;
    }

    @Override
    public void DBLoaded(Cursor cursor) {
        if(dbFragment != null) {
            dbFragment.setList(cursor);
        }

        /*
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                Long currentTime = Calendar.getInstance().getTimeInMillis();
                for (RequestedWeather weather : dbModel.getDataList(cursor)) {
                    Long time = currentTime - weather.getTime();
                    if (time > UPDATE_TIME_THRESHOLD && time < TIME_IN_DAY) {
                        updateWeather(weather.getCoord().getLat(), weather.getCoord().getLon());
                    } else {
                        updateView(weather);
                    }
                }
            }
            cursor.close();
        }*/

    }

    @Override
    public void onDestroy() {
        dbModel.close();
        apiClient.disconnect();
    }

    public MarkerDecorator initial(LatLng latLng) {
        MarkerDecorator decorator = new BaseDecorator(new BaseMarker(latLng));
        switch (mode) {
            case 2:
                //decorator =
            case 1:
                decorator = new TemperatureDecorator(decorator);
        }
        dataObservable.addObserver((BaseDecorator) decorator);
        markers.add(decorator);
        return decorator;
    }
/*
    private void updateWeather(double lat, double lon) {
        apiRequestInterface.getWeather(lat, lon)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weather -> {
                            dbModel.addRec(weather);
                            updateView(weather);
                        },
                        Throwable::printStackTrace);

    }
*/

    private void updateView(RequestedWeather weather) {
        dataObservable.notifyObservers(weather);
        mapFragment.updateView(getMarkerOptionsArray());
    }

    @Override
    public void setMode(String string) {
        mode = Integer.parseInt(string);
    }

    @Override
    public void setMap(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setOnMapClickListener(this::addLocation);
        for (MarkerDecorator decorator : markers) {
            mapFragment.addMarker(decorator.getMarkerOptions());
        }
        googleMap.setInfoWindowAdapter(new PopupAdapter(activity.getLayoutInflater()));
    }

    @Override
    public void addLocation(LatLng latLng) {
        MarkerDecorator temp = initial(latLng);
        Call<RequestedWeather> weatherCall = apiRequestInterface.getWeather(latLng.latitude, latLng.longitude);
        weatherCall.enqueue(new Callback<RequestedWeather>() {
            @Override
            public void onResponse(Call<RequestedWeather> call, Response<RequestedWeather> response) {
                temp.setWeather(response.body());
                dbModel.addRec(response.body());
                updateView(response.body());
            }

            @Override
            public void onFailure(Call<RequestedWeather> call, Throwable t) {
                t.printStackTrace();
            }
        });
        if (googleMap != null) {
            mapFragment.addMarker(temp.getMarkerOptions());
        }
    }

    @Override
    public List<MarkerDecorator> getMarkerList() {
        return markers;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);
            if (mLastLocation != null) {
                addLocation(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude()));
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private MarkerOptions[] getMarkerOptionsArray(){
        MarkerOptions[] markers = new MarkerOptions[this.markers.size()];
        for (int i = 0; i < markers.length; i++){
            markers[i] = this.markers.get(i).getMarkerOptions();
        }
        return markers;
    }

}
