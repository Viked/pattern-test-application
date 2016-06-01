package com.example.patternapplication.view.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.patternapplication.R;

import java.util.List;

/**
 * Created by 1 on 19.05.2016.
 */
public abstract class AbstractViewHolder<T> extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private CardView cardView;
    private RecyclerView recyclerView;
    private ContentRecycleViewAdapter adapter;

    public ImageView getImageView() {
        return imageView;
    }

    public AbstractViewHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.cardView);
        imageView = (ImageView) itemView.findViewById(R.id.image);
        adapter = new ContentRecycleViewAdapter();
        recyclerView = (RecyclerView) itemView.findViewById(R.id.content);
        recyclerView.setAdapter(adapter);

        bindView();
    }

    public void bindData(T data) {
        itemView.setTag(data);
        addContent(getContentList(data));
    }

    public void bindView() {
        cardView.setLayoutParams(
                new CardView.LayoutParams(CardView.LayoutParams.MATCH_PARENT,
                        CardView.LayoutParams.WRAP_CONTENT));
    }

    public abstract void deleteAction();

    public abstract List<String> getContentList(T data);

    public void addContent(List<String> list) {
        adapter.addItems(list);
    }
}
