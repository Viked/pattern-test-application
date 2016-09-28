package viked.weathermap.dagger.application;

import javax.inject.Singleton;

import dagger.Component;
import viked.weathermap.common.WeatherApplication;
import viked.weathermap.dagger.ui.main.MainActivityComponent;
import viked.weathermap.dagger.ui.main.MainActivityModule;
import viked.weathermap.dagger.ui.splash.SplashActivityComponent;
import viked.weathermap.dagger.ui.splash.SplashActivityModule;


/**
 * Created by Eugeniy Shein on 23.09.2016.
 */
@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class, UtilsModule.class, NavigatorModule.class, PreferencesModule.class})
public interface ApplicationComponent {

    SplashActivityComponent plus(SplashActivityModule splashActivityModule);

    MainActivityComponent plus(MainActivityModule activityModule);

    void inject(WeatherApplication weatherApplication);

}
