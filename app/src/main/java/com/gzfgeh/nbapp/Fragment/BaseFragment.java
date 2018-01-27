package com.gzfgeh.nbapp.Fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.gzfgeh.nbapp.Present.BasePresenter;
import com.gzfgeh.nbapp.Utils.Dagger.Component.ActivityComponent;
import com.gzfgeh.nbapp.Utils.Dagger.Component.ActivityComponentFactory;
import com.gzfgeh.nbapp.Utils.ShareUtils;
import com.gzfgeh.nbapp.View.BaseView;

import javax.inject.Inject;

/**
 * Description:
 * Created by guzhenfu on 2016/10/25 14:21.
 */

public class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {
    @Inject
    P presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        inject();
        if(presenter != null)
            presenter.attachView(this);
    }

    /**
     * 如果有 依赖注入，这个必须要加
     */
    protected void inject(){

    }

    private ActivityComponent activityComponent;


    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = ActivityComponentFactory.create(getActivity());
        }
        return activityComponent;
    }


    @Override
    public void onFail(String msg) {

    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(presenter != null)
            presenter.detachView();
    }
}
