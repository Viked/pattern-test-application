package com.example.patternapplication.view.adapters;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.patternapplication.R;
import com.example.patternapplication.model.data.RequestedWeather;
import com.example.patternapplication.model.db.Utils;

public class WeatherRecyclerViewAdapter extends RecyclerView.Adapter<WeatherRecyclerViewAdapter.ViewHolder> {

    private Cursor cursor;

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_lict_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        cursor.moveToPosition(position);
        RequestedWeather weather = Utils.parseCursor(cursor);
        String text = weather.getSys().getCountry() + " "
                + com.example.patternapplication.model.data.Utils.convertKelvin(weather.getMain().getTemp());
        holder.textView.setText(text);
    }

    @Override
    public int getItemCount() {
        if (cursor != null) {
            return cursor.getCount();
        } else {
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
