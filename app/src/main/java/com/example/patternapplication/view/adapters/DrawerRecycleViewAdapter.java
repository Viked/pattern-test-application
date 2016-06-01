package com.example.patternapplication.view.adapters;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.patternapplication.R;
import com.example.patternapplication.view.ViewConstants;

/**
 * Created by viked on 01.06.16.
 */
public class DrawerRecycleViewAdapter extends RecyclerView.Adapter<DrawerRecycleViewAdapter.ViewHolder> {

    public interface Callback {
        void onClick(int position);
    }

    private int[] titlesRes = ViewConstants.drawerTitlesRes;

    private Callback callback;

    public DrawerRecycleViewAdapter(Callback callback) {
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_content_row, parent, false);
        view.setOnClickListener(v -> callback.onClick((int) v.getTag()));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.setText(holder.itemView.getContext().getString(titlesRes[position]));
    }

    @Override
    public int getItemCount() {
        return titlesRes.length;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setHasFixedSize(true);
        super.onAttachedToRecyclerView(recyclerView);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }

        public void setText(String text) {
            textView.setText(text);
        }

    }

}
