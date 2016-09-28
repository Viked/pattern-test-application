package viked.weathermap.dagger.ui.main;

import dagger.Subcomponent;
import viked.weathermap.dagger.ActivityScope;
import viked.weathermap.ui.main.view.MainActivity;

/**
 * Created by Eugeniy Shein on 28.09.2016.
 */
@ActivityScope
@Subcomponent(modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity activity);
}
