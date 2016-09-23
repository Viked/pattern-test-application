package com.example.patternapplication.repository.data;

import android.content.ContentResolver;
import android.database.Cursor;

import com.example.patternapplication.constants.WeatherConstants;
import com.example.patternapplication.model.data.RequestedWeather;
import com.example.patternapplication.utils.WeatherUtils;
import com.google.android.gms.maps.model.LatLng;

import java.util.Calendar;

/**
 * Created by Eugeniy Shein on 23.09.2016.
 */

public class WeatherCacheImpl implements WeatherCache {

    private ContentResolver contentResolver;

    private WeatherUtils weatherUtils;

    public WeatherCacheImpl(ContentResolver contentResolver, WeatherUtils weatherUtils) {
        this.contentResolver = contentResolver;
        this.weatherUtils = weatherUtils;
    }

    @Override
    public RequestedWeather get(double latitude, double longitude) {
        Cursor cursor = contentResolver.query(WeatherContentProvider.getContentUri(),
                WeatherConstants.columns,
                weatherUtils.getSelectionForCoordinates(),
                weatherUtils.getSelectionArgsForCoordinates(latitude, longitude),
                null);
        RequestedWeather weather;
        long minTime = Calendar.getInstance().getTimeInMillis() - WeatherConstants.TIME_ERROR;
        if (cursor != null && cursor.getCount() > 0
                && (weather = weatherUtils.parseCursor(cursor)).getTime() >= minTime) {
            return weather;
        } else {
            return null;
        }
    }

    @Override
    public void put(RequestedWeather requestedWeather) {
        LatLng coordinates = requestedWeather.getMapCoordinates();
        contentResolver.delete(WeatherContentProvider.getContentUri(), weatherUtils.getSelectionForCoordinates(),
                weatherUtils.getSelectionArgsForCoordinates(coordinates.latitude, coordinates.longitude));
        contentResolver.insert(WeatherContentProvider.getContentUri(),
                weatherUtils.getWeatherContentValue(requestedWeather));
    }
}
