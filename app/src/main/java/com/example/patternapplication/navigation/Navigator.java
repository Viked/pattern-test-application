package com.example.patternapplication.navigation;

import android.content.Context;
import android.content.Intent;

import com.example.patternapplication.ui.main.view.MainActivity;

/**
 * Created by Eugeniy Shein on 23.09.2016.
 */

public class Navigator {

    private void startMainActivity(Context context, double lan, double lng) {
        if (context != null) {
            Intent intentToLaunch = MainActivity.getCallingIntent(context, lan, lng);
            context.startActivity(intentToLaunch);
        }
    }
}
