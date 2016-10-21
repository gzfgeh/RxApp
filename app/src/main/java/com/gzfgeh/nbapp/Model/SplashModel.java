package com.gzfgeh.nbapp.Model;

import com.gzfgeh.nbapp.Utils.RxUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Subscriber;

@Singleton
public class SplashModel extends BaseModel {
    @Inject
    public SplashModel() {
    }

    public Observable<String> getUrl() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg");
                subscriber.onCompleted();
            }
        }).compose(RxUtils.applyIOToMainThreadSchedulers());
    }
}