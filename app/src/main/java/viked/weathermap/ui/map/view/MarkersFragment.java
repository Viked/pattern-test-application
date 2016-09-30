package viked.weathermap.ui.map.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import viked.weathermap.R;
import viked.weathermap.ui.map.presenter.IMapPresenter;

/**
 * Created by Eugeniy Shein on 27.09.2016.
 */

public class MarkersFragment extends Fragment {

    @BindView(R.id.list)
    RecyclerView recyclerView;

    @Inject
    private IMapPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(view);
        ((MapContainerFragment) getParentFragment()).getComponent().inject(this);

    }
}
