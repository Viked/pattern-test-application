package com.example.patternapplication.view.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.patternapplication.R;
import com.example.patternapplication.WeatherApplication;
import com.example.patternapplication.presenter.IPresenter;
import com.example.patternapplication.view.adapters.AbstractRecyclerViewAdapter;
import com.example.patternapplication.view.adapters.MarkerRecyclerViewAdapter;

/**
 * Created by 1 on 19.05.2016.
 */
public abstract class BaseListFragment<T extends AbstractRecyclerViewAdapter> extends Fragment {

    private IPresenter presenter;

    private T adapter;

    public abstract T initialAdapter();

    public T getAdapter() {
        return adapter;
    }

    public IPresenter getPresenter() {
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((RecyclerView) view).setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = initialAdapter();
        ((RecyclerView) view).setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = ((WeatherApplication)context.getApplicationContext()).getPresenter();
    }

    public void notifyDataSetChanged(){
        adapter.notifyDataSetChanged();
    }

}
