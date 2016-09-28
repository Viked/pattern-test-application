package viked.weathermap.ui.main.presenter;

import android.content.Context;

import viked.weathermap.ui.main.view.IMainView;
import viked.weathermap.utils.ConnectionUtils;

/**
 * Created by Eugeniy Shein on 28.09.2016.
 */

public class MainActivityPresenterImpl implements IMainActivityPresenter {

    private ConnectionUtils connectionUtils;
    private IMainView view;
    private Context context;


    public MainActivityPresenterImpl(Context context,
                                     IMainView view,
                                     ConnectionUtils connectionUtils) {
        this.context = context;
        this.connectionUtils = connectionUtils;
        this.view = view;
    }

}
