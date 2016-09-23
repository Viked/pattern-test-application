package com.example.patternapplication.utils;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.patternapplication.constants.WeatherConstants;
import com.example.patternapplication.model.data.Clouds;
import com.example.patternapplication.model.data.Coord;
import com.example.patternapplication.model.data.Main;
import com.example.patternapplication.model.data.RequestedWeather;
import com.example.patternapplication.model.data.Sys;
import com.example.patternapplication.model.data.Weather;
import com.example.patternapplication.model.data.Wind;
import com.google.android.gms.maps.model.LatLng;

import java.util.Calendar;
import java.util.List;

import static com.example.patternapplication.constants.WeatherConstants.COLUMN_LAT;
import static com.example.patternapplication.constants.WeatherConstants.COLUMN_LNG;
import static com.example.patternapplication.constants.WeatherConstants.COLUMN_TIME;

/**
 * Created by Eugeniy Shein on 23.09.2016.
 */

public class WeatherUtils {

    public String getSelectionForCoordinates() {
        return COLUMN_LAT + " >= ? AND "
                + COLUMN_LAT + " <= ? AND "
                + COLUMN_LNG + " >= ? AND "
                + COLUMN_LNG + " <= ? ";
    }

    public String[] getSelectionArgsForCoordinates(double latitude, double longitude) {
        return new String[]{
                "" + (latitude - WeatherConstants.COORDINATES_ERROR),
                "" + (latitude + WeatherConstants.COORDINATES_ERROR),
                "" + (longitude - WeatherConstants.COORDINATES_ERROR),
                "" + (longitude + WeatherConstants.COORDINATES_ERROR)
        };
    }



    public RequestedWeather parseCursor(Cursor cursor) {
        RequestedWeather requestedWeather = new RequestedWeather();

        Long lat = cursor.getLong(cursor.getColumnIndex(WeatherConstants.COLUMN_LAT));
        Long lng = cursor.getLong(cursor.getColumnIndex(WeatherConstants.COLUMN_LNG));
        requestedWeather.setMapCoordinates(new LatLng(lat, lng));

        requestedWeather.setId(cursor.getLong(cursor.getColumnIndex(WeatherConstants.COLUMN_REQUEST_ID)));
        requestedWeather.setName(cursor.getString(cursor.getColumnIndex(WeatherConstants.COLUMN_NAME)));
        requestedWeather.setCod(cursor.getLong(cursor.getColumnIndex(WeatherConstants.COLUMN_COD)));
        requestedWeather.setDt(cursor.getLong(cursor.getColumnIndex(WeatherConstants.COLUMN_DT)));
        requestedWeather.setTime(cursor.getLong(cursor.getColumnIndex(WeatherConstants.COLUMN_TIME)));

        Coord coord = new Coord();
        coord.setLat(cursor.getDouble(cursor.getColumnIndex(WeatherConstants.COLUMN_COORD_LAT)));
        coord.setLon(cursor.getDouble(cursor.getColumnIndex(WeatherConstants.COLUMN_COORD_LON)));
        requestedWeather.setCoord(coord);


        Weather weather = new Weather();
        weather.setDescription(cursor.getString(cursor.getColumnIndex(WeatherConstants.COLUMN_WEATHER_DESCRIPTION)));
        weather.setIcon(cursor.getString(cursor.getColumnIndex(WeatherConstants.COLUMN_WEATHER_ICON)));
        weather.setMain(cursor.getString(cursor.getColumnIndex(WeatherConstants.COLUMN_WEATHER_MAIN)));
        weather.setId(cursor.getInt(cursor.getColumnIndex(WeatherConstants.COLUMN_WEATHER_ID)));
        requestedWeather.getWeather().add(weather);

        Main main = new Main();
        main.setHumidity(cursor.getInt(cursor.getColumnIndex(WeatherConstants.COLUMN_MAIN_HUMIDITY)));
        main.setPressure(cursor.getDouble(cursor.getColumnIndex(WeatherConstants.COLUMN_MAIN_PRESSURE)));
        main.setTemp(cursor.getDouble(cursor.getColumnIndex(WeatherConstants.COLUMN_MAIN_TEMP)));
        main.setTempMax(cursor.getDouble(cursor.getColumnIndex(WeatherConstants.COLUMN_MAIN_TEMP_MAX)));
        main.setTempMin(cursor.getDouble(cursor.getColumnIndex(WeatherConstants.COLUMN_MAIN_TEMP_MIN)));
        requestedWeather.setMain(main);

        Wind wind = new Wind();
        wind.setDeg(cursor.getDouble(cursor.getColumnIndex(WeatherConstants.COLUMN_WIND_DEG)));
        wind.setSpeed(cursor.getDouble(cursor.getColumnIndex(WeatherConstants.COLUMN_WIND_SPEED)));
        requestedWeather.setWind(wind);

        Sys sys = new Sys();
        sys.setCountry(cursor.getString(cursor.getColumnIndex(WeatherConstants.COLUMN_SYS_COUNTRY)));
        sys.setSunset(cursor.getLong(cursor.getColumnIndex(WeatherConstants.COLUMN_SYS_SUNSET)));
        sys.setSunrise(cursor.getLong(cursor.getColumnIndex(WeatherConstants.COLUMN_SYS_SUNRISE)));
        requestedWeather.setSys(sys);

        Clouds clouds = new Clouds();
        clouds.setAll(cursor.getLong(cursor.getColumnIndex(WeatherConstants.COLUMN_CLOUDS)));
        requestedWeather.setClouds(clouds);

        requestedWeather.setId(cursor.getLong(cursor.getColumnIndex(WeatherConstants.COLUMN_ID)));

        return requestedWeather;
    }

