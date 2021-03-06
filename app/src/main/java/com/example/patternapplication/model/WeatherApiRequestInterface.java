package com.example.patternapplication.model;

import com.example.patternapplication.model.data.RequestedWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Initb on 10.05.2016.
 */
public interface WeatherApiRequestInterface {
    @GET("weather?APPID=d45545a62ad42fe8a840303b8600c6d8")
    Call<RequestedWeather> getWeather(@Query("lat") double latitude, @Query("lon") double longitude);
}
