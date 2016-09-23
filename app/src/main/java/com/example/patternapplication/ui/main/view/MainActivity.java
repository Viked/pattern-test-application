package com.example.patternapplication.ui.main.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.Bind;

import com.example.patternapplication.R;
import com.example.patternapplication.WeatherApplication;
import com.example.patternapplication.common.BaseActivity;
import com.example.patternapplication.presenter.IPresenter;
import com.example.patternapplication.view.IMainActivity;
import com.example.patternapplication.view.adapters.MyPagerAdapter;
import com.example.patternapplication.view.dialogs.MarkerSettingsDialog;
import com.google.android.gms.maps.model.LatLng;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements IMainActivity, LoaderManager.LoaderCallbacks<Cursor>, NavigationView.OnNavigationItemSelectedListener {

    private static final int MY_DB_ID = 0;

    private static final int MENU_SETTINGS_ID = 0;

    public static final String LAT = "lat";
    public static final String LNG = "lon";

    //open weather api key d45545a62ad42fe8a840303b8600c6d8
    private IPresenter presenter;

    private MyPagerAdapter pagerAdapter;

    private Toolbar toolbar;

    private ViewPager mViewPager;

    private LatLng startCoordinates = null;


    @Bind(R.id.btn_LoadData)
    DrawerLayout mDrawerLayout;

    private NavigationView navigationView;

    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;

    private CharSequence mTitle;


    public static Intent getCallingIntent(Context context, double lat, double lng) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(LAT, lat);
        intent.putExtra(LNG, lng);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);
        mTitle = mDrawerTitle = getTitle();

        String[] titles = new String[]{getString(R.string.fragment_title_map),
                getString(R.string.fragment_title_marker_list),
                getString(R.string.fragment_title_db)};
        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), titles);

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        if (savedInstanceState == null) {
            startCoordinates = new LatLng(
                    getIntent().getDoubleExtra(SplashActivity.LAT, SplashActivity.romeLatitude),
                    getIntent().getDoubleExtra(SplashActivity.LNG, SplashActivity.romeLongitude));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu = navigationView.getMenu();
        MenuItem settings = menu.add(Menu.NONE, MENU_SETTINGS_ID, Menu.NONE, R.string.menu_item_settings);
        settings.setIcon(R.drawable.ic_settings);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_SETTINGS_ID:
                new MarkerSettingsDialog().show(getSupportFragmentManager(),
                        MarkerSettingsDialog.class.getName());
                return true;
            default:
                if (mDrawerToggle.onOptionsItemSelected(item)) {
                    return true;
                } else {
                    return super.onOptionsItemSelected(item);
                }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter = ((WeatherApplication) getApplication()).getPresenter();
        presenter.setActivity(this);
        if (startCoordinates != null) {
            presenter.addLocation(startCoordinates);
        }
    }

    @Override
    protected void onDestroy() {
        presenter.setActivity(null);
        super.onDestroy();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void showMassage(int stringId) {
        Snackbar snackbar = Snackbar
                .make(mViewPager, stringId, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void reloadDB(Bundle args) {
        if (getSupportLoaderManager().getLoader(MY_DB_ID) != null) {
            getSupportLoaderManager().restartLoader(MY_DB_ID, args, this);
        }
    }

    @Override
    public void loadDB() {
        getSupportLoaderManager().initLoader(MY_DB_ID, null, this);
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

    @Override
    public void showFragment(int i) {
        mViewPager.setCurrentItem(i, true);
    }

    private void selectItem(int position) {
        /*
        // Create a new fragment and specify the planet to show based on position
        Fragment fragment = new PlanetFragment();
        Bundle args = new Bundle();
        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
        fragment.setArguments(args);

        // Insert the fragment by replacing any existing fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
        */
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        toolbar.setTitle(mTitle);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_SETTINGS_ID:
                new MarkerSettingsDialog().show(getSupportFragmentManager(),
                        MarkerSettingsDialog.class.getName());
                return true;
            default:
                return false;

        }
    }
}
