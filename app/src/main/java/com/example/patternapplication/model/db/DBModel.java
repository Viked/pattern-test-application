package com.example.patternapplication.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.patternapplication.model.data.Clouds;
import com.example.patternapplication.model.data.Coord;
import com.example.patternapplication.model.data.Main;
import com.example.patternapplication.model.data.RequestedWeather;
import com.example.patternapplication.model.data.Sys;
import com.example.patternapplication.model.data.Weather;
import com.example.patternapplication.model.data.Wind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Initb on 26.04.2016.
 */
public class DBModel {

    private final Context mCtx;


    private DBHelper mDBHelper;
    private SQLiteDatabase mDB;

    public DBModel(Context ctx) {
        mCtx = ctx;
    }

    // открыть подключение
    public void open() {
        mDBHelper = new DBHelper(mCtx, DBConstants.DB_NAME, null, DBConstants.DB_VERSION);
        mDB = mDBHelper.getWritableDatabase();
    }

    // закрыть подключение
    public void close() {
        if (mDBHelper != null) mDBHelper.close();
    }

    // получить все данные из таблицы DB_TABLE
    public Cursor getAllData() {
        String[] columns = {
                DBConstants.COLUMN_ID,
                DBConstants.COLUMN_COORD_LON,
                DBConstants.COLUMN_COORD_LAT,
                DBConstants.COLUMN_WEATHER_ID,
                DBConstants.COLUMN_WEATHER_MAIN,
                DBConstants.COLUMN_WEATHER_DESCRIPTION,
                DBConstants.COLUMN_WEATHER_ICON,
                DBConstants.COLUMN_MAIN_TEMP,
                DBConstants.COLUMN_MAIN_PRESSURE,
                DBConstants.COLUMN_MAIN_HUMIDITY,
                DBConstants.COLUMN_MAIN_TEMP_MIN,
                DBConstants.COLUMN_MAIN_TEMP_MAX,
                DBConstants.COLUMN_WIND_SPEED,
                DBConstants.COLUMN_WIND_DEG,
                DBConstants.COLUMN_CLOUDS,
                DBConstants.COLUMN_DT,
                DBConstants.COLUMN_SYS_COUNTRY,
                DBConstants.COLUMN_SYS_SUNRISE,
                DBConstants.COLUMN_SYS_SUNSET,
                DBConstants.COLUMN_REQUEST_ID,
                DBConstants.COLUMN_NAME,
                DBConstants.COLUMN_COD
        };
        return mDB.query(DBConstants.DB_TABLE, columns,
                null, null, null, null, null);
    }

    public List<RequestedWeather> parseCursor(Cursor cursor) {
        List<RequestedWeather> out = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            int coordLonIndex = cursor.getColumnIndex(DBConstants.COLUMN_COORD_LON);
            int coordLatIndex = cursor.getColumnIndex(DBConstants.COLUMN_COORD_LAT);
            int weatherIdIndex = cursor.getColumnIndex(DBConstants.COLUMN_WEATHER_ID);
            int weatherMainIndex = cursor.getColumnIndex(DBConstants.COLUMN_WEATHER_MAIN);
            int weatherDescriptionIndex = cursor.getColumnIndex(DBConstants.COLUMN_WEATHER_DESCRIPTION);
            int weatherIconIndex = cursor.getColumnIndex(DBConstants.COLUMN_WEATHER_ICON);
            int mainTempIndex = cursor.getColumnIndex(DBConstants.COLUMN_MAIN_TEMP);
            int mainPressureIndex = cursor.getColumnIndex(DBConstants.COLUMN_MAIN_PRESSURE);
            int mainHumidityIndex = cursor.getColumnIndex(DBConstants.COLUMN_MAIN_HUMIDITY);
            int mainTempMinIndex = cursor.getColumnIndex(DBConstants.COLUMN_MAIN_TEMP_MIN);
            int mainTempMaxIndex = cursor.getColumnIndex(DBConstants.COLUMN_MAIN_TEMP_MAX);
            int windSpeedIndex = cursor.getColumnIndex(DBConstants.COLUMN_WIND_SPEED);
            int windDegIndex = cursor.getColumnIndex(DBConstants.COLUMN_WIND_DEG);
            int cloudsIndex = cursor.getColumnIndex(DBConstants.COLUMN_CLOUDS);
            int dtIndex = cursor.getColumnIndex(DBConstants.COLUMN_DT);
            int sysCountryIndex = cursor.getColumnIndex(DBConstants.COLUMN_SYS_COUNTRY);
            int sysSunriseIndex = cursor.getColumnIndex(DBConstants.COLUMN_SYS_SUNRISE);
            int sysSunsetIndex = cursor.getColumnIndex(DBConstants.COLUMN_SYS_SUNSET);
            int requestIdIndex = cursor.getColumnIndex(DBConstants.COLUMN_REQUEST_ID);
            int nameIndex = cursor.getColumnIndex(DBConstants.COLUMN_NAME);
            int codeIndex = cursor.getColumnIndex(DBConstants.COLUMN_COD);
            while (cursor.moveToNext()) {
                RequestedWeather requestedWeather = new RequestedWeather();
                requestedWeather.setId(cursor.getLong(requestIdIndex));
                requestedWeather.setName(cursor.getString(nameIndex));
                requestedWeather.setCod(cursor.getLong(codeIndex));
                requestedWeather.setDt(cursor.getLong(dtIndex));

                Coord coord = new Coord();
                coord.setLat(cursor.getDouble(coordLatIndex));
                coord.setLon(cursor.getDouble(coordLonIndex));
                requestedWeather.setCoord(coord);


                Weather weather = new Weather();
                weather.setDescription(cursor.getString(weatherDescriptionIndex));
                weather.setIcon(cursor.getString(weatherIconIndex));
                weather.setMain(cursor.getString(weatherMainIndex));
                weather.setId(cursor.getInt(weatherIdIndex));
                requestedWeather.getWeather().add(weather);

                Main main = new Main();
                main.setHumidity(cursor.getInt(mainHumidityIndex));
                main.set(cursor.getInt(mainHumidityIndex));


                Clouds clouds = new Clouds();
                clouds.setAll(cursor.getLong(cloudsIndex));
                requestedWeather.setClouds(clouds);


                out.add(requestedWeather);
            }
        }
        return out;
    }

    // добавить запись в DB_TABLE
    public void addRec(RequestedWeather weather) {
        if (weather != null) {
            ContentValues cv = new ContentValues();
            Coord coord = weather.getCoord();
            if (coord == null) {
                coord = new Coord();
                weather.setCoord(coord);
            }
            cv.put(DBConstants.COLUMN_COORD_LON, coord.getLon());
            cv.put(DBConstants.COLUMN_COORD_LAT, coord.getLat());

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
            mDB.insert(DBConstants.DB_TABLE, null, cv);
        }
    }

    /*
    public void editRec(Contact contact) {
        ContentValues cv = new ContentValues();
        cv.put(DBConstants.COLUMN_ID, contact.getId());
        cv.put(DBConstants.COLUMN_NAME, contact.getName());
        cv.put(DBConstants.COLUMN_EMAIL, contact.getEmail());
        cv.put(DBConstants.COLUMN_PHONE, contact.getPhone());
        cv.put(DBConstants.COLUMN_IMG, contact.getImg().toString());
        mDB.replace(DBConstants.DB_TABLE, null, cv);
    }
*/

    // удалить запись из DB_TABLE
    public void delRec(RequestedWeather weather) {
        mDB.delete(DBConstants.DB_TABLE, DBConstants.COLUMN_REQUEST_ID + " = " + weather.getId(), null);
    }

}
