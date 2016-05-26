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

    private ImageButton deleteButton;
    private ImageButton viewButton;

    protected RelativeLayout swipeLayout;
    protected RelativeLayout deleteButtonLayout;
    protected RelativeLayout viewButtonLayout;

    public TextView getTextView() {
        return textView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public ImageButton getDeleteButton() {
        return deleteButton;
    }

    public ImageButton getViewButton() {
        return viewButton;
    }

    public AbstractViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.text);
        imageView = (ImageView) itemView.findViewById(R.id.image);
        deleteButton = (ImageButton) itemView.findViewById(R.id.delete_button);
        viewButton = (ImageButton) itemView.findViewById(R.id.view_button);
        swipeLayout = (RelativeLayout) itemView.findViewById(R.id.swipable_content);
        deleteButtonLayout = (RelativeLayout) itemView.findViewById(R.id.content_delete_button);
        deleteButtonLayout.setVisibility(View.VISIBLE);
        viewButtonLayout = (RelativeLayout) itemView.findViewById(R.id.content_view_button);
        viewButtonLayout.setVisibility(View.VISIBLE);
        viewButtonLayout.setX(1000);
        deleteButtonLayout.setX(-1000);
    }

    public void bindData(T data) {
        itemView.setTag(data);
    }

    public abstract View.OnClickListener deleteButtonOnClick();

    public abstract View.OnClickListener viewButtonOnClick();
}
