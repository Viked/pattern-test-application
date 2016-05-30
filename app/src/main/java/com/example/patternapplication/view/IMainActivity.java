package com.example.patternapplication.view;

import android.os.Bundle;

/**
 * Created by Initb on 18.05.2016.
 */
public interface IMainActivity{

    void loadDB();

    void reloadDB(Bundle args);

    void showFragment(int i);

    void showMassage(int stringId);

}
