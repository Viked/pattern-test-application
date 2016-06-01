package com.example.patternapplication.model.marker;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.patternapplication.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by viked on 27.05.16.
 */
public class DecoratorSettings {

    private static final String DECORATOR_LIST_KEY = "decorator_list";

    private DecoratorSettings() {
    }

    public static List<DecoratorItemSettings> getSettings(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String list = prefs.getString(DECORATOR_LIST_KEY, "");
        if (list.isEmpty()) {
            return setDefaultList(context);
        } else {
            return convert(list);
        }
    }

    public static void setSettings(Context context, List<DecoratorItemSettings> list) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit()
                .putString(DECORATOR_LIST_KEY, new Gson().toJson(list))
                .apply();
    }


    private static List<DecoratorItemSettings> convert(String list) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<DecoratorItemSettings>>() {
        }.getType();
        return gson.fromJson(list, type);
    }

    private static List<DecoratorItemSettings> setDefaultList(Context context) {
        List<DecoratorItemSettings> out = getDefaultList();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit()
                .putString(DECORATOR_LIST_KEY, new Gson().toJson(out))
                .apply();
        return out;
    }

    private static List<DecoratorItemSettings> getDefaultList() {
        List<DecoratorItemSettings> out = new ArrayList<>();
        out.add(new DecoratorItemSettings( true,
                DecoratorConstants.MAIN_WEATHER_ID,
                R.string.decorator_title_weather));
        out.add(new DecoratorItemSettings(true,
                DecoratorConstants.TEMPERATURE_ID,
                R.string.decorator_title_current_temperature));
        out.add(new DecoratorItemSettings(true,
                DecoratorConstants.HUMIDITY_ID,
                R.string.decorator_title_humidity));
        out.add(new DecoratorItemSettings( false,
                DecoratorConstants.COUNTRY_NAME_ID,
                R.string.decorator_title_country_name));
        out.add(new DecoratorItemSettings( false,
                DecoratorConstants.LOCATION_NAME_ID,
                R.string.decorator_title_location_name));
        out.add(new DecoratorItemSettings( false,
                DecoratorConstants.PRESSURE_ID,
                R.string.decorator_title_pressure));
        out.add(new DecoratorItemSettings( false,
                DecoratorConstants.DESCRIPTION_WEATHER_ID,
                R.string.decorator_title_weather_description));
        return out;
    }


}
