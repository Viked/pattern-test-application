package com.example.patternapplication.model.db;

/**
 * Created by Initb on 26.04.2016.
 */
public class DBConstants {

    public static final String DB_NAME = "weather.db";
    public static final int DB_VERSION = 1;
    public static final String DB_TABLE = "db_table_weather";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_COORD_LON = "coord_lon";
    public static final String COLUMN_COORD_LAT = "coord_lat";
    public static final String COLUMN_WEATHER_ID = "weather_id";
    public static final String COLUMN_WEATHER_MAIN = "weather_main";
    public static final String COLUMN_WEATHER_DESCRIPTION = "weather_description";
    public static final String COLUMN_WEATHER_ICON = "weather_icon";
    public static final String COLUMN_MAIN_TEMP = "main_temp";
    public static final String COLUMN_MAIN_PRESSURE = "main_pressure";
    public static final String COLUMN_MAIN_HUMIDITY = "main_humidity";
    public static final String COLUMN_MAIN_TEMP_MIN = "main_temp_min";
    public static final String COLUMN_MAIN_TEMP_MAX = "main_temp_max";
    public static final String COLUMN_WIND_SPEED = "wind_speed";
    public static final String COLUMN_WIND_DEG = "wind_deg";
    public static final String COLUMN_CLOUDS = "clouds";
    public static final String COLUMN_DT = "dt";
    public static final String COLUMN_SYS_COUNTRY = "sys_country";
    public static final String COLUMN_SYS_SUNRISE = "sys_sunrise";
    public static final String COLUMN_SYS_SUNSET = "sys_sunset";
    public static final String COLUMN_REQUEST_ID = "request_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_COD = "cod";

    public static final String DB_CREATE =
            "create table " + DB_TABLE + "(" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    COLUMN_TIME + " real, " +
                    COLUMN_COORD_LON + " real, " +
                    COLUMN_COORD_LAT + " real, " +
                    COLUMN_WEATHER_ID + " integer, " +
                    COLUMN_WEATHER_MAIN + " text, " +
                    COLUMN_WEATHER_DESCRIPTION + " text, " +
                    COLUMN_WEATHER_ICON + " text, " +
                    COLUMN_MAIN_TEMP + " real, " +
                    COLUMN_MAIN_PRESSURE + " real, " +
                    COLUMN_MAIN_HUMIDITY + " integer, " +
                    COLUMN_MAIN_TEMP_MIN + " real, " +
                    COLUMN_MAIN_TEMP_MAX + " real, " +
                    COLUMN_WIND_SPEED + " real, " +
                    COLUMN_WIND_DEG + " real, " +
                    COLUMN_CLOUDS + " integer, " +
                    COLUMN_DT + " integer, " +
                    COLUMN_SYS_COUNTRY + " text, " +
                    COLUMN_SYS_SUNRISE + " integer, " +
                    COLUMN_SYS_SUNSET + " integer, " +
                    COLUMN_REQUEST_ID + " integer, " +
                    COLUMN_NAME + " text, " +
                    COLUMN_COD + " integer);";


    public static String[] columns = {
            COLUMN_ID,
            COLUMN_TIME,
            COLUMN_COORD_LON,
            COLUMN_COORD_LAT,
            COLUMN_WEATHER_ID,
            COLUMN_WEATHER_MAIN,
            COLUMN_WEATHER_DESCRIPTION,
            COLUMN_WEATHER_ICON,
            COLUMN_MAIN_TEMP,
            COLUMN_MAIN_PRESSURE,
            COLUMN_MAIN_HUMIDITY,
            COLUMN_MAIN_TEMP_MIN,
            COLUMN_MAIN_TEMP_MAX,
            COLUMN_WIND_SPEED,
            COLUMN_WIND_DEG,
            COLUMN_CLOUDS,
            COLUMN_DT,
            COLUMN_SYS_COUNTRY,
            COLUMN_SYS_SUNRISE,
            COLUMN_SYS_SUNSET,
            COLUMN_REQUEST_ID,
            COLUMN_NAME,
            COLUMN_COD
    };

    public static final String BUNDLE_ARG_SELECTION = "selection";
    public static final String BUNDLE_ARG_SELECTIONS_ARGS = "selection_args";
    public static final String BUNDLE_ARG_GROUP_BY = "group_by";
    public static final String BUNDLE_ARG_HAVING = "having";
    public static final String BUNDLE_ARG_ORDER_BY = "order_by";

    public static final String order = " ASC";

}

