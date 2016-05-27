package com.example.patternapplication.view.fragments.db;

import android.database.Cursor;
import android.view.View;

import com.example.patternapplication.R;
import com.example.patternapplication.model.data.RequestedWeather;
import com.example.patternapplication.model.data.Utils;
import com.example.patternapplication.view.adapters.AbstractViewHolder;
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
        Cursor cursor = getPresenter().getWeatherDB().getDBCursor();
        if (getAdapter()!= null && cursor != null) {
            getAdapter().setItems(cursor);
        }
    }

    private class DBViewHolder extends AbstractViewHolder<RequestedWeather> {


        public DBViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindData(RequestedWeather data) {
            super.bindData(data);
            String text = data.getSys().getCountry() + " "
                    + Utils.convertKelvin(data.getMain().getTemp());
            getTextView().setText(text);
            getImageView().setImageResource(R.drawable.weather_cloudy);

        }

        @Override
        public void deleteAction() {
            getPresenter().getWeatherDB().deleteRec((RequestedWeather) itemView.getTag());
            getPresenter().requestUpdate();
        }

        @Override
        public void bindView() {
            itemView.setOnClickListener(v -> getPresenter().showMarker(v.getTag()));
        }
    }

}
