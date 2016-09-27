package viked.weathermap.ui.map.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import viked.weathermap.constants.MapFragmentConstants;

/**
 * Created by Eugeniy Shein on 27.09.2016.
 */

public class MapContainerPagerAdapter extends FragmentPagerAdapter {

    private String[] titles;

    public MapContainerPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == MapFragmentConstants.MAP_FRAGMENT) {
            return new MapFragment();
        } else {
            return new MarkersFragment();
        }
    }

    @Override
    public int getCount() {
        return MapFragmentConstants.FRAGMENTS_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}
