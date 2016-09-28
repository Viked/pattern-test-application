package viked.weathermap.navigation;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

import viked.weathermap.R;
import viked.weathermap.constants.MapFragmentConstants;
import viked.weathermap.ui.history.view.HistoryFragment;
import viked.weathermap.ui.map.view.IMapView;
import viked.weathermap.ui.map.view.MapContainerFragment;
import viked.weathermap.ui.map.view.MapFragment;
import viked.weathermap.ui.map.view.MarkersFragment;
import viked.weathermap.ui.marker.view.MarkerSettingsFragment;
import viked.weathermap.ui.main.view.MainActivity;

/**
 * Created by Eugeniy Shein on 23.09.2016.
 */

public class Navigator {

    public void startMainActivity(Context context) {
        if (context != null) {
            Intent intentToLaunch = MainActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public void selectFragment(FragmentManager fm, int layoutId, int itemId) {
        Fragment fragment = null;
        switch (itemId) {
            case R.id.nav_map:
                fragment = selectMapFragment(fm, layoutId, MapFragmentConstants.MAP_FRAGMENT);
                break;
            case R.id.nav_markers:
                fragment = selectMapFragment(fm, layoutId, MapFragmentConstants.MARKERS_FRAGMENT);
                break;
            case R.id.nav_history:
                fragment = selectHistoryFragment(fm, layoutId);
                break;
            case R.id.nav_marker_settings:
                fragment = selectMarkerSettingsFragment(fm, layoutId);
                break;
        }

        if (fragment != null) {
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(layoutId, fragment, fragment.getClass().getSimpleName());
            fragmentTransaction.commit();
        }
    }

    private Fragment selectMapFragment(FragmentManager fm, int layoutId, int item) {
        Fragment fragment = fm.findFragmentById(layoutId);
        if (fragment != null && fragment instanceof MapContainerFragment && fragment.isVisible()) {
            ((IMapView) fragment).setItem(item);
            return null;
        } else {
            clearMapFragments(fm);
            fragment = new MapContainerFragment();
            fragment.setArguments(MapContainerFragment.getArguments(item));
            return fragment;
        }
    }

    private Fragment selectHistoryFragment(FragmentManager fm, int layoutId) {
        Fragment fragment = fm.findFragmentById(layoutId);
        if (fragment != null && fragment instanceof HistoryFragment && fragment.isVisible()) {
            return null;
        } else {
            return new HistoryFragment();
        }
    }

    private Fragment selectMarkerSettingsFragment(FragmentManager fm, int layoutId) {
        Fragment fragment = fm.findFragmentById(layoutId);
        if (fragment != null && fragment instanceof MarkerSettingsFragment && fragment.isVisible()) {
            return null;
        } else {
            return new MarkerSettingsFragment();
        }
    }

    private void clearMapFragments(FragmentManager fm) {
        List<Fragment> fragments = fm.getFragments();
        if (fragments != null && !fragments.isEmpty()) {
            for (Fragment fragment : fragments) {
                if (fragment instanceof MapFragment || fragment instanceof MarkersFragment) {
                    deleteFragment(fm, fragment);
                }
            }
        }
    }

    private void deleteFragment(FragmentManager fm, Fragment fragment) {
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.remove(fragment);
        try {
            fragmentTransaction.commitNowAllowingStateLoss();
        } catch (Exception e) {
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

}
