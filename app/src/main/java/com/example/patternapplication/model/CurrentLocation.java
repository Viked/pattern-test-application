package com.example.patternapplication.model;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

/**
 * Created by Initb on 12.05.2016.
 */
public class CurrentLocation {

    private GoogleApiClient apiClient;

    public Observable<Location> getCurrentLocation(Activity context) {

        PublishSubject<Location> locationPublishSubject = PublishSubject.create();
        GoogleApiClient.ConnectionCallbacks callback = new GoogleApiClient.ConnectionCallbacks() {
            @Override
            public void onConnected(@Nullable Bundle bundle) {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);
                    if (mLastLocation != null) {
                        locationPublishSubject.onNext(mLastLocation);
                        locationPublishSubject.onCompleted();

                    }
                }
            }

            @Override
            public void onConnectionSuspended(int i) {

            }
        };

        GoogleApiClient.OnConnectionFailedListener failedListener = connectionResult -> locationPublishSubject.onError(new Exception());

        apiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(callback)
                .addOnConnectionFailedListener(failedListener)
                .addApi(LocationServices.API)
                .build();

        apiClient.connect();

        return locationPublishSubject.doAfterTerminate(()->apiClient.disconnect());
    }
}
