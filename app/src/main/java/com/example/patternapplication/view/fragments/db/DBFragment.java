package com.example.patternapplication.view.fragments.db;

import com.example.patternapplication.view.fragments.BaseListFragment;

import java.util.Observable;

/**
 * Created by Initb on 18.05.2016.
 */
public class DBFragment extends BaseListFragment<DBAdapter> {

    @Override
    public DBAdapter initialAdapter() {
        return new DBAdapter(DBViewHolder::new);
    }

    @Override
    public void update(Observable observable, Object data) {
        getAdapter().setItems(getPresenter().getWeatherDB().getDBCursor());
    }
}
