package com.example.patternapplication.dagger.application;

import com.example.patternapplication.WeatherApplication;
import com.example.patternapplication.common.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Eugeniy Shein on 23.09.2016.
 */
@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class, UtilsModule.class, NavigatorModule.class})
public interface ApplicationComponent {
    void inject(WeatherApplication weatherApplication);
    void inject(BaseActivity baseActivity);
}
