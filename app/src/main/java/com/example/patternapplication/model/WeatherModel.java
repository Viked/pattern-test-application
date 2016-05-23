package com.example.patternapplication.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Initb on 13.05.2016.
 */
public class WeatherModel {

    private WeatherModel(){}

    private static final String base_url = "http://api.openweathermap.org/data/2.5/";

    public static WeatherApiRequestInterface create() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(base_url)
                .build();
        return retrofit.create(WeatherApiRequestInterface.class);
    }

}
