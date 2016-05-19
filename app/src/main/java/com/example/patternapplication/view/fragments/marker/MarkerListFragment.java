package com.example.patternapplication.view.fragments.marker;

import android.content.Context;

import com.example.patternapplication.model.observable.MarkerDecorator;
import com.example.patternapplication.view.fragments.BaseListFragment;

import java.util.List;

/**
 * Created by 1 on 19.05.2016.
 */
public class MarkerListFragment extends BaseListFragment<MarkerAdapter> implements IMarkerListFragment {

    @Override
    public MarkerAdapter initialAdapter() {
        return new MarkerAdapter(MarkerViewHolder::new);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getPresenter().attachMarkerListFragment(this);
    }


    @Override
    public void onResume() {
        super.onResume();
        if(getPresenter() != null){
            getAdapter().setItems(getPresenter().getMarkerList());
        }
    }


    @Override
    public void setMarkers(List<MarkerDecorator> list) {
        getAdapter().setItems(list);
    }

    @Override
    public void addMarker() {
        notifyDataSetChanged();
    }


}
