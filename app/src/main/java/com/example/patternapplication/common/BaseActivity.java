package com.example.patternapplication.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.patternapplication.WeatherApplication;
import com.example.patternapplication.dagger.application.ApplicationComponent;
import com.example.patternapplication.navigation.Navigator;

import javax.inject.Inject;

/**
 * Created by Eugeniy Shein on 23.09.2016.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }


    protected ApplicationComponent getApplicationComponent() {
        return ((WeatherApplication) getApplication()).getApplicationComponent();
    }

}
