package com.example.patternapplication;

import android.app.Application;

import com.example.patternapplication.model.db.HelperFactory;

/**
 * Created by Initb on 13.05.2016.
 */
public class WeatherApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HelperFactory.setHelper(getApplicationContext());
    }
    @Override
    public void onTerminate() {
        HelperFactory.releaseHelper();
        super.onTerminate();
    }

}
