package com.example.patternapplication.view.fragments.marker;

import com.example.patternapplication.view.fragments.BaseListFragment;

import java.util.Observable;

/**
 * Created by 1 on 19.05.2016.
 */
public class MarkerListFragment extends BaseListFragment<MarkerAdapter> {

    @Override
    public MarkerAdapter initialAdapter() {
        return new MarkerAdapter(MarkerViewHolder::new);
    }

    @Override
    public void update(Observable observable, Object data) {
        getAdapter().setItems(getPresenter().getMarkerList());
    }
}
