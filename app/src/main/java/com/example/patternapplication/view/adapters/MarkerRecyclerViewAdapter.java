package com.example.patternapplication.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.patternapplication.R;
import com.example.patternapplication.model.data.RequestedWeather;
import com.example.patternapplication.model.observable.MarkerDecorator;

import java.util.List;

/**
 * Created by 1 on 19.05.2016.
 */
public class MarkerRecyclerViewAdapter extends RecyclerView.Adapter<MarkerRecyclerViewAdapter.ViewHolder> {

    private List<MarkerDecorator> list;

    public void setList(List<MarkerDecorator> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public MarkerRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_lict_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MarkerRecyclerViewAdapter.ViewHolder holder, int position) {
        RequestedWeather weather = list.get(position).getWeather();
        String text = weather.getSys().getCountry() + " "
                + list.get(position).getLocation().toString();
        holder.textView.setText(text);
    }

    @Override
    public int getItemCount() {
        if(list != null){
            return list.size();
        }else {
            return 0;
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view;
        }
    }

}
