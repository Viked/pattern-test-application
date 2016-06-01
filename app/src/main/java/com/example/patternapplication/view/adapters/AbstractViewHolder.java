package com.example.patternapplication.view.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
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
    private CardView cardView;

    public TextView getTextView() {
        return textView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public AbstractViewHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.cardView);
        textView = (TextView) itemView.findViewById(R.id.text);
        imageView = (ImageView) itemView.findViewById(R.id.image);
        cardView.setLayoutParams(
                new CardView.LayoutParams(CardView.LayoutParams.MATCH_PARENT,
                        CardView.LayoutParams.WRAP_CONTENT));
        bindView();
    }

    public void bindData(T data) {
        itemView.setTag(data);
    }

    public abstract void bindView();
    public abstract void deleteAction();
}
