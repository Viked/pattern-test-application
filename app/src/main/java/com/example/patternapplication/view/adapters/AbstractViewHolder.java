package com.example.patternapplication.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 1 on 19.05.2016.
 */
public abstract class AbstractViewHolder<T> extends RecyclerView.ViewHolder {

    public AbstractViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindData(T data);
}
