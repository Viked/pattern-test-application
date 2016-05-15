package com.example.patternapplication.model.data;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by 1 on 15.05.2016.
 */
public class CurrentWeatherDeserializer implements JsonDeserializer<CurrentWeather> {
    private static final String COORDINATES_KEY = "coord";
    private static final String Longitude_KEY = "lon";
    private static final String Latitude_KEY = "lat";


    @Override
    public CurrentWeather deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        CurrentWeather weather = new CurrentWeather();
        JsonObject jsonObject = json.getAsJsonObject();
        JsonObject coordinates = jsonObject.getAsJsonObject(COORDINATES_KEY);
        weather.setLongitude(coordinates.get(Longitude_KEY).getAsDouble());
        weather.setLatitude(coordinates.get(Latitude_KEY).getAsDouble());


        return weather;
    }
}
