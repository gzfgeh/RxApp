package com.gzfgeh.nbapp.Present;

import com.gzfgeh.nbapp.Model.SplashModel;
import com.gzfgeh.nbapp.Utils.RxSubUtils;
import com.gzfgeh.nbapp.View.SplashView;

import javax.inject.Inject;

public class SplashPresent extends BasePresenter<SplashView> {
    @Inject
    SplashModel splashModel;

    @Inject
    public SplashPresent() {
    }

    public void getUrl() {
        compositeDisposable.add(splashModel.getUrl()
                .subscribeWith(new RxSubUtils<String>(compositeDisposable) {
                    @Override
                    protected void _onNext(String s) {
                        getView().getUrlData(s);
                    }
                }));
    }
}