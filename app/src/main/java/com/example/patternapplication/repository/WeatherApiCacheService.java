package com.example.patternapplication.repository;

import com.example.patternapplication.model.data.RequestedWeather;

import rx.Observable;

/**
 * Created by Eugeniy Shein on 23.09.2016.
 */
public interface WeatherApiCacheService {
    Observable<RequestedWeather> getWeather(double latitude, double longitude);
}
