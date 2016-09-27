package viked.weathermap.dagger.application;

import viked.weathermap.navigation.Navigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Eugeniy Shein on 23.09.2016.
 */
@Module
public class NavigatorModule {

    @Provides
    @Singleton
    Navigator provideNavigator() {
        return new Navigator();
    }

}
