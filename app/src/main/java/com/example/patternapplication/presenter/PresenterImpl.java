package com.example.patternapplication.presenter;

import android.app.Application;

import com.example.patternapplication.model.WeatherApiRequestInterface;
import com.example.patternapplication.model.WeatherModel;
import com.example.patternapplication.model.data.RequestedWeather;
import com.example.patternapplication.model.db.DBModel;
import com.example.patternapplication.model.db.IDBModel;
import com.example.patternapplication.model.marker.DecoratorConstants;
import com.example.patternapplication.model.marker.DecoratorItemSettings;
import com.example.patternapplication.model.marker.DecoratorSettings;
import com.example.patternapplication.model.marker.WeatherMarker;
import com.example.patternapplication.model.marker.decorator.BaseDecorator;
import com.example.patternapplication.model.marker.decorator.CountryCodeDecorator;
import com.example.patternapplication.model.marker.decorator.CountryNameDecorator;
import com.example.patternapplication.model.marker.decorator.DecoratorMock;
import com.example.patternapplication.model.marker.decorator.LocationNameDecorator;
import com.example.patternapplication.model.marker.decorator.TemperatureDecorator;
import com.example.patternapplication.model.marker.decorator.TextDecorator;
import com.example.patternapplication.view.IMainActivity;
import com.example.patternapplication.view.fragments.BaseFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private Map<LatLng, WeatherMarker> markerMap = new HashMap<>();

    private LatLng activeMarkerKey = new LatLng(0, 0);

    private List<DecoratorItemSettings> settings;

    private boolean markerDecoratorActuality = false;

    public PresenterImpl(Application context) {
        this.context = context;
        dbModel = new DBModel(context);
        settings = DecoratorSettings.getSettings(context);
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
        List<DecoratorItemSettings> settings = DecoratorSettings.getSettings(context);
        if (this.settings != null && this.settings.equals(settings)) {
            markerDecoratorActuality = true;
            return;
        }
        this.settings = settings;
        markerDecoratorActuality = false;
    }

    @Override
    public void update() {
        fragmentObservable.notifyObservers(null);
    }

    @Override
    public void onDestroy() {
        dbModel.close();
    }

    @Override
    public void addLocation(LatLng latLng) {
        WeatherMarker marker = new WeatherMarker(latLng);
        dataObservable.addObserver(marker);
        activeMarkerKey = latLng;
        markerMap.put(latLng, marker);
        decorateMarker(marker);
        RequestedWeather weather = dbModel.getWeatherByCoordinates(latLng, COORDINATE_ERROR);
        if (weather != null) {
            long time = Calendar.getInstance().getTimeInMillis() - weather.getTime();
            if (time < UPDATE_TIME_THRESHOLD) {
                dataObservable.notifyObservers(weather);
                requestUpdate();
            } else {
                Call<RequestedWeather> weatherCall = apiRequestInterface.getWeather(latLng.latitude, latLng.longitude);
                weatherCall.enqueue(new Callback<RequestedWeather>() {
                    @Override
                    public void onResponse(Call<RequestedWeather> call, Response<RequestedWeather> response) {
                        RequestedWeather weather = response.body();
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
    public List<WeatherMarker> getMarkerList() {
        if (!markerDecoratorActuality) {
            decorateMarkers();
        }
        return new ArrayList<>(markerMap.values());
    }

    @Override
    public void deleteMarker(WeatherMarker marker) {
        markerMap.remove(marker.getLocation());
        dataObservable.deleteObserver(marker);
        update();
    }

    @Override
    public void showMarker(Object marker) {
        if (marker instanceof WeatherMarker) {
            activeMarkerKey = ((WeatherMarker) marker).getLocation();
            update();
        } else if (marker instanceof RequestedWeather) {
            RequestedWeather weather = (RequestedWeather) marker;
            addLocation(weather.getMapCoordinates());
        }
        activity.showFragment(0);
    }


    @Override
    public WeatherMarker getActiveMarker() {
        return markerMap.get(activeMarkerKey);
    }

    private void decorateMarkers() {
        for (WeatherMarker weatherMarker : markerMap.values()) {
            decorateMarker(weatherMarker);
        }
        markerDecoratorActuality = true;
    }

    private void decorateMarker(WeatherMarker marker) {
        marker.setTextDecorator(new BaseDecorator(new DecoratorMock()));
        for (int i = settings.size() - 1; i > -1; i--) {
            if (settings.get(i).isChecked()) {
                marker.setTextDecorator(decorateMarker(settings.get(i).getId(), marker.getTextDecorator()));
            }
        }
    }

    private TextDecorator decorateMarker(int decoratorId, TextDecorator markerDecorator) {
        switch (decoratorId) {
            case DecoratorConstants.TEMPERATURE_ID:
                return new TemperatureDecorator(markerDecorator);
            case DecoratorConstants.COUNTRY_CODE_ID:
                return new CountryCodeDecorator(markerDecorator);
            case DecoratorConstants.COUNTRY_NAME_ID:
                return new CountryNameDecorator(markerDecorator);
            case DecoratorConstants.LOCATION_NAME_ID:
                return new LocationNameDecorator(markerDecorator);
            default:
                return markerDecorator;
        }
    }

}
