package com.example.patternapplication;

import android.support.multidex.MultiDexApplication;

import com.example.patternapplication.presenter.IPresenter;
import com.example.patternapplication.presenter.PresenterImpl;

/**
 * Created by Initb on 13.05.2016.
 */
public class WeatherApplication extends MultiDexApplication {

    private IPresenter presenter;

    public IPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        presenter = new PresenterImpl(this);
        presenter.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        presenter.onDestroy();
    }


}