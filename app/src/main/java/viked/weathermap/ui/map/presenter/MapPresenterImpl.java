package viked.weathermap.ui.map.presenter;

import android.content.Context;
import android.support.v4.view.ViewPager;

import com.example.patternapplication.model.marker.WeatherMarker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.ClusterManager;

import viked.weathermap.ui.map.view.IMapView;
import viked.weathermap.ui.map.view.MapClusterRenderer;
import viked.weathermap.ui.map.view.MapPopupAdapter;

/**
 * Created by Eugeniy Shein on 29.09.2016.
 */

public class MapPresenterImpl implements IMapPresenter, MapClusterRenderer.Callback, MapPopupAdapter.Callback, GoogleMap.OnMapClickListener {

    private IMapView mapView;
    private Context context;

    private GoogleMap map;
    private ClusterManager<WeatherMarker> clusterManager;
    private WeatherMarker chosenMarker;
    private boolean cameraMoved = false;

    public MapPresenterImpl(IMapView mapView, Context context) {
        this.mapView = mapView;
        this.context = context;
    }

    @Override
    public void initViewPager(ViewPager viewPager) {

    }

    @Override
    public void initMap(MapView mapView) {
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        googleMap.setOnMapClickListener(this);
        clusterManager = new ClusterManager<>(context, googleMap);
        clusterManager.getMarkerCollection().setOnInfoWindowAdapter(new MapPopupAdapter(this));
        googleMap.setOnCameraChangeListener(clusterManager);
        googleMap.setOnMarkerClickListener(clusterManager);
        map.setInfoWindowAdapter(clusterManager.getMarkerManager());
        clusterManager.setRenderer(new MapClusterRenderer(this, context, map, clusterManager));
        clusterManager.setOnClusterItemClickListener(marker -> {
            chosenMarker = marker;
            return false;
        });
        updateView();
    }

    @Override
    public void updateView() {
        // getPresenter().requestUpdate();

    }

    @Override
    public void selectMarker(Marker marker) {
        if (chosenMarker != null && marker.getPosition().equals(chosenMarker.getPosition())) {
            marker.showInfoWindow();
            if (!cameraMoved) {
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 5));
                cameraMoved = true;
            }
        }
    }

    @Override
    public WeatherMarker getChosenMarker() {
        return chosenMarker;
    }

    @Override
    public void onMapClick(LatLng latLng) {
        //getPresenter()::addLocation
    }
}
