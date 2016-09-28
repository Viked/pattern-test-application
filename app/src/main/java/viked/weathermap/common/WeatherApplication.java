package viked.weathermap.common;


import android.app.Application;

import com.example.patternapplication.dagger.application.DaggerApplicationComponent;

import javax.inject.Inject;

import viked.weathermap.dagger.application.ApplicationComponent;
import viked.weathermap.dagger.application.ApplicationModule;
import viked.weathermap.model.preferences.IPreferencesModel;

import static viked.weathermap.constants.MapFragmentConstants.ROME;

/**
 * Created by Initb on 13.05.2016.
 */
public class WeatherApplication extends Application {

    private ApplicationComponent appComponent;

    @Inject
    private IPreferencesModel preferencesModel;

    @Override
    public void onCreate() {
        super.onCreate();
        buildDagger();

        initCoordinates();
    }

    private void buildDagger() {
        ApplicationModule appModule = new ApplicationModule(this);
        appComponent = DaggerApplicationComponent.builder().applicationModule(appModule).build();
        appComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return appComponent;
    }

    private void initCoordinates() {
        if (preferencesModel.getLastLocation() == null) {
            preferencesModel.setLastLocation(ROME);
        }
    }
}
