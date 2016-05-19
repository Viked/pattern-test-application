package com.example.patternapplication.view.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.patternapplication.view.fragments.ListFragment;
import com.example.patternapplication.view.fragments.MapFragment;

/**
 * Created by Initb on 29.04.2016.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    private String[] titles;

    public MyPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MapFragment();
            case 1:
                return new ListFragment();
            default:
                return new Fragment();
        }
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
