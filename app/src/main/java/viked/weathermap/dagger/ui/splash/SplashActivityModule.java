package viked.weathermap.dagger.ui.splash;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import viked.weathermap.dagger.ActivityScope;
import viked.weathermap.ui.splash.presenter.ISplashPresenter;
import viked.weathermap.ui.splash.presenter.SplashPresenterImpl;
import viked.weathermap.ui.splash.view.ISplashView;
import viked.weathermap.utils.ConnectionUtils;

/**
 * Created by Eugeniy Shein on 28.09.2016.
 */
@Module
public class SplashActivityModule {

    private ISplashView activity;

    public SplashActivityModule(ISplashView activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    ISplashPresenter provideSplashPresenter(Context context, ConnectionUtils utils) {
        return new SplashPresenterImpl(context, activity, utils);
    }

}
