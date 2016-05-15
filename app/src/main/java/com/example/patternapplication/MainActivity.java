package com.example.patternapplication;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.patternapplication.presenter.IPresenter;
import com.example.patternapplication.presenter.PresenterImpl;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private GoogleApiClient apiClient;

    private TextView textView;

    //open weather api key d45545a62ad42fe8a840303b8600c6d8
    IPresenter presenter = new PresenterImpl();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);
        presenter.onCreate(this);
        getSupportLoaderManager().initLoader(0, null, this);
        
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    public void updateView(String text){
        textView.setText(text);
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