    public ContentValues getWeatherContentValue(RequestedWeather weather) {
        ContentValues cv = new ContentValues();

        cv.put(WeatherConstants.COLUMN_ID, weather.getId());

        Long time = weather.getTime();
        if (time == null || time == 0) {
            time = Calendar.getInstance().getTimeInMillis();
            weather.setTime(time);
        }
        cv.put(WeatherConstants.COLUMN_TIME, time);

        Coord coord = weather.getCoord();
        if (coord == null) {
            coord = new Coord();
            weather.setCoord(coord);
        }
        cv.put(WeatherConstants.COLUMN_COORD_LON, coord.getLon());
        cv.put(WeatherConstants.COLUMN_COORD_LAT, coord.getLat());

        LatLng latLng = weather.getMapCoordinates();
        if (latLng == null) {
            latLng = new LatLng(coord.getLat(), coord.getLon());
            weather.setMapCoordinates(latLng);
        }
        cv.put(WeatherConstants.COLUMN_LNG, latLng.longitude);
        cv.put(WeatherConstants.COLUMN_LAT, latLng.latitude);

        List<Weather> w = weather.getWeather();
        if (w.isEmpty()) {
            w.add(new Weather());
        }
        cv.put(WeatherConstants.COLUMN_WEATHER_ID, w.get(0).getId());
        cv.put(WeatherConstants.COLUMN_WEATHER_ICON, w.get(0).getIcon());
        cv.put(WeatherConstants.COLUMN_WEATHER_MAIN, w.get(0).getMain());
        cv.put(WeatherConstants.COLUMN_WEATHER_DESCRIPTION, w.get(0).getDescription());


        Main m = weather.getMain();
        if (m == null) {
            m = new Main();
            weather.setMain(m);
        }
        cv.put(WeatherConstants.COLUMN_MAIN_TEMP, m.getTemp());
        cv.put(WeatherConstants.COLUMN_MAIN_PRESSURE, m.getPressure());
        cv.put(WeatherConstants.COLUMN_MAIN_HUMIDITY, m.getHumidity());
        cv.put(WeatherConstants.COLUMN_MAIN_TEMP_MIN, m.getTempMin());
        cv.put(WeatherConstants.COLUMN_MAIN_TEMP_MAX, m.getTempMax());


        Wind wind = weather.getWind();
        if (wind == null) {
            wind = new Wind();
            weather.setWind(wind);
        }
        cv.put(WeatherConstants.COLUMN_WIND_SPEED, wind.getSpeed());
        cv.put(WeatherConstants.COLUMN_WIND_DEG, wind.getDeg());

        Clouds clouds = weather.getClouds();
        if (clouds == null) {
            clouds = new Clouds();
            weather.setClouds(clouds);
        }
        cv.put(WeatherConstants.COLUMN_CLOUDS, clouds.getAll());

        Sys sys = weather.getSys();
        if (sys == null) {
            sys = new Sys();
            weather.setSys(sys);
        }
        cv.put(WeatherConstants.COLUMN_SYS_COUNTRY, sys.getCountry());
        cv.put(WeatherConstants.COLUMN_SYS_SUNRISE, sys.getSunrise());
        cv.put(WeatherConstants.COLUMN_SYS_SUNSET, sys.getSunset());


        cv.put(WeatherConstants.COLUMN_DT, weather.getDt());
        cv.put(WeatherConstants.COLUMN_REQUEST_ID, weather.getId());
        cv.put(WeatherConstants.COLUMN_NAME, weather.getName());
        cv.put(WeatherConstants.COLUMN_COD, weather.getCod());
        return cv;
    }

}
