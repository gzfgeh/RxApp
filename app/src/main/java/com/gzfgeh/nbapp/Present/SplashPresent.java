package com.gzfgeh.nbapp.Present;

import com.gzfgeh.nbapp.Model.SplashModel;
import com.gzfgeh.nbapp.Utils.RxSubUtils;
import com.gzfgeh.nbapp.View.SplashView;

import javax.inject.Inject;

import rx.Subscriber;

public class SplashPresent extends BasePresenter<SplashView> {
    @Inject
    SplashModel splashModel;

    @Inject
    public SplashPresent() {
    }

    public void getUrl() {
        mCompositeSubscription.add(splashModel.getUrl()
                .subscribe(new RxSubUtils<String>(mCompositeSubscription) {
                    @Override
                    protected void _onNext(String s) {
                        getView().getUrlData(s);
                    }

                    @Override
                    protected void _onError() {

                    }
                }));
    }
}