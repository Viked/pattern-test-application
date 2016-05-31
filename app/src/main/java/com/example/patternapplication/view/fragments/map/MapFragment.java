package com.example.patternapplication.view.fragments.map;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.patternapplication.R;
import com.example.patternapplication.model.marker.WeatherMarker;
import com.example.patternapplication.view.fragments.BaseFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

import java.util.List;
import java.util.Observable;

/**
 * Created by Initb on 18.05.2016.
 */
public class MapFragment extends BaseFragment implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap map;
    private ClusterManager<WeatherMarker> mClusterManager;
    private WeatherMarker chosenMarker;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = (MapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
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
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        googleMap.setOnMapClickListener(getPresenter()::addLocation);
        getPresenter().requestUpdate();
        mClusterManager = new ClusterManager<>(getContext(), googleMap);
        mClusterManager.getMarkerCollection().setOnInfoWindowAdapter(new PopupAdapter(getActivity().getLayoutInflater()));
        googleMap.setOnCameraChangeListener(mClusterManager);
        googleMap.setOnMarkerClickListener(mClusterManager);
        map.setInfoWindowAdapter(mClusterManager.getMarkerManager());
        mClusterManager.setRenderer(new MyClusterRenderer(getContext(), map, mClusterManager));
        mClusterManager.setOnClusterItemClickListener(marker -> {
            chosenMarker = marker;
            return false;
        });
    }

    @Override
    public void update(Observable observable, Object data) {
        if (map != null) {
            mClusterManager.clearItems();
            List<WeatherMarker> weatherMarkers = getPresenter().getMarkerList();
            if (weatherMarkers.size() > 0) {
                mClusterManager.addItems(weatherMarkers);
            }
            mClusterManager.cluster();
            chosenMarker = getPresenter().getActiveMarker();
            if (chosenMarker != null) {
                for (Marker marker : mClusterManager.getMarkerCollection().getMarkers()) {
                    if (marker.getPosition().equals(chosenMarker.getPosition())) {
                        marker.showInfoWindow();
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 5));
                        break;
                    }
                }
            }
        }
    }

    class PopupAdapter implements GoogleMap.InfoWindowAdapter {

        private View popup = null;
        private LayoutInflater inflater = null;

        public PopupAdapter(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }

        @Override
        public View getInfoContents(Marker marker) {
            if (popup == null) {
                popup = inflater.inflate(R.layout.popup, null);
            }
            ImageView imageView = (ImageView) popup.findViewById(R.id.image);
            imageView.setImageResource(R.drawable.weather_cloudy);
            TextView tv = (TextView) popup.findViewById(R.id.title);
            tv.setText(R.string.marker_title);
            if (chosenMarker != null) {
                tv = (TextView) popup.findViewById(R.id.snippet);
                tv.setText(chosenMarker.getTextDecorator()
                        .getText(chosenMarker.getWeather(), getContext()));
            }
            return (popup);
        }
    }

    class MyClusterRenderer extends DefaultClusterRenderer<WeatherMarker> {

        public MyClusterRenderer(Context context, GoogleMap map,
                                 ClusterManager<WeatherMarker> clusterManager) {
            super(context, map, clusterManager);
        }

        @Override
        protected void onBeforeClusterItemRendered(WeatherMarker item, MarkerOptions markerOptions) {
            super.onBeforeClusterItemRendered(item, markerOptions);
        }

        @Override
        protected void onClusterItemRendered(WeatherMarker clusterItem, Marker marker) {
            super.onClusterItemRendered(clusterItem, marker);
            if (chosenMarker != null && marker.getPosition().equals(chosenMarker.getPosition())) {
                marker.showInfoWindow();
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 5));
            }
        }
    }
}


