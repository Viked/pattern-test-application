package viked.weathermap.dagger.application;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import viked.weathermap.model.preferences.IPreferencesModel;
import viked.weathermap.model.preferences.PreferencesModelImpl;

/**
 * Created by Eugeniy Shein on 28.09.2016.
 */
@Module
public class PreferencesModule {
    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    IPreferencesModel providePreferencesModel(SharedPreferences preferences) {
        return new PreferencesModelImpl(preferences);
    }
}
