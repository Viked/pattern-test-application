package com.example.patternapplication.view.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.patternapplication.WeatherApplication;
import com.example.patternapplication.presenter.IPresenter;

/**
 * Created by 1 on 19.05.2016.
 */
public abstract class BaseFragment extends Fragment {

    private IPresenter presenter;

    public IPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = ((WeatherApplication)context.getApplicationContext()).getPresenter();
    }

}
