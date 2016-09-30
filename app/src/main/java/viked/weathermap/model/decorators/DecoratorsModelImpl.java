package viked.weathermap.model.decorators;

import android.content.SharedPreferences;


import java.util.List;

import viked.weathermap.constants.DecoratorConstants;
import viked.weathermap.model.decorators.objects.BaseDecorator;
import viked.weathermap.model.decorators.objects.CountryNameDecorator;
import viked.weathermap.model.decorators.objects.DecoratorMock;
import viked.weathermap.model.decorators.objects.HumidityDecorator;
import viked.weathermap.model.decorators.objects.LocationNameDecorator;
import viked.weathermap.model.decorators.objects.PressureDecorator;
import viked.weathermap.model.decorators.objects.TemperatureDecorator;
import viked.weathermap.model.decorators.objects.TextDecorator;
import viked.weathermap.model.decorators.objects.WeatherDescriptionDecorator;
import viked.weathermap.model.decorators.objects.WeatherMainDecorator;

/**
 * Created by Eugeniy Shein on 29.09.2016.
 */

public class DecoratorsModelImpl implements IDecoratorsModel {

    private SharedPreferences sharedPreferences;

    public DecoratorsModelImpl(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public TextDecorator getDecorator() {
        TextDecorator out = new BaseDecorator(new DecoratorMock());
        List<DecoratorItemSettings> settings =  getDecoratorSettings();
        for (int i = 0; i < settings.size(); i++) {
            if (settings.get(i).isChecked()) {
                out = (decorateMarker(settings.get(i).getId(), out));
            }
        }
        return out;
    }

    @Override
    public void setDecoratorSettings(List<DecoratorItemSettings> decoratorSettings) {

    }

    @Override
    public List<DecoratorItemSettings> getDecoratorSettings() {
        return null;
    }

    private TextDecorator decorateMarker(int decoratorId, TextDecorator markerDecorator) {
        switch (decoratorId) {
            case DecoratorConstants.TEMPERATURE_ID:
                return new TemperatureDecorator(markerDecorator);
            case DecoratorConstants.PRESSURE_ID:
                return new PressureDecorator(markerDecorator);
            case DecoratorConstants.COUNTRY_NAME_ID:
                return new CountryNameDecorator(markerDecorator);
            case DecoratorConstants.LOCATION_NAME_ID:
                return new LocationNameDecorator(markerDecorator);
            case DecoratorConstants.HUMIDITY_ID:
                return new HumidityDecorator(markerDecorator);
            case DecoratorConstants.MAIN_WEATHER_ID:
                return new WeatherMainDecorator(markerDecorator);
            case DecoratorConstants.DESCRIPTION_WEATHER_ID:
                return new WeatherDescriptionDecorator(markerDecorator);
            default:
                return markerDecorator;
        }
    }
}
