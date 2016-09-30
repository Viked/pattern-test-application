package viked.weathermap.ui.map.view;

import android.support.v4.app.Fragment;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Eugeniy Shein on 30.09.2016.
 */

public abstract class BaseMapFragment extends Fragment implements Observer {

    abstract void attachToPresenter();

    abstract void detachToPresenter();

    abstract void updateView();

    @Override
    public void update(Observable observable, Object o) {
        updateView();
    }

    @Override
    public void onStart() {
        super.onStart();
        attachToPresenter();
    }

    @Override
    public void onStop() {
        super.onStop();
        detachToPresenter();
    }

}
