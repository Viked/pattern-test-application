package viked.weathermap.ui.main.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.patternapplication.R;
import com.example.patternapplication.presenter.IPresenter;
import com.example.patternapplication.view.IMainActivity;
import com.google.android.gms.maps.model.LatLng;

import butterknife.BindView;
import butterknife.ButterKnife;
import viked.weathermap.common.BaseActivity;

public class MainActivity extends BaseActivity implements IMainActivity, NavigationView.OnNavigationItemSelectedListener {

    public static final String LAT = "lat";
    public static final String LNG = "lon";

    //open weather api key d45545a62ad42fe8a840303b8600c6d8

    @BindView(R.id.toolbar)
    private Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    private DrawerLayout mDrawerLayout;

    @BindView(R.id.navigation)
    private NavigationView navigationView;

    @BindView(R.id.tabs)
    private TabLayout tabLayout;

    private IPresenter presenter;

    private ActionBarDrawerToggle mDrawerToggle;

    private LatLng startCoordinates = null;

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

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        navigationView.setNavigationItemSelectedListener(this);
        mTitle = mDrawerTitle = getTitle();

        if (savedInstanceState == null) {
            startCoordinates = new LatLng(
                    getIntent().getDoubleExtra(LAT, 0),
                    getIntent().getDoubleExtra(LNG, 0));
            presenter.addLocation(startCoordinates);
        }
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
    public void setTitle(CharSequence title) {
        mTitle = title;
        toolbar.setTitle(mTitle);
    }

    @Override
    public void setupViewPager(ViewPager viewPager) {
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        getNavigator().selectFragment(getSupportFragmentManager(), R.id.container, item.getItemId());
        return true;
    }

}
