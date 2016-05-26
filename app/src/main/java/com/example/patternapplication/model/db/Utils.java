package com.example.patternapplication.model.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.patternapplication.model.data.Clouds;
import com.example.patternapplication.model.data.Coord;
import com.example.patternapplication.model.data.Main;
import com.example.patternapplication.model.data.RequestedWeather;
import com.example.patternapplication.model.data.Sys;
import com.example.patternapplication.model.data.Weather;
import com.example.patternapplication.model.data.Wind;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Initb on 18.05.2016.
 */
abstract public class Utils {

    public static List<RequestedWeather> getDataList(Cursor cursor) {
        List<RequestedWeather> out = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                out.add(parseCursor(cursor));
            }
        }
        return out;
    }

    public static RequestedWeather parseCursor(Cursor cursor) {
        RequestedWeather requestedWeather = new RequestedWeather();

        Long lat = cursor.getLong(cursor.getColumnIndex(DBConstants.COLUMN_LAT));
        Long lng = cursor.getLong(cursor.getColumnIndex(DBConstants.COLUMN_LNG));
        requestedWeather.setMapCoordinates(new LatLng(lat, lng));

        requestedWeather.setId(cursor.getLong(cursor.getColumnIndex(DBConstants.COLUMN_REQUEST_ID)));
        requestedWeather.setName(cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_NAME)));
        requestedWeather.setCod(cursor.getLong(cursor.getColumnIndex(DBConstants.COLUMN_COD)));
        requestedWeather.setDt(cursor.getLong(cursor.getColumnIndex(DBConstants.COLUMN_DT)));
        requestedWeather.setTime(cursor.getLong(cursor.getColumnIndex(DBConstants.COLUMN_TIME)));

        Coord coord = new Coord();
        coord.setLat(cursor.getDouble(cursor.getColumnIndex(DBConstants.COLUMN_COORD_LAT)));
        coord.setLon(cursor.getDouble(cursor.getColumnIndex(DBConstants.COLUMN_COORD_LON)));
        requestedWeather.setCoord(coord);


        Weather weather = new Weather();
        weather.setDescription(cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_WEATHER_DESCRIPTION)));
        weather.setIcon(cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_WEATHER_ICON)));
        weather.setMain(cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_WEATHER_MAIN)));
        weather.setId(cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_WEATHER_ID)));
        requestedWeather.getWeather().add(weather);

        Main main = new Main();
        main.setHumidity(cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_MAIN_HUMIDITY)));
        main.setPressure(cursor.getDouble(cursor.getColumnIndex(DBConstants.COLUMN_MAIN_PRESSURE)));
        main.setTemp(cursor.getDouble(cursor.getColumnIndex(DBConstants.COLUMN_MAIN_TEMP)));
        main.setTempMax(cursor.getDouble(cursor.getColumnIndex(DBConstants.COLUMN_MAIN_TEMP_MAX)));
        main.setTempMin(cursor.getDouble(cursor.getColumnIndex(DBConstants.COLUMN_MAIN_TEMP_MIN)));
        requestedWeather.setMain(main);

        Wind wind = new Wind();
        wind.setDeg(cursor.getDouble(cursor.getColumnIndex(DBConstants.COLUMN_WIND_DEG)));
        wind.setSpeed(cursor.getDouble(cursor.getColumnIndex(DBConstants.COLUMN_WIND_SPEED)));
        requestedWeather.setWind(wind);

        Sys sys = new Sys();
        sys.setCountry(cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_SYS_COUNTRY)));
        sys.setSunset(cursor.getLong(cursor.getColumnIndex(DBConstants.COLUMN_SYS_SUNSET)));
        sys.setSunrise(cursor.getLong(cursor.getColumnIndex(DBConstants.COLUMN_SYS_SUNRISE)));
        requestedWeather.setSys(sys);

        Clouds clouds = new Clouds();
        clouds.setAll(cursor.getLong(cursor.getColumnIndex(DBConstants.COLUMN_CLOUDS)));
        requestedWeather.setClouds(clouds);

        requestedWeather.setId(cursor.getLong(cursor.getColumnIndex(DBConstants.COLUMN_ID)));

        return requestedWeather;
    }

    public static ContentValues getWeatherContentValue(RequestedWeather weather) {
        ContentValues cv = new ContentValues();

        cv.put(DBConstants.COLUMN_ID, weather.getId());

        Long time = weather.getTime();
        if (time == null || time == 0) {
            time = Calendar.getInstance().getTimeInMillis();
            weather.setTime(time);
        }
        cv.put(DBConstants.COLUMN_TIME, time);

        Coord coord = weather.getCoord();
        if (coord == null) {
            coord = new Coord();
            weather.setCoord(coord);
        }
        cv.put(DBConstants.COLUMN_COORD_LON, coord.getLon());
        cv.put(DBConstants.COLUMN_COORD_LAT, coord.getLat());

        LatLng latLng = weather.getMapCoordinates();
        if (latLng == null) {
            latLng = new LatLng(coord.getLat(), coord.getLon());
            weather.setMapCoordinates(latLng);
        }
        cv.put(DBConstants.COLUMN_LNG, latLng.longitude);
        cv.put(DBConstants.COLUMN_LAT, latLng.latitude);

        List<Weather> w = weather.getWeather();
        if (w.isEmpty()) {
            w.add(new Weather());
        }
        cv.put(DBConstants.COLUMN_WEATHER_ID, w.get(0).getId());
        cv.put(DBConstants.COLUMN_WEATHER_ICON, w.get(0).getIcon());
        cv.put(DBConstants.COLUMN_WEATHER_MAIN, w.get(0).getMain());
        cv.put(DBConstants.COLUMN_WEATHER_DESCRIPTION, w.get(0).getDescription());


        Main m = weather.getMain();
        if (m == null) {
            m = new Main();
            weather.setMain(m);
        }
        cv.put(DBConstants.COLUMN_MAIN_TEMP, m.getTemp());
        cv.put(DBConstants.COLUMN_MAIN_PRESSURE, m.getPressure());
        cv.put(DBConstants.COLUMN_MAIN_HUMIDITY, m.getHumidity());
        cv.put(DBConstants.COLUMN_MAIN_TEMP_MIN, m.getTempMin());
        cv.put(DBConstants.COLUMN_MAIN_TEMP_MAX, m.getTempMax());


        Wind wind = weather.getWind();
        if (wind == null) {
            wind = new Wind();
            weather.setWind(wind);
        }
        cv.put(DBConstants.COLUMN_WIND_SPEED, wind.getSpeed());
        cv.put(DBConstants.COLUMN_WIND_DEG, wind.getDeg());

        Clouds clouds = weather.getClouds();
        if (clouds == null) {
            clouds = new Clouds();
            weather.setClouds(clouds);
        }
        cv.put(DBConstants.COLUMN_CLOUDS, clouds.getAll());

        Sys sys = weather.getSys();
        if (sys == null) {
            sys = new Sys();
            weather.setSys(sys);
        }
        cv.put(DBConstants.COLUMN_SYS_COUNTRY, sys.getCountry());
        cv.put(DBConstants.COLUMN_SYS_SUNRISE, sys.getSunrise());
        cv.put(DBConstants.COLUMN_SYS_SUNSET, sys.getSunset());


        cv.put(DBConstants.COLUMN_DT, weather.getDt());
        cv.put(DBConstants.COLUMN_REQUEST_ID, weather.getId());
        cv.put(DBConstants.COLUMN_NAME, weather.getName());
        cv.put(DBConstants.COLUMN_COD, weather.getCod());
        return cv;
    }

}
