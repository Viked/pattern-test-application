package com.example.patternapplication.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.patternapplication.WeatherApplication;
import com.example.patternapplication.presenter.IPresenter;

import java.util.Observer;

/**
 * Created by 1 on 19.05.2016.
 */
public abstract class BaseFragment extends Fragment implements Observer {

    private IPresenter presenter;

    public IPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = ((WeatherApplication)context.getApplicationContext()).getPresenter();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.addFragment(this);
    }

    @Override
    public void onDestroy() {
        presenter.removeFragment(this);
        super.onDestroy();
    }
}
