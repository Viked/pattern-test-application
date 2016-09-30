package viked.weathermap.ui.map.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import viked.weathermap.R;
import viked.weathermap.common.BaseActivity;
import viked.weathermap.dagger.ui.main.MainActivityComponent;
import viked.weathermap.dagger.ui.map.MapComponent;
import viked.weathermap.dagger.ui.map.MapModule;
import viked.weathermap.ui.map.presenter.IMapPresenter;

/**
 * Created by Eugeniy Shein on 27.09.2016.
 */

public class MapContainerFragment extends Fragment implements IMapView {

    @BindView(R.id.fragment_map_container_view_pager)
    ViewPager viewPager;

    @BindArray(R.array.fragment_map_container_titles)
    String[] fragmentTitles;

    @Inject
    private IMapPresenter presenter;

    private MapComponent component;

    private MapContainerPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map_container, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(view);
        initialDagger();
        adapter = new MapContainerPagerAdapter(getFragmentManager(), fragmentTitles);
        viewPager.setAdapter(adapter);
        presenter.initViewPager(viewPager);
    }

    @Override
    public void setItem(int position) {
        viewPager.setCurrentItem(position);
    }

    private void initialDagger() {
        if (getActivity() == null && !(getActivity() instanceof BaseActivity)) {
            return;
        }

        BaseActivity activity = (BaseActivity) getActivity();

        if (activity.getActivityComponent() != null && activity.getActivityComponent() instanceof MainActivityComponent) {
            MainActivityComponent activityComponent = activity.getActivityComponent();
            component = activityComponent.plus(new MapModule(this));
            component.inject(this);
        }
    }

    public MapComponent getComponent() {
        return component;
    }
}
