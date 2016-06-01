package com.example.patternapplication.view.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
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

    private Bitmap delete;

    private int swipeColor;

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

    public C getItems() {
        return items;
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
        delete = BitmapFactory.decodeResource(recyclerView.getContext().getResources(),
                R.drawable.delete);
        swipeColor = ContextCompat.getColor(recyclerView.getContext(), R.color.colorSwipe);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);

    }

    private class ItemTouchHelperCallback extends ItemTouchHelper.SimpleCallback {

        private Paint p = new Paint();

        public ItemTouchHelperCallback() {
            super(0, ItemTouchHelper.RIGHT);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView,
                              RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
            ((AbstractViewHolder<T>) viewHolder).deleteAction();
        }

        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                View itemView = viewHolder.itemView;
                float height = (float) itemView.getBottom() - (float) itemView.getTop();
                float width = height / 3;
                p.setColor(swipeColor);
                RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX, (float) itemView.getBottom());
                c.drawRect(background, p);

                RectF icon_dest = new RectF((float) itemView.getLeft() + width, (float) itemView.getTop() + width, (float) itemView.getLeft() + 2 * width, (float) itemView.getBottom() - width);
                if (icon_dest.right < dX) {
                    c.drawBitmap(delete, null, icon_dest, p);
                }
            }
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        }
    }
}
