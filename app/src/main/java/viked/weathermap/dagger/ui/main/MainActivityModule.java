package viked.weathermap.dagger.ui.main;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import viked.weathermap.dagger.ActivityScope;
import viked.weathermap.ui.main.presenter.IMainActivityPresenter;
import viked.weathermap.ui.main.presenter.MainActivityPresenterImpl;
import viked.weathermap.ui.main.view.IMainView;
import viked.weathermap.utils.ConnectionUtils;

/**
 * Created by Eugeniy Shein on 28.09.2016.
 */
@Module
public class MainActivityModule {
    private IMainView activity;

    public MainActivityModule(IMainView activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    IMainActivityPresenter provideSplashPresenter(Context context, ConnectionUtils utils) {
        return new MainActivityPresenterImpl(context, activity, utils);
    }
}
