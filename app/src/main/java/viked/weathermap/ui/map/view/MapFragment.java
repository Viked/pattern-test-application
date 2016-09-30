package viked.weathermap.ui.map.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.MapView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import viked.weathermap.R;
import viked.weathermap.ui.map.presenter.IMapPresenter;

/**
 * Created by Eugeniy Shein on 27.09.2016.
 */

public class MapFragment extends BaseMapFragment {

    @BindView(R.id.map)
    MapView mapView;

    @Inject
    private IMapPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(view);
        ((MapContainerFragment) getParentFragment()).getComponent().inject(this);
        mapView.onCreate(savedInstanceState);
        presenter.initMap(mapView);
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }


    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    void attachToPresenter() {
        presenter.addFragment(this);
    }

    @Override
    void detachToPresenter() {
        presenter.releseFragment(this);
    }

    @Override
    void updateView() {

    }
}
