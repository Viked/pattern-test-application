package com.example.patternapplication.view.fragments.db;

import android.content.Context;
import android.database.Cursor;
import com.example.patternapplication.view.fragments.BaseListFragment;

/**
 * Created by Initb on 18.05.2016.
 */
public class DBFragment extends BaseListFragment<DBAdapter> implements IDBFragment {


    @Override
    public DBAdapter initialAdapter() {
        return new DBAdapter(DBViewHolder::new);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getPresenter().setDBFragment(this);
    }

    @Override
    public void setList(Cursor cursor) {
        if(getAdapter() !=null) {
            getAdapter().setItems(cursor);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getPresenter() != null && getPresenter().getWeatherDB().getDBCursor() != null ){
            getAdapter().setItems(getPresenter().getWeatherDB().getDBCursor());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPresenter().setDBFragment(null);
    }
}
