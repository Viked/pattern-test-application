package com.example.patternapplication.presenter;

import android.app.Activity;
import android.database.Cursor;
import android.location.Location;
import android.os.Looper;

import com.example.patternapplication.MainActivity;
import com.example.patternapplication.model.CurrentLocation;
import com.example.patternapplication.model.WeatherApiRequestInterface;
import com.example.patternapplication.model.WeatherModel;
import com.example.patternapplication.model.data.RequestedWeather;
import com.example.patternapplication.model.db.DBModel;

import java.util.Calendar;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * Created by Initb on 13.05.2016.
 */
public class PresenterImpl implements IPresenter {

    private static final long TIME_THRESHOLD = 600000;

    private MainActivity activity;
    private DBModel dbModel;

    private WeatherApiRequestInterface apiRequestInterface;

    private Location currentLocation;

    private Cursor cursor;

    @Override
    public void onCreate(Activity context) {
        this.activity = (MainActivity) context;
        dbModel = new DBModel(activity);
        dbModel.open();
        apiRequestInterface = WeatherModel.create();
    }

    @Override
    public Cursor loadDB() {
        return dbModel.getAllData();
    }

    @Override
    public void DBLoaded(Cursor cursor) {
        this.cursor = cursor;
        update();
    }

    @Override
    public void onDestroy() {
        dbModel.close();
        cursor.close();
    }

    private void update(){
        if (cursor != null && cursor.getCount() > 0 && cursor.moveToLast()) {
            RequestedWeather weather = dbModel.parseCursor(cursor);
            Long currentTime = Calendar.getInstance().getTimeInMillis();
            Long time = currentTime - weather.getTime();
            if (time > TIME_THRESHOLD) {
                updateWeather();
            } else {
                updateView(weather);
            }
        } else {
            updateWeather();
        }
    }

    private void updateWeather() {
        if(currentLocation != null) {
            Observable.just(currentLocation)
                    .flatMap(location -> apiRequestInterface.getWeather(location.getLatitude(), location.getLongitude()))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(weather -> {
                                dbModel.addRec(weather);
                                updateView(weather);
                            },
                            Throwable::printStackTrace);
        }
    }

    private void updateView(RequestedWeather weather) {
        activity.updateView(weather.getName());
    }

    @Override
    public void setLocation(Location location) {
        currentLocation = location;
        update();
    }
}
