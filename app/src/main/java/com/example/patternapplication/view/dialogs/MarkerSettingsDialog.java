package com.example.patternapplication.view.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.patternapplication.R;
import com.example.patternapplication.WeatherApplication;
import com.example.patternapplication.model.marker.DecoratorItemSettings;
import com.example.patternapplication.model.marker.DecoratorSettings;
import com.example.patternapplication.view.adapters.SettingsDialogRecyclerViewAdapter;

import java.util.List;

/**
 * Created by viked on 27.05.16.
 */
public class MarkerSettingsDialog extends AppCompatDialogFragment {

    public MarkerSettingsDialog() {
        setStyle(AppCompatDialogFragment.STYLE_NO_TITLE, R.style.CustomDialog);
    }

    private List<DecoratorItemSettings> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((RecyclerView) view).setLayoutManager(new LinearLayoutManager(getContext()));
        list = DecoratorSettings.getSettings(getContext());
        ((RecyclerView) view).setAdapter(new SettingsDialogRecyclerViewAdapter(list));
        ((RecyclerView) view).setHasFixedSize(true);
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        dialog.setTitle(R.string.dialog_marker_settings_title);
        super.setupDialog(dialog, style);
    }

    @Override
    public void onStop() {
        DecoratorSettings.setSettings(getContext(), list);
        ((WeatherApplication)getContext().getApplicationContext()).getPresenter().requestUpdate();
        super.onStop();
    }
}
