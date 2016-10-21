package com.gzfgeh.nbapp.Present;

import com.gzfgeh.nbapp.Model.SplashModel;
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
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        if (mCompositeSubscription != null)
                            mCompositeSubscription.remove(this);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (getView() != null)
                            getView().onFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(String data) {
                        if (getView() != null)
                            getView().getUrlData(data);
                    }
                }));
    }
}