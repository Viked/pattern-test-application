package com.example.patternapplication.view;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.patternapplication.R;
import com.example.patternapplication.presenter.IPresenter;
import com.example.patternapplication.presenter.PresenterImpl;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, GoogleApiClient.ConnectionCallbacks,  GoogleApiClient.OnConnectionFailedListener{

    private GoogleApiClient apiClient;

    //open weather api key d45545a62ad42fe8a840303b8600c6d8
    IPresenter presenter = new PresenterImpl();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter.onCreate(this);
        presenter.initialViews(new TextView[]{
                (TextView)findViewById(R.id.textView1),
                (TextView)findViewById(R.id.textView2),
                (TextView)findViewById(R.id.textView3),
                (TextView)findViewById(R.id.textView4)
        });
        apiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        apiClient.connect();
        getSupportLoaderManager().initLoader(0, null, this);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        apiClient.disconnect();
        presenter.onDestroy();
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        presenter.DBLoaded(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new MyCursorLoader(this, presenter);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);
            if (mLastLocation != null) {
                presenter.setLocation(mLastLocation);
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    static class MyCursorLoader extends CursorLoader {

        IPresenter presenter;

        public MyCursorLoader(Context context, IPresenter presenter) {
            super(context);
            this.presenter = presenter;
        }

        @Override
        public Cursor loadInBackground() {
            return presenter.loadDB();
        }

    }

}
