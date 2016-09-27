package viked.weathermap.ui.map.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.patternapplication.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import viked.weathermap.ui.map.presenter.IMapPresenter;

import static viked.weathermap.constants.MapFragmentConstants.SELECT_FRAGMENT_INDEX_KEY;

/**
 * Created by Eugeniy Shein on 27.09.2016.
 */

public class MapContainerFragment extends Fragment implements IMapView {

    @BindView(R.id.fragment_map_container_view_pager)
    private ViewPager viewPager;

    @Inject
    private IMapPresenter presenter;

    private MapContainerPagerAdapter fragmentAdapter;

    public static Bundle getArguments(int selectedPage){
        Bundle args = new Bundle();
        args.putInt(SELECT_FRAGMENT_INDEX_KEY, selectedPage);
        return args;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map_container, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(view);
        String[] titles = new String[]{getString(R.string.fragment_title_map),
                getString(R.string.fragment_title_marker_list)};
        fragmentAdapter = new MapContainerPagerAdapter(getFragmentManager(), titles);
        viewPager.setAdapter(fragmentAdapter);
        presenter.initViewPager(viewPager);
    }

    @Override
    public void setItem(int position) {
        viewPager.setCurrentItem(position);
    }
}
