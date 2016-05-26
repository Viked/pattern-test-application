package com.example.patternapplication.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.patternapplication.R;

/**
 * Created by 1 on 19.05.2016.
 */
public abstract class AbstractViewHolder<T> extends RecyclerView.ViewHolder {

    private TextView textView;
    private ImageView imageView;

    public TextView getTextView() {
        return textView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public AbstractViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.text);
        imageView = (ImageView) itemView.findViewById(R.id.image);
    }

    public void bindData(T data) {
        itemView.setTag(data);
    }

    public abstract void bindView();
    public abstract void deleteAction();
}
