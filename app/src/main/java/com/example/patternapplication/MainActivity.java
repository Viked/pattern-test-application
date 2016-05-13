package com.example.patternapplication;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.patternapplication.model.data.RequestedWeather;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<RequestedWeather>>{

    //open weather api key d45545a62ad42fe8a840303b8600c6d8

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onLoadFinished(Loader<List<RequestedWeather>> loader, List<RequestedWeather> data) {

    }

    @Override
    public void onLoaderReset(Loader<List<RequestedWeather>> loader) {

    }

    @Override
    public Loader<List<RequestedWeather>> onCreateLoader(int id, Bundle args) {
        return null;
    }
}
