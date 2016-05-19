package com.example.patternapplication.view.fragments;

import android.content.Context;
import android.view.View;

import com.example.patternapplication.model.observable.MarkerDecorator;
import com.example.patternapplication.presenter.IPresenter;
import com.example.patternapplication.view.adapters.AbstractRecyclerViewAdapter;
import com.example.patternapplication.view.adapters.AbstractViewHolder;
import com.example.patternapplication.view.fragments.interfaces.IMarkerListFragment;

import java.util.List;

/**
 * Created by 1 on 19.05.2016.
 */
public class MarkerListFragment extends BaseListFragment implements IMarkerListFragment {

    @Override
    public AbstractRecyclerViewAdapter initialAdapter() {
        return new MarkerAdapter(MarkerViewHolder::new);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter.attachMarkerListFragment(this);
    }


    @Override
    public void onResume() {
        super.onResume();
        if(presenter != null && presenter.getWeatherDB().getDBCursor() != null ){
            adapter.setCursor(presenter.getWeatherDB().getDBCursor());
        }
    }


    @Override
    public void setMarkers(List<MarkerDecorator> list) {

    }

    @Override
    public void addMarker() {
        notifyDataSetChanged();
    }

    static class MarkerViewHolder extends AbstractViewHolder<MarkerDecorator>{

        public MarkerViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindData(MarkerDecorator data) {

        }
    }

    static class MarkerAdapter extends AbstractRecyclerViewAdapter<MarkerDecorator,MarkerViewHolder,List<MarkerDecorator>>{

        public MarkerAdapter(ViewHolderFactory<MarkerDecorator, MarkerViewHolder> factory) {
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

}
