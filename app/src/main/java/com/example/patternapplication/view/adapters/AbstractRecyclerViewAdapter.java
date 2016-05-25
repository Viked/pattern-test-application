package com.example.patternapplication.view.adapters;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private class ItemTouchHelperCallback extends ItemTouchHelper.SimpleCallback {

        public ItemTouchHelperCallback() {
            super(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView,
                              RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {

            viewHolder.itemView.setOnClickListener(v -> notifyItemChanged(viewHolder.getAdapterPosition()));
        }

        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            AbstractViewHolder<T> holder = (AbstractViewHolder<T>) viewHolder;
            float x = dX / (holder.swipeLayout.getWidth() / holder.deleteButtonLayout.getWidth());
            holder.swipeLayout.setX(x);
            holder.deleteButtonLayout.setX(holder.swipeLayout.getX() - holder.deleteButtonLayout.getWidth());
            holder.viewButtonLayout.setX(holder.swipeLayout.getX() + holder.swipeLayout.getWidth());
        }
    }
}
