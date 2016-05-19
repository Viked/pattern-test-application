package com.example.patternapplication.view;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.patternapplication.R;
import com.example.patternapplication.WeatherApplication;
import com.example.patternapplication.model.db.IDBModel;
import com.example.patternapplication.presenter.IPresenter;
import com.example.patternapplication.view.adapters.MyPagerAdapter;

public class MainActivity extends AppCompatActivity implements IMainActivity, LoaderManager.LoaderCallbacks<Cursor>{

    private static final int MY_DB_ID = 0;

    //open weather api key d45545a62ad42fe8a840303b8600c6d8
    private IPresenter presenter;

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

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter = ((WeatherApplication)getApplication()).getPresenter();
        presenter.setActivity(this);
    }

    @Override
    public void reloadDB() {
        getSupportLoaderManager().restartLoader(MY_DB_ID, null, this);
    }

    @Override
    public void loadDB(){
        getSupportLoaderManager().initLoader(MY_DB_ID, null, this);
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
        return new MyCursorLoader(this, presenter.getWeatherDB());
    }


    static class MyCursorLoader extends CursorLoader {

        private IDBModel db;

        public MyCursorLoader(Context context, IDBModel db) {
            super(context);
            this.db = db;
        }

        @Override
        public Cursor loadInBackground() {
            return db.getNewDBCursor();
        }

    }

}
