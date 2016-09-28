package viked.weathermap.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import javax.inject.Inject;

import viked.weathermap.dagger.application.ApplicationComponent;
import viked.weathermap.navigation.Navigator;

/**
 * Created by Eugeniy Shein on 23.09.2016.
 */

public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    @Inject
    private Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((WeatherApplication) getApplication()).getApplicationComponent();
    }

    public Navigator getNavigator() {
        return navigator;
    }

    @Override
    public void showMassage(View view, int stringId) {
        Snackbar.make(view, stringId, Snackbar.LENGTH_LONG).show();
    }

    @Nullable
    public <T> T getActivityComponent(){
        return null;
    }

}
