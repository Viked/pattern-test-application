package viked.weathermap.common;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.patternapplication.WeatherApplication;

import javax.inject.Inject;

import viked.weathermap.dagger.application.ApplicationComponent;
import viked.weathermap.navigation.Navigator;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by Eugeniy Shein on 23.09.2016.
 */

public abstract class BaseActivity extends AppCompatActivity implements IBaseView{

    @Inject
    private Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }


    protected ApplicationComponent getApplicationComponent() {
        return ((WeatherApplication) getApplication()).getApplicationComponent();
    }

    public Navigator getNavigator() {
        return navigator;
    }

    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void showMassage(View view, int stringId) {
        Snackbar.make(view, stringId, Snackbar.LENGTH_LONG).show();
    }

}
