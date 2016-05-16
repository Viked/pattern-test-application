package com.example.patternapplication.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.example.patternapplication.R;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Initb on 16.05.2016.
 */
public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    public static String LOCATION_KEY = "location";

    LatLng point;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        double[] temp = getIntent().getDoubleArrayExtra(LOCATION_KEY);
        point = new LatLng(temp[0], temp[1]);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MarkerOptions temp = new MarkerOptions();
        temp.position(point).title("Тут");
        googleMap.addMarker(temp);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(point));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 15));

    }
}
