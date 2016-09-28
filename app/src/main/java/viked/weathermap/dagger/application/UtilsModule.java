package viked.weathermap.dagger.application;

import android.content.Context;

import com.example.patternapplication.utils.WeatherUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import viked.weathermap.utils.ConnectionUtils;

/**
 * Created by Eugeniy Shein on 23.09.2016.
 */
@Module
public class UtilsModule {

    @Provides
    @Singleton
    WeatherUtils provideWeatherUtils() {
        return new WeatherUtils();
    }

    @Provides
    @Singleton
    ConnectionUtils provideConnectionUtils(Context context) {
        return new ConnectionUtils(context);
    }

}
