package com.example.patternapplication.presenter;

import android.app.Application;

import com.example.patternapplication.model.WeatherApiRequestInterface;
import com.example.patternapplication.model.WeatherModel;
import com.example.patternapplication.model.data.RequestedWeather;
import com.example.patternapplication.model.db.DBModel;
import com.example.patternapplication.model.db.IDBModel;
import com.example.patternapplication.model.marker.BaseDecorator;
import com.example.patternapplication.model.marker.BaseMarker;
import com.example.patternapplication.model.marker.MarkerDecorator;
import com.example.patternapplication.model.marker.TemperatureDecorator;
import com.example.patternapplication.view.IMainActivity;
import com.example.patternapplication.view.fragments.BaseFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Calendar;
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
    private static final double COORDINATE_ERROR = 0.5;


    private static Observable getObservable() {
        return new Observable() {
            @Override
            public boolean hasChanged() {
                return true;
            }
        };
    }

    private Application context;

    private IMainActivity activity;

    private DBModel dbModel;

    private WeatherApiRequestInterface apiRequestInterface;

    private Observable dataObservable = getObservable();

    private Observable fragmentObservable = getObservable();

    private List<MarkerDecorator> markers = new ArrayList<>();

    private MarkerDecorator activeMarker = null;

    private int mode = 0;

    public PresenterImpl(Application context) {
        this.context = context;
        dbModel = new DBModel(context);
    }

    @Override
    public void onCreate() {
        dbModel.open();
        apiRequestInterface = WeatherModel.create();
    }

    @Override
    public void setActivity(IMainActivity activity) {
        this.activity = activity;
        if (dbModel.getDBCursor() == null) {
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
    public void requestUpdate() {
        activity.reloadDB(null);
    }

    @Override
    public void update() {
        fragmentObservable.notifyObservers(null);
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
        markers.add(decorator);
        activeMarker = decorator;
        return decorator;
    }

    @Override
    public void setMode(String string) {
        mode = Integer.parseInt(string);
    }

    @Override
    public void addLocation(LatLng latLng) {
        MarkerDecorator temp = initial(latLng);
        RequestedWeather weather = dbModel.getWeatherByCoordinates(latLng, COORDINATE_ERROR);
        if (weather != null) {
            long time = Calendar.getInstance().getTimeInMillis() - weather.getTime();
            if (time < UPDATE_TIME_THRESHOLD) {
                temp.setWeather(weather);
                dataObservable.notifyObservers(weather);
                requestUpdate();
            } else {
                Call<RequestedWeather> weatherCall = apiRequestInterface.getWeather(latLng.latitude, latLng.longitude);
                weatherCall.enqueue(new Callback<RequestedWeather>() {
                    @Override
                    public void onResponse(Call<RequestedWeather> call, Response<RequestedWeather> response) {
                        RequestedWeather weather = response.body();
                        temp.setWeather(weather);
                        dbModel.editRec(weather);
                        dataObservable.notifyObservers(weather);
                        requestUpdate();
                    }

                    @Override
                    public void onFailure(Call<RequestedWeather> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        } else {
            Call<RequestedWeather> weatherCall = apiRequestInterface.getWeather(latLng.latitude, latLng.longitude);
            weatherCall.enqueue(new Callback<RequestedWeather>() {
                @Override
                public void onResponse(Call<RequestedWeather> call, Response<RequestedWeather> response) {
                    RequestedWeather weather = response.body();
                    weather.setMapCoordinates(latLng);
                    temp.setWeather(weather);
                    dbModel.addRec(weather);
                    dataObservable.notifyObservers(weather);
                    requestUpdate();
                }

                @Override
                public void onFailure(Call<RequestedWeather> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    @Override
    public List<MarkerDecorator> getMarkerList() {
        return markers;
    }

    @Override
    public void deleteMarker(MarkerDecorator marker) {
        markers.remove(marker);
        dataObservable.deleteObserver((BaseDecorator) marker);
        update();
    }

    @Override
    public void showMarker(Object marker) {
        if (marker instanceof MarkerDecorator) {
            activeMarker = (MarkerDecorator) marker;
            update();
        } else if (marker instanceof RequestedWeather) {
            RequestedWeather weather = (RequestedWeather) marker;
            addLocation(weather.getMapCoordinates());
        }
        activity.showFragment(0);
    }

    @Override
    public MarkerDecorator getActiveMarker() {
        if (activeMarker != null) {
            return activeMarker;
        } else if (!markers.isEmpty()) {
            return markers.get(markers.size() - 1);
        } else {
            return null;
        }
    }
}
