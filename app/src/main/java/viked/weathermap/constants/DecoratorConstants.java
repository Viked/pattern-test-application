package viked.weathermap.constants;

import com.example.patternapplication.model.marker.DecoratorItemSettings;

import java.util.ArrayList;
import java.util.List;

import viked.weathermap.R;

/**
 * Created by viked on 27.05.16.
 */
public class DecoratorConstants {
    private DecoratorConstants() {
    }

    public static final int TEMPERATURE_ID = 0;
    public static final int HUMIDITY_ID = 1;
    public static final int COUNTRY_NAME_ID = 2;
    public static final int LOCATION_NAME_ID = 3;
    public static final int PRESSURE_ID = 4;
    public static final int MAIN_WEATHER_ID = 5;
    public static final int DESCRIPTION_WEATHER_ID = 6;

    public static List<DecoratorItemSettings> getDefaultList() {
        List<DecoratorItemSettings> out = new ArrayList<>();
        out.add(new DecoratorItemSettings(true,
                MAIN_WEATHER_ID,
                R.string.decorator_title_weather));
        out.add(new DecoratorItemSettings(true,
                TEMPERATURE_ID,
                R.string.decorator_title_current_temperature));
        out.add(new DecoratorItemSettings(true,
                HUMIDITY_ID,
                R.string.decorator_title_humidity));
        out.add(new DecoratorItemSettings(false,
                COUNTRY_NAME_ID,
                R.string.decorator_title_country_name));
        out.add(new DecoratorItemSettings(false,
                LOCATION_NAME_ID,
                R.string.decorator_title_location_name));
        out.add(new DecoratorItemSettings(false,
                PRESSURE_ID,
                R.string.decorator_title_pressure));
        out.add(new DecoratorItemSettings(false,
                DESCRIPTION_WEATHER_ID,
                R.string.decorator_title_weather_description));
        return out;
    }
}
