package com.example.patternapplication.view;

import android.os.Bundle;
import android.view.LayoutInflater;

/**
 * Created by Initb on 18.05.2016.
 */
public interface IMainActivity {

    void loadDB();

    void reloadDB(Bundle args);

    LayoutInflater getLayoutInflater();

}
