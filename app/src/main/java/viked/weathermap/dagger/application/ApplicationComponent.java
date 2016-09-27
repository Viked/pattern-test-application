package viked.weathermap.dagger.application;

import com.example.patternapplication.WeatherApplication;

import javax.inject.Singleton;

import dagger.Component;
import viked.weathermap.common.BaseActivity;


/**
 * Created by Eugeniy Shein on 23.09.2016.
 */
@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class, UtilsModule.class, NavigatorModule.class})
public interface ApplicationComponent {
    void inject(WeatherApplication weatherApplication);

    void inject(BaseActivity baseActivity);

    void inject(MainActivity activity);

    void inject(SplashActivity activity);
}
