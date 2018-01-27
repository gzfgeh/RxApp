package com.gzfgeh.nbapp.Activity;

import android.os.Bundle;

import com.gzfgeh.nbapp.Present.BasePresenter;
import com.gzfgeh.nbapp.View.BaseView;

import javax.inject.Inject;

/**
 * Description:
 * Created by guzhenfu on 2018/1/27.
 */

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity implements BaseView {

    @Inject
    P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        if(presenter != null)
            presenter.attachView(this);
    }

    protected abstract void inject();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter != null)
            presenter.detachView();
    }

    @Override
    public void onFail(String msg) {

    }
}
