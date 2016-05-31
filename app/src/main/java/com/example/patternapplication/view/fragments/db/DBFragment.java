package com.example.patternapplication.view.fragments.db;

import android.database.Cursor;
import android.view.View;

import com.example.patternapplication.R;
import com.example.patternapplication.model.data.RequestedWeather;
import com.example.patternapplication.model.marker.decorator.TextDecorator;
import com.example.patternapplication.view.adapters.AbstractViewHolder;
import com.example.patternapplication.view.fragments.BaseListFragment;

import java.text.DateFormat;
import java.util.Date;
import java.util.Observable;

/**
 * Created by Initb on 18.05.2016.
 */
public class DBFragment extends BaseListFragment<DBAdapter> {

    TextDecorator decorator;

    @Override
    public DBAdapter initialAdapter() {
        return new DBAdapter(DBViewHolder::new);
    }

    @Override
    public void update(Observable observable, Object data) {
        decorator = getPresenter().getDecorator();
        Cursor cursor = getPresenter().getWeatherDB().getDBCursor();
        if (getAdapter() != null && cursor != null) {
            getAdapter().setItems(cursor);
        }
    }

    private class DBViewHolder extends AbstractViewHolder<RequestedWeather> {

        private DateFormat format = DateFormat.getDateTimeInstance();

        public DBViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindData(RequestedWeather data) {
            super.bindData(data);
            StringBuffer text = new StringBuffer(itemView.getContext().getString(R.string.last_update_time))
                    .append(": ")
                    .append(format.format(new Date(data.getTime())))
                    .append("\n")
                    .append(decorator.getText(data, itemView.getContext()));
            getTextView().setText(text.toString());
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
