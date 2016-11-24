package com.gzfgeh.nbapp.Model;

import com.gzfgeh.nbapp.APP;
import com.gzfgeh.nbapp.Utils.RxUtils;
import com.gzfgeh.nbapp.Utils.Utils;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by
 */
@Singleton
public class IonicModel extends BaseModel {
    @Inject
    public IonicModel() {}

    public Observable<Boolean> getApkFinish(){
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                subscriber.onNext(Utils.copyApkFromAssets(APP.getContext(), "ionic.apk"));
                subscriber.onCompleted();
            }
        }).compose(RxUtils.applyIOToMainThreadSchedulers());
    }
}
