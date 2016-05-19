package com.example.patternapplication.model.data;

import java.util.Locale;

/**
 * Created by Initb on 18.05.2016.
 */
abstract public class Utils {

    public static String convertKelvin(Double k){
        return String.format(Locale.getDefault(), "Текущаяя температура: %.2f", (k - 273.15));
    }

}
