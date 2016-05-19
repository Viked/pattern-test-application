package com.example.patternapplication.model.data;

import java.util.Locale;

/**
 * Created by Initb on 18.05.2016.
 */
abstract public class Utils {

    public static String convertKelvin(Double k){
        return "Текущаяя температура: " +  roundDouble(k - 273.15);
    }

    public static String roundDouble(Double d){
        return String.format(Locale.getDefault(), "%.2f", d);
    }


}
