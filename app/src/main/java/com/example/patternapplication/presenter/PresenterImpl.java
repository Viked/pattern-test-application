package com.example.patternapplication.presenter;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
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
import com.example.patternapplication.view.fragments.BaseFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Initb on 13.05.2016.
 */
public class PresenterImpl implements IPresenter {

    private static final long UPDATE_TIME_THRESHOLD = 600000;
    private static final long TIME_IN_DAY = 86400000;

    private static Observable getObservable(){
        return new Observable() {
            @Override
            public boolean hasChanged() {
                return true;
            }
        };
    }

    private Application context;

    private IMainActivity activity;

    private GoogleApiClient apiClient;

    private DBModel dbModel;

    private WeatherApiRequestInterface apiRequestInterface;

    private Observable dataObservable = getObservable();

    private Observable fragmentObservable  = getObservable();

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
    }

    @Override
    public void setActivity(IMainActivity activity) {
        this.activity = activity;
        if(dbModel.getDBCursor() == null) {
            activity.loadDB();
        }
    }

    @Override
    public void addFragment(BaseFragment fragment) {
        fragmentObservable.addObserver(fragment);
    }

    @Override
    public void removeFragment(BaseFragment fragment) {
        fragmentObservable.deleteObserver(fragment);
    }

    @Override
    public IDBModel getWeatherDB() {
        return dbModel;
    }

    @Override
    public void update() {
        fragmentObservable.notifyObservers(null);

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

    @Override
    public void setMode(String string) {
        mode = Integer.parseInt(string);
    }


    @Override
    public void addLocation(LatLng latLng) {
        MarkerDecorator temp = initial(latLng);
        Call<RequestedWeather> weatherCall = apiRequestInterface.getWeather(latLng.latitude, latLng.longitude);
        weatherCall.enqueue(new Callback<RequestedWeather>() {
            @Override
            public void onResponse(Call<RequestedWeather> call, Response<RequestedWeather> response) {
                RequestedWeather weather = response.body();
                temp.setWeather(weather);
                dbModel.addRec(weather);
                dataObservable.notifyObservers(weather);
                update();
            }

            @Override
            public void onFailure(Call<RequestedWeather> call, Throwable t) {
                t.printStackTrace();
            }
        });
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
}
