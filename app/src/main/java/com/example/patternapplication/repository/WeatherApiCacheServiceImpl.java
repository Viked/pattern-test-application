package com.example.patternapplication.repository;

import com.example.patternapplication.model.data.RequestedWeather;
import com.example.patternapplication.repository.api.WeatherApiRequestInterface;
import com.example.patternapplication.repository.data.WeatherCache;
import com.google.android.gms.maps.model.LatLng;

import rx.Observable;

/**
 * Created by Eugeniy Shein on 23.09.2016.
 */

public class WeatherApiCacheServiceImpl implements WeatherApiCacheService {

    private WeatherApiRequestInterface weatherApiRequestInterface;

    private WeatherCache weatherCache;

    public WeatherApiCacheServiceImpl(WeatherApiRequestInterface weatherApiRequestInterface, WeatherCache weatherCache) {
        this.weatherApiRequestInterface = weatherApiRequestInterface;
        this.weatherCache = weatherCache;
    }

    @Override
    public Observable<RequestedWeather> getWeather(double latitude, double longitude) {
        return Observable.create(subscriber -> {
            RequestedWeather requestedWeather = weatherCache.get(latitude, longitude);
            if (requestedWeather != null) {
                subscriber.onNext(requestedWeather);
            } else {
                weatherApiRequestInterface.getWeather(latitude, longitude)
                        .doOnNext(weather -> weather.setMapCoordinates(new LatLng(latitude, longitude)))
                        .doOnNext(weather -> weatherCache.put(weather))
                        .subscribe(subscriber);
            }
            subscriber.onCompleted();
        });
    }


}
