package viked.weathermap.dagger.ui.splash;

import dagger.Subcomponent;
import viked.weathermap.dagger.ActivityScope;
import viked.weathermap.ui.splash.view.SplashActivity;

/**
 * Created by Eugeniy Shein on 28.09.2016.
 */
@ActivityScope
@Subcomponent(modules = SplashActivityModule.class)
public interface SplashActivityComponent {
    void inject(SplashActivity splashActivity);
}
