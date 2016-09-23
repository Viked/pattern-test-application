package com.example.patternapplication.repository.data;

import com.example.patternapplication.model.data.RequestedWeather;

/**
 * Created by Eugeniy Shein on 23.09.2016.
 */

public interface WeatherCache {

    RequestedWeather get(double latitude, double longitude);

    void put(RequestedWeather requestedWeather);

}
