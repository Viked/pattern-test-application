package com.example.patternapplication.view.fragments.marker;

import com.example.patternapplication.model.marker.MarkerDecorator;
import com.example.patternapplication.view.adapters.AbstractRecyclerViewAdapter;

import java.util.List;

/**
 * Created by 1 on 19.05.2016.
 */
public class MarkerAdapter extends AbstractRecyclerViewAdapter<MarkerDecorator, List<MarkerDecorator>> {

    public MarkerAdapter(ViewHolderFactory<MarkerDecorator> factory) {
        super(factory);
    }

    @Override
    public int getItemsSize(List<MarkerDecorator> items) {
        return items.size();
    }

    @Override
    public MarkerDecorator getItem(List<MarkerDecorator> items, int i) {
        return items.get(i);
    }
}
