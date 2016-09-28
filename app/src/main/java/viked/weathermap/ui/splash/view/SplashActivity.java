package viked.weathermap.ui.splash.view;

import android.os.Bundle;


import javax.inject.Inject;

import viked.weathermap.R;
import viked.weathermap.common.BaseActivity;
import viked.weathermap.dagger.ui.splash.SplashActivityModule;
import viked.weathermap.ui.splash.presenter.ISplashPresenter;

/**
 * Created by viked on 01.06.16.
 */
public class SplashActivity extends BaseActivity implements ISplashView {

    @Inject
    private ISplashPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        getApplicationComponent().plus(new SplashActivityModule(this)).inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onConnect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDisconnect();
    }

    @Override
    public void startMainActivity() {
        getNavigator().startMainActivity(SplashActivity.this);
    }

}
