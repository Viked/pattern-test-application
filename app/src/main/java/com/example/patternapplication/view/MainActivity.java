package com.example.patternapplication.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.patternapplication.R;
import com.example.patternapplication.WeatherApplication;
import com.example.patternapplication.model.db.DBLoader;
import com.example.patternapplication.model.observable.MarkerDecorator;
import com.example.patternapplication.presenter.IPresenter;
import com.example.patternapplication.view.adapters.MyPagerAdapter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements IMainActivity, LoaderManager.LoaderCallbacks<Cursor>, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final int MY_DB_ID = 0;

    //open weather api key d45545a62ad42fe8a840303b8600c6d8
    private IPresenter presenter;

    private GoogleApiClient apiClient;

    private MyPagerAdapter pagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String[] titles = new String[]{getString(R.string.fragment_title_map),
                getString(R.string.fragment_title_marker_list),
                getString(R.string.fragment_title_db)};
        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), titles);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        if(savedInstanceState==null) {
            apiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter = ((WeatherApplication) getApplication()).getPresenter();
        presenter.setActivity(this);
        if(apiClient != null){
            apiClient.connect();
        }
    }

    @Override
    protected void onDestroy() {
        if(apiClient != null){
            apiClient.disconnect();
        }
        super.onDestroy();
    }

    @Override
    public void reloadDB(Bundle args) {
        getSupportLoaderManager().restartLoader(MY_DB_ID, args, this);
    }

    @Override
    public void loadDB() {
        getSupportLoaderManager().initLoader(MY_DB_ID, null, this);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);
            if (mLastLocation != null) {
                presenter.addLocation(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude()));
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        presenter.update();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new DBLoader(this, args, presenter.getWeatherDB());
    }


}
