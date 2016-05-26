package com.example.patternapplication.view;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.patternapplication.model.observable.MarkerDecorator;

/**
 * Created by Initb on 18.05.2016.
 */
public interface IMainActivity {

    void loadDB();

    void reloadDB(Bundle args);

    void showFragment(int i);

}
