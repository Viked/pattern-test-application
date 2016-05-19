package com.example.patternapplication.view.fragments.marker;

import com.example.patternapplication.model.observable.MarkerDecorator;

import java.util.List;

/**
 * Created by 1 on 19.05.2016.
 */
public interface IMarkerListFragment {

    void addMarker();

    void setMarkers(List<MarkerDecorator> list);

}
