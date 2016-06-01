package com.example.patternapplication.presenter;

import android.app.Application;

import com.example.patternapplication.R;
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
import com.example.patternapplication.model.marker.decorator.CountryNameDecorator;
import com.example.patternapplication.model.marker.decorator.DecoratorMock;
import com.example.patternapplication.model.marker.decorator.HumidityDecorator;
import com.example.patternapplication.model.marker.decorator.LocationNameDecorator;
import com.example.patternapplication.model.marker.decorator.PressureDecorator;
import com.example.patternapplication.model.marker.decorator.TemperatureDecorator;
import com.example.patternapplication.model.marker.decorator.TextDecorator;
import com.example.patternapplication.model.marker.decorator.WeatherDescriptionDecorator;
import com.example.patternapplication.model.marker.decorator.WeatherMainDecorator;
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

    private static final long NORMAL_COD = 200L;
    private static final long UPDATE_TIME_THRESHOLD = 3600000;
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
    }

    @Override
    public void update() {
        List<DecoratorItemSettings> settings = DecoratorSettings.getSettings(context);
        if (this.settings != null && this.settings.equals(settings)) {
            markerDecoratorActuality = true;
        } else {
            this.settings = settings;
            markerDecoratorActuality = false;
        }
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
        markerMap.put(latLng, marker);
        marker.setTextDecorator(getDecorator());
        RequestedWeather weather = dbModel.getWeatherByCoordinates(latLng, COORDINATE_ERROR);
        if (weather != null) {
            long time = Calendar.getInstance().getTimeInMillis() - weather.getTime();
            if (time < UPDATE_TIME_THRESHOLD) {
                activeMarkerKey = latLng;
                dataObservable.notifyObservers(weather);
                requestUpdate();
            } else {
                Call<RequestedWeather> weatherCall = apiRequestInterface.getWeather(latLng.latitude, latLng.longitude);
                weatherCall.enqueue(new Callback<RequestedWeather>() {
                    @Override
                    public void onResponse(Call<RequestedWeather> call, Response<RequestedWeather> response) {
                        RequestedWeather weather = response.body();
                        if (testRequestResult(weather)) {
                            dbModel.editRec(weather);
                            activeMarkerKey = latLng;
                            dataObservable.notifyObservers(weather);
                        } else {
                            activity.showMassage(R.string.massage_error_cod_not_equals_last_result);
                        }
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
                    if (testRequestResult(weather)) {
                        weather.setMapCoordinates(latLng);
                        dbModel.addRec(weather);
                        activeMarkerKey = latLng;
                        dataObservable.notifyObservers(weather);
                    } else {
                        activity.showMassage(R.string.massage_error_cod_not_equals_try_again);
                        dataObservable.deleteObserver(marker);
                        markerMap.remove(latLng);
                    }
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
        markerMap.remove(marker.getPosition());
        dataObservable.deleteObserver(marker);
        update();
    }

    @Override
    public void showMarker(Object marker) {
        if (marker instanceof WeatherMarker) {
            activeMarkerKey = ((WeatherMarker) marker).getPosition();
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

    @Override
    public TextDecorator getDecorator() {
        TextDecorator out = new BaseDecorator(new DecoratorMock());
        for (int i = 0; i < settings.size(); i++) {
            if (settings.get(i).isChecked()) {
                out = (decorateMarker(settings.get(i).getId(), out));
            }
        }
        return out;
    }

    private void decorateMarkers() {
        TextDecorator decorator = getDecorator();
        for (WeatherMarker weatherMarker : markerMap.values()) {
            weatherMarker.setTextDecorator(decorator);
        }
        markerDecoratorActuality = true;
    }

    private TextDecorator decorateMarker(int decoratorId, TextDecorator markerDecorator) {
        switch (decoratorId) {
            case DecoratorConstants.TEMPERATURE_ID:
                return new TemperatureDecorator(markerDecorator);
            case DecoratorConstants.PRESSURE_ID:
                return new PressureDecorator(markerDecorator);
            case DecoratorConstants.COUNTRY_NAME_ID:
                return new CountryNameDecorator(markerDecorator);
            case DecoratorConstants.LOCATION_NAME_ID:
                return new LocationNameDecorator(markerDecorator);
            case DecoratorConstants.HUMIDITY_ID:
                return new HumidityDecorator(markerDecorator);
            case DecoratorConstants.MAIN_WEATHER_ID:
                return new WeatherMainDecorator(markerDecorator);
            case DecoratorConstants.DESCRIPTION_WEATHER_ID:
                return new WeatherDescriptionDecorator(markerDecorator);
            default:
                return markerDecorator;
        }
    }

    private boolean testRequestResult(RequestedWeather weather) {
        return weather.getCod().equals(NORMAL_COD);
    }


}
