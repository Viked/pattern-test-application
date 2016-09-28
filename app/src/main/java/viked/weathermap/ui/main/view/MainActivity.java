package viked.weathermap.ui.main.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import viked.weathermap.R;
import viked.weathermap.common.BaseActivity;
import viked.weathermap.dagger.ui.main.MainActivityComponent;
import viked.weathermap.dagger.ui.main.MainActivityModule;
import viked.weathermap.ui.main.presenter.IMainActivityPresenter;

public class MainActivity extends BaseActivity implements IMainView, NavigationView.OnNavigationItemSelectedListener {

    //open weather api key d45545a62ad42fe8a840303b8600c6d8

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.navigation)
    NavigationView navigationView;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    private MainActivityComponent component;

    @Inject
    private IMainActivityPresenter presenter;

    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mTitle;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        component = getApplicationComponent().plus(new MainActivityModule(this));
        component.inject(this);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        initNavigationView();

        mTitle = getTitle();

        if (savedInstanceState == null) {
            initFrameLayout();
        }
    }

    private void initNavigationView() {
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        navigationView.setNavigationItemSelectedListener(this);
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
        tabLayout.setVisibility(viewPager != null ? View.VISIBLE : View.GONE);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        getNavigator().selectFragment(getSupportFragmentManager(), R.id.container, item.getItemId());
        return true;
    }

    private void initFrameLayout() {
        navigationView.setCheckedItem(R.id.nav_map);
    }

    @Nullable
    @Override
    public MainActivityComponent getActivityComponent() {
        return component;
    }
}
