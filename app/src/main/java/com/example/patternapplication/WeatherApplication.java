package com.example.patternapplication;

import android.app.Application;

import viked.weathermap.dagger.application.ApplicationComponent;
import viked.weathermap.dagger.application.ApplicationModule;
import com.example.patternapplication.dagger.application.DaggerApplicationComponent;

/**
 * Created by Initb on 13.05.2016.
 */
public class WeatherApplication extends Application {

    private ApplicationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        buildDagger();

    }

    private void buildDagger() {
        ApplicationModule appModule = new ApplicationModule(this);
        appComponent = DaggerApplicationComponent.builder().applicationModule(appModule).build();
        appComponent.inject(this);
    }


    public ApplicationComponent getApplicationComponent() {
        return appComponent;
    }
}
