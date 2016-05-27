package com.example.patternapplication;

import android.app.Application;
import android.content.Context;

import com.example.patternapplication.presenter.IPresenter;
import com.example.patternapplication.presenter.PresenterImpl;

/**
 * Created by Initb on 13.05.2016.
 */
public class WeatherApplication extends Application {

    private static Context application;

    public static Context getContext() {
        return application;
    }

    private IPresenter presenter;

    public IPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        presenter = new PresenterImpl(this);
        presenter.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        presenter.onDestroy();
    }


}
