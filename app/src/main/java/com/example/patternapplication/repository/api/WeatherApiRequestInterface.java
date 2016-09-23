package com.example.patternapplication.repository.api;

import com.example.patternapplication.model.data.RequestedWeather;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Initb on 10.05.2016.
 */
public interface WeatherApiRequestInterface {

    String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    String APPID = "d45545a62ad42fe8a840303b8600c6d8";

    @GET("weather?APPID=" + APPID)
    Observable<RequestedWeather> getWeather(@Query("lat") double latitude, @Query("lon") double longitude);

}
