package com.example.patternapplication.view.fragments.db;

import android.database.Cursor;

import com.example.patternapplication.model.data.RequestedWeather;
import com.example.patternapplication.model.db.Utils;
import com.example.patternapplication.view.adapters.AbstractRecyclerViewAdapter;

/**
 * Created by 1 on 19.05.2016.
 */
public class DBAdapter extends AbstractRecyclerViewAdapter<RequestedWeather, Cursor> {

    public DBAdapter(ViewHolderFactory<RequestedWeather> factory) {
        super(factory);
    }

    @Override
    public void setItems(Cursor items) {
        super.setItems(items);
    }

    @Override
    public RequestedWeather getItem(Cursor items, int i) {
        items.moveToPosition(i);
        return Utils.parseCursor(items);
    }

    @Override
    public int getItemsSize(Cursor items) {
        if (items.isClosed() && items.getCount() < 0) {
            return 0;
        } else {
            return items.getCount();
        }
    }

}
