package com.example.patternapplication.presenter;

import android.app.Activity;

import com.example.patternapplication.MainActivity;
import com.example.patternapplication.model.CurrentLocation;
import com.example.patternapplication.model.WeatherApiRequestInterface;
import com.example.patternapplication.model.WeatherModel;
import com.example.patternapplication.model.db.DBModel;

/**
 * Created by Initb on 13.05.2016.
 */
public class Presenter {

    private DBModel dbModel;
    CurrentLocation currentLocation;
    WeatherApiRequestInterface apiRequestInterface;

    public void onCreate(Activity activity) {
        dbModel = new DBModel(activity);
        dbModel.open();
        currentLocation = new CurrentLocation();
        WeatherModel weatherModel = new WeatherModel();
        apiRequestInterface = weatherModel.getApiModel();
        currentLocation.getCurrentLocation(activity).subscribe((location) ->
                apiRequestInterface.getRoute(location.getLatitude(), location.getLongitude())
                        .subscribe(weather -> {
                            ((MainActivity)activity).updateView(weather.getRain().getTime().toString());
                            dbModel.addRec(weather);}));
    }

    public void onDestroy(){
        dbModel.close();
    }

    public DBModel getDbModel() {
        return dbModel;
    }
}
