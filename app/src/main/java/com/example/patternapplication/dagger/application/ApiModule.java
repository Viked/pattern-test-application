package com.example.patternapplication.dagger.application;

import android.content.ContentResolver;

import com.example.patternapplication.repository.WeatherApiCacheService;
import com.example.patternapplication.repository.WeatherApiCacheServiceImpl;
import com.example.patternapplication.repository.api.WeatherApiRequestInterface;
import com.example.patternapplication.repository.data.WeatherCache;
import com.example.patternapplication.repository.data.WeatherCacheImpl;
import com.example.patternapplication.utils.WeatherUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Eugeniy Shein on 23.09.2016.
 */
@Module
public class ApiModule {

    @Provides
    @Singleton
    WeatherApiRequestInterface provideWeatherApi() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(WeatherApiRequestInterface.BASE_URL)
                .build()
                .create(WeatherApiRequestInterface.class);
    }

    @Provides
    @Singleton
    WeatherCache provideWeatherCache(ContentResolver contentResolver, WeatherUtils utils) {
        return new WeatherCacheImpl(contentResolver, utils);
    }

    @Provides
    @Singleton
    WeatherApiCacheService provideApiCacheServise(WeatherApiRequestInterface provideWeatherApi, WeatherCache weatherCache) {
        return new WeatherApiCacheServiceImpl(provideWeatherApi, weatherCache);
    }

}
