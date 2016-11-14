package com.gzfgeh.nbapp.Fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.gzfgeh.nbapp.Common.Contants;
import com.gzfgeh.nbapp.Component.ActivityComponent;
import com.gzfgeh.nbapp.Component.ActivityComponentFactory;
import com.gzfgeh.nbapp.Utils.ShareUtils;

/**
 * Description:
 * Created by guzhenfu on 2016/10/25 14:21.
 */

public class BaseFragment extends Fragment {
    private ActivityComponent activityComponent;

    private static final String ARG_PARAM1 = "param1";
    protected String mParam1;

    public static MyFragment newInstance(String param1) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }


    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = ActivityComponentFactory.create(getActivity());
        }
        return activityComponent;
    }


}
