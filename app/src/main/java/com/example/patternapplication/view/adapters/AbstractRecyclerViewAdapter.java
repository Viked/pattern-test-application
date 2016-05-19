package com.example.patternapplication.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.patternapplication.R;

/**
 * Created by 1 on 19.05.2016.
 */
public abstract class AbstractRecyclerViewAdapter<T, C> extends RecyclerView.Adapter<AbstractViewHolder<T>> {

    public interface ViewHolderFactory<T> {
        AbstractViewHolder<T> create(View view);
    }

    public abstract int getItemsSize(C items);

    public abstract T getItem(C items, int i);

    private ViewHolderFactory<T> factory;

    private C items;

    public AbstractRecyclerViewAdapter(ViewHolderFactory<T> factory) {
        this.factory = factory;
    }

    public void setItems(C items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (items != null) {
            return getItemsSize(items);
        } else {
            return 0;
        }
    }

    @Override
    public AbstractViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_lict_row, parent, false);
        return factory.create(view);
    }

    @Override
    public void onBindViewHolder(AbstractViewHolder<T> holder, int position) {
        holder.bindData(getItem(items, position));
    }
}
