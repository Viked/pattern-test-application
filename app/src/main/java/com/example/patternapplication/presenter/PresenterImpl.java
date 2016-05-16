package com.example.patternapplication.presenter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.widget.TextView;

import com.example.patternapplication.model.observable.MarkerDecorator;
import com.example.patternapplication.view.MainActivity;
import com.example.patternapplication.model.WeatherApiRequestInterface;
import com.example.patternapplication.model.WeatherModel;
import com.example.patternapplication.model.data.RequestedWeather;
import com.example.patternapplication.model.db.DBModel;
import com.example.patternapplication.model.observable.BaseDecorator;
import com.example.patternapplication.model.observable.BaseMarker;
import com.example.patternapplication.model.observable.TemperatureDecorator;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Initb on 13.05.2016.
 */
public class PresenterImpl implements IPresenter {

    private static final long TIME_THRESHOLD = 600000;

    private MainActivity activity;
    private DBModel dbModel;

    private WeatherApiRequestInterface apiRequestInterface;

    private java.util.Observable dataObservable;

    private GoogleMap googleMap;

    private List<MarkerDecorator> newMarkers = new ArrayList<>();
    private List<MarkerDecorator> readyMarkers = new ArrayList<>();

    private int mode = 0;

    @Override
    public void onCreate(Activity context) {
        this.activity = (MainActivity) context;
        dbModel = new DBModel(activity);
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
    public Cursor loadDB() {
        return dbModel.getAllData();
    }

    @Override
    public void DBLoaded(Cursor cursor) {
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                Long currentTime = Calendar.getInstance().getTimeInMillis();
                for (RequestedWeather weather : dbModel.getDataList(cursor)) {
                    Long time = currentTime - weather.getTime();
                    if (time > TIME_THRESHOLD && time < 36000000) {
                        updateWeather(weather.getCoord().getLat(), weather.getCoord().getLon());
                    } else {
                        updateView(weather);
                    }
                }
            }
            cursor.close();
        }

    }

    @Override
    public void onDestroy() {
        dbModel.close();

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
        newMarkers.add(decorator);
        activity.loadDB();
        return decorator;
    }

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

    private void updateView(RequestedWeather weather) {
        if(!newMarkers.isEmpty()){
            newMarkers.get(0).setWeather(weather);
            if(googleMap != null){
                googleMap.addMarker(newMarkers.get(0).getMarkerOptions());
            } else {
                readyMarkers.add(newMarkers.get(0));
            }
            newMarkers.remove(0);
        }
        dataObservable.notifyObservers(weather);
    }

    @Override
    public void setMode(String string) {
        mode = Integer.parseInt(string);
    }

    @Override
    public void setMap(GoogleMap googleMap) {
        this.googleMap = googleMap;
        for (MarkerDecorator decorator : readyMarkers){
            googleMap.addMarker(decorator.getMarkerOptions());
        }
        readyMarkers.clear();
    }

    @Override
    public void addLocation(LatLng latLng) {
        MarkerDecorator temp = initial(latLng);
        if(googleMap != null){
            googleMap.addMarker(temp.getMarkerOptions());
        } else {
            readyMarkers.add(temp);
        }

    }
}
