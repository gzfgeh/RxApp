package com.gzfgeh.nbapp.Fragment;

import android.support.v4.app.Fragment;

import com.gzfgeh.nbapp.Component.ActivityComponent;
import com.gzfgeh.nbapp.Component.ActivityComponentFactory;

/**
 * Description:
 * Created by guzhenfu on 2016/10/25 14:21.
 */

public class BaseFragment extends Fragment {
    protected ActivityComponent activityComponent;

    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = ActivityComponentFactory.create(getActivity());
        }
        return activityComponent;
    }
}
