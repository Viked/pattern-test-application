package com.example.patternapplication.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.patternapplication.R;
import com.example.patternapplication.model.marker.DecoratorItemSettings;

import java.util.List;

/**
 * Created by viked on 27.05.16.
 */
public class SettingsDialogRecyclerViewAdapter extends RecyclerView.Adapter<SettingsDialogRecyclerViewAdapter.ViewHolder> {
    private List<DecoratorItemSettings> list;

    public SettingsDialogRecyclerViewAdapter(List<DecoratorItemSettings> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dialog_settings_list_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) ->
                    ((DecoratorItemSettings) itemView.getTag()).setChecked(isChecked));
        }

        public void bindData(DecoratorItemSettings data) {
            itemView.setTag(data);
            textView.setText(itemView.getContext().getString(data.getTitleId()));
            checkBox.setChecked(data.isChecked());
        }
    }


}
