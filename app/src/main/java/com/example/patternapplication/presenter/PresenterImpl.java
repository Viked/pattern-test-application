package com.example.patternapplication.presenter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.widget.TextView;

import com.example.patternapplication.view.MainActivity;
import com.example.patternapplication.model.WeatherApiRequestInterface;
import com.example.patternapplication.model.WeatherModel;
import com.example.patternapplication.model.data.RequestedWeather;
import com.example.patternapplication.model.db.DBModel;
import com.example.patternapplication.model.observable.BaseDecorator;
import com.example.patternapplication.model.observable.BaseObject;
import com.example.patternapplication.model.observable.TemperatureDecorator;
import com.example.patternapplication.view.MapActivity;

import java.util.Calendar;

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

    private Location currentLocation;

    private Cursor cursor;

    private java.util.Observable dataObservable;

    @Override
    public void onCreate(Activity context) {
        this.activity = (MainActivity) context;
        dbModel = new DBModel(activity);
        dbModel.open();
        apiRequestInterface = WeatherModel.create();
        dataObservable = new java.util.Observable(){
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

    public void initialViews(TextView[] textViews){
        for(int i = 0; i < textViews.length; i++){
            textViews[i].setOnClickListener(v -> {
                RequestedWeather weather = (RequestedWeather) v.getTag();
                if(weather!=null){
                    Intent intent = new Intent(v.getContext(), MapActivity.class);
                    intent.putExtra(MapActivity.LOCATION_KEY, new double[]{weather.getCoord().getLat(), weather.getCoord().getLon()});
                    activity.startActivity(intent);
                }
            });
            BaseDecorator object;
            switch (i%4){
                case 1 :
                    object = new TemperatureDecorator(new BaseObject(textViews[i]));
                    break;
                case 2 :
                    object = new TemperatureDecorator(new TemperatureDecorator(new TemperatureDecorator(new BaseObject(textViews[i]))));
                    break;
                case 3 :
                    object = new TemperatureDecorator(new TemperatureDecorator(new BaseObject(textViews[i])));
                    break;
                default :
                    object = new TemperatureDecorator(new BaseObject(textViews[i]));
            }
            dataObservable.addObserver(object);
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
        dataObservable.notifyObservers(weather);
    }

    @Override
    public void setLocation(Location location) {
        currentLocation = location;
        update();
    }




}
