package com.example.patternapplication.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.example.patternapplication.R;
import com.example.patternapplication.WeatherApplication;
import com.example.patternapplication.presenter.IPresenter;
import com.example.patternapplication.view.fragments.interfaces.IMapFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Initb on 18.05.2016.
 */
public class MapFragment extends Fragment implements IMapFragment, OnMapReadyCallback {

    private IPresenter presenter;
    private MapView mapView;
    private GoogleMap map;

    private final CompoundButton.OnCheckedChangeListener modeListener =
            (buttonView, isChecked) -> {
                if (isChecked) {
                    presenter.setMode((String) buttonView.getTag());
                }
            };

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

        RadioButton simple = (RadioButton) view.findViewById(R.id.simple);
        RadioButton withTwoTemperatures = (RadioButton) view.findViewById(R.id.with_two_temperatures);
        RadioButton bonus = (RadioButton) view.findViewById(R.id.bonus);
        simple.setOnCheckedChangeListener(modeListener);
        withTwoTemperatures.setOnCheckedChangeListener(modeListener);
        bonus.setOnCheckedChangeListener(modeListener);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = ((WeatherApplication)context.getApplicationContext()).getPresenter();
        presenter.attachMapFragment(this);
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
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void addMarker(MarkerOptions options) {
        map.addMarker(options).showInfoWindow();
    }

    @Override
    public void clearMap() {
        map.clear();
    }

    @Override
    public void updateView(MarkerOptions[] markerOptionsArray) {
        clearMap();
        for (MarkerOptions marker : markerOptionsArray){
            addMarker(marker);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        presenter.setMap(map = googleMap);
    }



}
