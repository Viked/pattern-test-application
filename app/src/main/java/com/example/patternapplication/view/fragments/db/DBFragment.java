package com.example.patternapplication.view.fragments.db;

import android.database.Cursor;
import android.view.View;

import com.example.patternapplication.R;
import com.example.patternapplication.model.data.RequestedWeather;
import com.example.patternapplication.model.marker.decorator.TextDecorator;
import com.example.patternapplication.view.adapters.AbstractViewHolder;
import com.example.patternapplication.view.fragments.BaseListFragment;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
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

        private DateFormat formatDate = DateFormat.getDateInstance();
        private DateFormat formatTime = DateFormat.getTimeInstance();
        private Calendar calendar = Calendar.getInstance(Locale.getDefault());
        public DBViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindData(RequestedWeather data) {
            super.bindData(data);
            getImageView().setImageResource(R.drawable.weather_cloudy);
        }

        @Override
        public void deleteAction() {
            getPresenter().getWeatherDB().deleteRec((RequestedWeather) itemView.getTag());
            getPresenter().requestUpdate();
        }

        @Override
        public List<String> getContentList(RequestedWeather data) {
            List<String> temp = decorator.getText(data, itemView.getContext());
            Date date = new Date(data.getTime());
            long diff = calendar.getTimeInMillis() - date.getTime();
            long days = diff / (24 * 60 * 60 * 1000);
            String formattedDate;
            if(days>0){
                formattedDate = formatDate.format(date);
            }else {
                formattedDate = formatTime.format(date);
            }

            temp.add(0, formattedDate);
            temp.add(0, itemView.getContext().getString(R.string.last_update_time));
            return temp;
        }

        @Override
        public void bindView() {
            super.bindView();
            itemView.setOnClickListener(v -> getPresenter().showMarker(v.getTag()));
        }
    }

}
