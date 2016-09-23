package com.example.patternapplication.dagger.application;

import com.example.patternapplication.utils.WeatherUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Eugeniy Shein on 23.09.2016.
 */
@Module
public class UtilsModule {

    @Provides
    @Singleton
    WeatherUtils provideWeatherUtils() {
        return new WeatherUtils();
    }
}
