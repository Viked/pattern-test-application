package com.example.patternapplication;

import android.app.Application;
import android.content.Context;

import com.example.patternapplication.dagger.application.ApplicationComponent;
import com.example.patternapplication.dagger.application.ApplicationModule;
import com.example.patternapplication.dagger.application.DaggerApplicationComponent;
import com.example.patternapplication.presenter.IPresenter;
import com.example.patternapplication.presenter.PresenterImpl;

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
